package main;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;

import DAO.KartaDAO;
import DAO.KorisnikDAO;
import DAO.ManifestacijeDAO;
import io.Input;
import model.Adresa;
import model.Karta;
import model.Korisnik;
import model.Kupac;
import model.Lokacija;
import model.Manifestacija;
import model.Prodavac;
import model.TipKupca;

public class Main {

	public static void main(String[] args) throws IOException {
		port(9090);
		Gson g = new Gson();
		ManifestacijeDAO.loadManifestacije();
		KorisnikDAO.loadKorisnike();
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		after((req, res) -> res.type("application/json"));
		get("/test", (req, res) -> {
			return "Works";
		});
		post("/login", (req, res) -> {
			try {
				Korisnik k = g.fromJson(req.body(), Korisnik.class);
				if((k=KorisnikDAO.getKorisnikByUsername(k.getUsername()))!=null) {
					res.status(200);
					return g.toJson(k);
				}else {
					res.status(404);
					return "Greska";
				}
				
			} catch (Exception e) {
				
			}
			return "Success";
		});
		
		get("/logout", (req, res) -> {
			return null;
		});
		
		post("/registruj", (req, res) -> {
			
			HashMap<String, String> mapa = g.fromJson(req.body(), HashMap.class);
			if(!mapa.get("pol").equals("MUSKI") && !mapa.get("pol").equals("ZENSKI")) {
				res.status(404);
				return "Error.";
			}
			
			String datum = mapa.get("datum");
			Date dateTest = null;
			try {
				dateTest = new SimpleDateFormat("dd.MM.yyyy").parse(datum);
			} catch (ParseException e) {
				res.status(404);
				return "Error.";
			}
			
			Kupac k = new Kupac(mapa.get("username"), mapa.get("password"), mapa.get("ime"), mapa.get("prezime"), mapa.get("pol"),
					mapa.get("datum"), mapa.get("uloga"), 0, new TipKupca("Nema Rank", 0, 0));
			//Korisnik k = g.fromJson(req.body(), Korisnik.class);
			ArrayList<Korisnik> listaKorisnika = KorisnikDAO.getListaKorisnika();
			listaKorisnika.add(k);
			
			Input o = new Input("data/korisnici.txt");
			o.snimiKorisnike(listaKorisnika);
			
			res.status(200);
			
			return g.toJson(k);
				
			
		});
		post("/regManifestacije", (req, res) -> {
			@SuppressWarnings("unchecked")
			HashMap<String, String> mapa = g.fromJson(req.body(), HashMap.class);
			String[] lokacija = mapa.get("lokacija").toString().split(",");
			Lokacija lo;
			try {
				lo = new Lokacija(Double.parseDouble(lokacija[0]), Double.parseDouble(lokacija[1]), new Adresa(
						(lokacija[2]), Integer.parseInt(lokacija[3]), lokacija[4], Integer.parseInt(lokacija[5])));
			} catch (Exception e) {
				res.status(400);
				return "Bad Location";
			}
			Manifestacija m;
			try {
				m = new Manifestacija(mapa.get("naziv"), mapa.get("tip"),
						Integer.parseInt(mapa.get("brojMesta")),
						LocalDateTime.parse(mapa.get("datum"), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
						Double.parseDouble(mapa.get("cena")), mapa.get("status"), lo, "zdravko");// slika
			} catch (Exception e) {
				res.status(400);
				return "Bad Br. Mesta, Datum or Cena";
			}
		
			ManifestacijeDAO.dodajManifestaciju(m);
			Prodavac p = (Prodavac) KorisnikDAO.getKorisnikByUsername(mapa.get("korisnik"));
			p.dodajManifestaciju(m);
			Input o = new Input("data/manifestacije.txt");
			o.snimiManifestacije(ManifestacijeDAO.getListaManifestacija());
			return ":D";
		});
		
		post("/aktiviraj/:id",(req,res)->{
			String naziv = req.params(":id");
			Manifestacija m = ManifestacijeDAO.getManifestacijaByNaziv(naziv);
			m.setStatus("aktivan");
			Input o = new Input("data/manifestacije.txt");
			o.snimiManifestacije(ManifestacijeDAO.getListaManifestacija());
			return "OK";
		});

		get("/manifestacije/getAll", (req, res) -> {
			res.type("application/json");
			return g.toJson(ManifestacijeDAO.listaManifestacija);
		});
		
		get("/korisnici/getAll", (req, res) -> {
			res.type("application/json");
			return g.toJson(KorisnikDAO.listaKorisnika);
		});
		
		get("/prikazManif/:id",(req,res)->{
			String naziv = req.params(":id");
			Manifestacija m = ManifestacijeDAO.getManifestacijaByNaziv(naziv);
			return g.toJson(m);
		});
		
		get("/karte/:id",(req,res)->{
			String naziv = req.params(":id");
			Kupac k = (Kupac) KorisnikDAO.getKorisnikByUsername(naziv);
			res.type("application/json");
			return g.toJson(KartaDAO.getKarteByKupac(k));
		});
		
		post("/otkazi/:id",(req,res)->{
			String naziv = req.params(":id");
			Karta k = KartaDAO.getKartaById(naziv);
			if(k.getDatum().plusDays(7).isAfter(LocalDateTime.now())) {
				k.setStatus("Otkazana");
				Integer bodovi = (int) (k.getCena()/1000*133*4);
				k.getKupac().setBrojBodova(k.getKupac().getBrojBodova()-bodovi);
				if(k.getKupac().getBrojBodova()<4000) {
					if(k.getKupac().getBrojBodova()<3000) {
						k.getKupac().getTip().setImeTipa("Bronzani");
						k.getKupac().getTip().setPopust(0);
					}else {
						k.getKupac().getTip().setImeTipa("Srebrni");
						k.getKupac().getTip().setPopust(3);
					}
				}
				Input o = new Input("data/korisnici.txt");
				o.snimiKorisnike(KorisnikDAO.listaKorisnika);
				
				k.getManifestacija().setBrojMesta(k.getManifestacija().getBrojMesta()+1);
				o = new Input("data/manifestacije.txt");
				o.snimiManifestacije(ManifestacijeDAO.getListaManifestacija());
				return "OK";
			}
			res.status(400);
			return "Bad";
		});
		
		get("/prikazKorisnika",(req,res)->{
			
			return "Succ";
		});
		
		post("/rezervisi",(req,res)->{
			HashMap<String, Object> mapa = g.fromJson(req.body(), HashMap.class);
			Manifestacija m = ManifestacijeDAO.getManifestacijaByNaziv(mapa.get("manifestacija").toString());
			Kupac k = (Kupac) KorisnikDAO.getKorisnikByUsername(mapa.get("korisnik").toString());
			Double kartaReg = Double.parseDouble(mapa.get("kartaReg").toString());
			Double kartaVip = Double.parseDouble(mapa.get("kartaVip").toString());
			Double kartaFun = Double.parseDouble(mapa.get("kartaFun").toString());
			if((kartaReg+kartaVip+kartaFun)>4.0) {
				res.status(400);
				return "Error";
			}
			Integer brojBodova = 0;
			for(int i=0;i<kartaReg;i++) {
				Karta karta = new Karta(KartaDAO.kreiranjeId(), m, m.getDatum(), m.getCena(), k, "rezervisana", "regular");
				brojBodova += (int) (karta.getCena()/1000*133);
				KartaDAO.dodajKartu(karta);
			}
			for(int i=0;i<kartaVip;i++) {
				Karta karta = new Karta(KartaDAO.kreiranjeId(), m, m.getDatum(), 2*m.getCena(), k, "rezervisana", "vip");
				brojBodova +=(int) (karta.getCena()/1000*133);
				KartaDAO.dodajKartu(karta);
			}
			for(int i=0;i<kartaFun;i++) {
				Karta karta = new Karta(KartaDAO.kreiranjeId(), m, m.getDatum(), 4*m.getCena(), k, "rezervisana", "fun");
				brojBodova +=(int) (karta.getCena()/1000*133);
				KartaDAO.dodajKartu(karta);
			}
			k.setBrojBodova(k.getBrojBodova()+brojBodova);
			if(k.getBrojBodova()>3000) {
				if(k.getBrojBodova()>4000) {
					k.getTip().setImeTipa("Zlatni");
					k.getTip().setPopust(5);
				}else {
					k.getTip().setImeTipa("Bronzani");
					k.getTip().setPopust(3);
				}
					
			}
			Double cena= kartaReg*m.getCena()+kartaVip*2*m.getCena()+kartaFun*4*m.getCena();
			cena = (100-k.getTip().getPopust())/100.0*cena;
			
			m.setBrojMesta((int) (m.getBrojMesta()-kartaReg-kartaVip-kartaFun));
			Input o = new Input("data/manifestacije.txt");
			o.snimiManifestacije(ManifestacijeDAO.getListaManifestacija());
			o = new Input("data/korisnici.txt");
			o.snimiKorisnike(KorisnikDAO.listaKorisnika);
			
			return g.toJson(cena);
		});
		
		post("/edit",(req,res)->{
			HashMap<String, String> mapa = g.fromJson(req.body(), HashMap.class);
			Korisnik k = KorisnikDAO.getKorisnikByUsername(mapa.get("username"));
			k.setIme(mapa.get("ime"));
			k.setPassword( mapa.get("password"));
			k.setPrezime(mapa.get("prezime"));
			k.setPol(mapa.get("pol"));
			k.setDatum(mapa.get("datum"));
			
			Input o = new Input("data/korisnici.txt");
			o.snimiKorisnike(KorisnikDAO.listaKorisnika);
			return "OK";
		});
	}
}
