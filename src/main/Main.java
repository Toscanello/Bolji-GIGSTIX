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
import model.TipKupca;

public class Main {

	public static void main(String[] args) throws IOException {
		port(9090);
		Gson g = new Gson();
		ManifestacijeDAO.loadManifestacije();
		KorisnikDAO.loadKorisnike();
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		after((req, res) -> res.type("application/json"));
		ManifestacijeDAO.dodajManifestaciju(new Manifestacija("koncert Zdravka Colica", "koncert", 250,
				LocalDateTime.parse("2007-12-03T10:15:30."), 2000.0, "aktivan",
				new Lokacija(10.0, 10.0, new Adresa("4. juli", 27, "Zrenjanin", 23000)), "zdravko"));
		ManifestacijeDAO.dodajManifestaciju(new Manifestacija("koncert Zdravka Colica", "koncert", 250,
				LocalDateTime.parse("2009-12-03T10:15:30."), 2000.0, "aktivan",
				new Lokacija(10.0, 10.0, new Adresa("4. juli", 27, "Zrenjanin", 23000)), "zdravko"));
		ManifestacijeDAO.dodajManifestaciju(new Manifestacija("koncert Zdravka Colica", "koncert", 250,
				LocalDateTime.parse("2008-12-03T10:15:30."), 2000.0, "aktivan",
				new Lokacija(10.0, 10.0, new Adresa("4. juli", 27, "Zrenjanin", 23000)), "zdravko"));
		get("/test", (req, res) -> {
			return "Works";
		});
		post("/login", (req, res) -> {
			Korisnik k = g.fromJson(req.body(), Korisnik.class);
			if((k=KorisnikDAO.getKorisnikByUsername(k.getUsername()))!=null) {
				res.status(200);
				return g.toJson(k);
			}
			res.status(404);
			return "Greska";
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
			Lokacija lo = new Lokacija(Double.parseDouble(lokacija[0]), Double.parseDouble(lokacija[1]), new Adresa(
					(lokacija[2]), Integer.parseInt(lokacija[3]), lokacija[4], Integer.parseInt(lokacija[5])));
			Manifestacija m = new Manifestacija(mapa.get("naziv"), mapa.get("tip"),
					Integer.parseInt(mapa.get("brojMesta")),
					LocalDateTime.parse(mapa.get("datum"), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
					Double.parseDouble(mapa.get("cena")), mapa.get("status"), lo, "zdravko");// slika
			ManifestacijeDAO.dodajManifestaciju(m);
			Input o = new Input("data/manifestacije.txt");
			o.snimiManifestacije(ManifestacijeDAO.getListaManifestacija());
			return ":D";
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
			for(int i=0;i<kartaReg;i++) {
				Karta karta = new Karta(KartaDAO.kreiranjeId(), m, m.getDatum(), m.getCena(), k, "rezervisana", "regular");
				KartaDAO.dodajKartu(karta);
			}
			for(int i=0;i<kartaVip;i++) {
				Karta karta = new Karta(KartaDAO.kreiranjeId(), m, m.getDatum(), 2*m.getCena(), k, "rezervisana", "vip");
				KartaDAO.dodajKartu(karta);
			}
			for(int i=0;i<kartaFun;i++) {
				Karta karta = new Karta(KartaDAO.kreiranjeId(), m, m.getDatum(), 4*m.getCena(), k, "rezervisana", "fun");
				KartaDAO.dodajKartu(karta);
			}
			
			return "";
		});
	}
}
