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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import DAO.KartaDAO;
import DAO.KomentarDAO;
import DAO.KorisnikDAO;
import DAO.ManifestacijeDAO;
import io.Input;
import model.Adresa;
import model.Karta;
import model.Komentar;
import model.Korisnik;
import model.Kupac;
import model.Lokacija;
import model.Manifestacija;
import model.Prodavac;
import model.TipKupca;

public class Main {

	public static void main(String[] args) throws IOException {
		port(9090);

		/*JsonSerializer<LocalDateTime> jsonDateSerializer = (localDateTime, typeOfT, context) -> localDateTime == null
				? null
				: new JsonPrimitive(localDateTime.toString());

		Gson g = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, jsonDateSerializer).create();*/
		Gson g = new Gson();

		ManifestacijeDAO.loadManifestacije();
		KorisnikDAO.loadKorisnike();
		KomentarDAO.loadKomentare();
		
		
		
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		after((req, res) -> res.type("application/json"));
		get("/test", (req, res) -> {
			return "Works";
		});
		post("/login", (req, res) -> {
			try {
				Korisnik k = g.fromJson(req.body(), Korisnik.class);
				if ((k = KorisnikDAO.getKorisnikByUsername(k.getUsername())) != null) {
					res.status(200);
					return g.toJson(k);
				} else {
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
			if (!mapa.get("pol").equals("MUSKI") && !mapa.get("pol").equals("ZENSKI")) {
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

			Kupac k = new Kupac(mapa.get("username"), mapa.get("password"), mapa.get("ime"), mapa.get("prezime"),
					mapa.get("pol"), mapa.get("datum"), mapa.get("uloga"), 0, new TipKupca("Nema Rank", 0, 0));
			// Korisnik k = g.fromJson(req.body(), Korisnik.class);
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
				m = new Manifestacija(mapa.get("naziv"), mapa.get("tip"), Integer.parseInt(mapa.get("brojMesta")),
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

		post("/aktiviraj/:id", (req, res) -> {
			String naziv = req.params(":id");
			Manifestacija m = ManifestacijeDAO.getManifestacijaByNaziv(naziv);
			m.setStatus("aktivan");
			Input o = new Input("data/manifestacije.txt");
			o.snimiManifestacije(ManifestacijeDAO.getListaManifestacija());
			return "OK";
		});

		post("/pretragaKarte", (req, res) -> {
			HashMap<String, String> mapa = g.fromJson(req.body(), HashMap.class);

			String naziv = mapa.get("manifestacija");
			String cenaOd = mapa.get("cena_od");
			String cenaDo = mapa.get("cena_do");
			String datumOd = mapa.get("datum_od");
			String datumDo = mapa.get("datum_do");

			ArrayList<Karta> karte = KartaDAO.listaKarata;

			if (naziv.equals("") && cenaOd.equals("") && cenaDo.equals("") && datumOd.equals("") && datumDo.equals(""))
				return g.toJson(KartaDAO.listaKarata);

			if (!naziv.equals("")) {
				karte = (ArrayList<Karta>) karte.stream().filter(m -> m.getManifestacija().getNaziv().contains(naziv))
						.collect(Collectors.toList());
			}
			if (!cenaOd.equals("")) {
				Double cena = Double.parseDouble(cenaOd);
				karte = (ArrayList<Karta>) karte.stream().filter(m -> m.getCena() >= cena).collect(Collectors.toList());
			}
			if (!cenaDo.equals("")) {
				Double cena = Double.parseDouble(cenaDo);
				karte = (ArrayList<Karta>) karte.stream().filter(m -> m.getCena() <= cena).collect(Collectors.toList());
			}
			if (!datumOd.equals("")) {
				LocalDateTime datum = LocalDate.parse(datumOd, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
						.atStartOfDay();
				karte = (ArrayList<Karta>) karte.stream().filter(m -> m.getDatum().isAfter(datum))
						.collect(Collectors.toList());
			}
			if (!datumDo.equals("")) {
				LocalDateTime datum = LocalDate.parse(datumDo, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
						.atStartOfDay();
				karte = (ArrayList<Karta>) karte.stream().filter(m -> m.getDatum().isBefore(datum))
						.collect(Collectors.toList());
			}

			res.type("application/json");
			return g.toJson(karte);
		});

		post("/sortKarte", (req, res) -> {
			HashMap<String, Boolean> mapa = g.fromJson(req.body(), HashMap.class);

			boolean rast = mapa.get("rast");
			boolean opad = mapa.get("opad");
			boolean dat_opad = mapa.get("dat_opad");
			boolean dat_rast = mapa.get("dat_rast");

			ArrayList<Karta> karte = KartaDAO.listaKarata;

			if (rast) {
				int n = karte.size();
				Karta temp = null;
				for (int i = 0; i < n; i++) {
					for (int j = 1; j < (n - i); j++) {
						if (karte.get(j - 1).getManifestacija().getNaziv()
								.compareTo(karte.get(j).getManifestacija().getNaziv()) > 0) {
							// swap elements
							temp = karte.get(j - 1);
							karte.set(j - 1, karte.get(j));
							karte.set(j, temp);
						}

					}
				}
			}
			if (opad) {
				int n = karte.size();
				Karta temp = null;
				for (int i = 0; i < n; i++) {
					for (int j = 1; j < (n - i); j++) {
						if (karte.get(j - 1).getManifestacija().getNaziv()
								.compareTo(karte.get(j).getManifestacija().getNaziv()) < 0) {
							// swap elements
							temp = karte.get(j - 1);
							karte.set(j - 1, karte.get(j));
							karte.set(j, temp);
						}

					}
				}
			}
			if (dat_opad) {
				int n = karte.size();
				Karta temp = null;
				for (int i = 0; i < n; i++) {
					for (int j = 1; j < (n - i); j++) {
						if (karte.get(j - 1).getDatum().isAfter(karte.get(j).getDatum())) {
							// swap elements
							temp = karte.get(j - 1);
							karte.set(j - 1, karte.get(j));
							karte.set(j, temp);
						}

					}
				}
			}
			if (dat_rast) {
				int n = karte.size();
				Karta temp = null;
				for (int i = 0; i < n; i++) {
					for (int j = 1; j < (n - i); j++) {
						if (karte.get(j - 1).getDatum().isBefore(karte.get(j).getDatum())) {
							// swap elements
							temp = karte.get(j - 1);
							karte.set(j - 1, karte.get(j));
							karte.set(j, temp);
						}

					}
				}
			}

			res.type("application/json");
			return g.toJson(karte);
		});

		get("/manifestacije/getAll", (req, res) -> {
			res.type("application/json");
			return g.toJson(ManifestacijeDAO.listaManifestacija);
		});
		post("/pretragaManif", (req, res) -> {
			HashMap<String, String> mapa = g.fromJson(req.body(), HashMap.class);

			String naziv = mapa.get("naziv");
			String cenaOd = mapa.get("cena_od");
			String cenaDo = mapa.get("cena_do");
			String datumOd = mapa.get("datum_od");
			String datumDo = mapa.get("datum_do");

			ArrayList<Manifestacija> manif = ManifestacijeDAO.listaManifestacija;

			if (naziv.equals("") && cenaOd.equals("") && cenaDo.equals("") && datumOd.equals("") && datumDo.equals(""))
				return g.toJson(ManifestacijeDAO.listaManifestacija);

			if (!naziv.equals("")) {
				manif = (ArrayList<Manifestacija>) manif.stream().filter(m -> m.getNaziv().contains(naziv))
						.collect(Collectors.toList());
			}
			if (!cenaOd.equals("")) {
				Double cena = Double.parseDouble(cenaOd);
				manif = (ArrayList<Manifestacija>) manif.stream().filter(m -> m.getCena() >= cena)
						.collect(Collectors.toList());
			}
			if (!cenaDo.equals("")) {
				Double cena = Double.parseDouble(cenaDo);
				manif = (ArrayList<Manifestacija>) manif.stream().filter(m -> m.getCena() <= cena)
						.collect(Collectors.toList());
			}
			if (!datumOd.equals("")) {
				LocalDateTime datum = LocalDate.parse(datumOd, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
						.atStartOfDay();
				manif = (ArrayList<Manifestacija>) manif.stream().filter(m -> m.getDatum().isAfter(datum))
						.collect(Collectors.toList());
			}
			if (!datumDo.equals("")) {
				LocalDateTime datum = LocalDate.parse(datumDo, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
						.atStartOfDay();
				manif = (ArrayList<Manifestacija>) manif.stream().filter(m -> m.getDatum().isAfter(datum))
						.collect(Collectors.toList());
			}

			res.type("application/json");
			return g.toJson(manif);
		});
		
		get("/korisnici/getAll", (req, res) -> {
			res.type("application/json");
			return g.toJson(KorisnikDAO.listaKorisnika);
		});

		get("/prikazManif/:id", (req, res) -> {
			String naziv = req.params(":id");
			Manifestacija m = ManifestacijeDAO.getManifestacijaByNaziv(naziv);
			return g.toJson(m);
		});

		get("/karte/:id", (req, res) -> {
			String naziv = req.params(":id");
			Kupac k = (Kupac) KorisnikDAO.getKorisnikByUsername(naziv);
			res.type("application/json");
			return g.toJson(KartaDAO.getKarteByKupac(k));
		});

		post("/otkazi/:id", (req, res) -> {
			String naziv = req.params(":id");
			Karta k = KartaDAO.getKartaById(naziv);
			if (k.getDatum().minusDays(7).isAfter(LocalDateTime.now())) {
				k.setStatus("Otkazana");
				Integer bodovi = (int) (k.getCena() / 1000 * 133 * 4);
				k.getKupac().setBrojBodova(k.getKupac().getBrojBodova() - bodovi);
				if (k.getKupac().getBrojBodova() < 4000) {
					if (k.getKupac().getBrojBodova() < 3000) {
						k.getKupac().getTip().setImeTipa("Bronzani");
						k.getKupac().getTip().setPopust(0);
					} else {
						k.getKupac().getTip().setImeTipa("Srebrni");
						k.getKupac().getTip().setPopust(3);
					}
				}
				Input o = new Input("data/korisnici.txt");
				o.snimiKorisnike(KorisnikDAO.listaKorisnika);

				k.getManifestacija().setBrojMesta(k.getManifestacija().getBrojMesta() + 1);
				o = new Input("data/manifestacije.txt");
				o.snimiManifestacije(ManifestacijeDAO.getListaManifestacija());
				return "OK";
			}
			res.status(400);
			return "Bad";
		});

		get("/prikazKorisnika", (req, res) -> {

			return "Succ";
		});

		post("/rezervisi", (req, res) -> {
			HashMap<String, Object> mapa = g.fromJson(req.body(), HashMap.class);
			Manifestacija m = ManifestacijeDAO.getManifestacijaByNaziv(mapa.get("manifestacija").toString());
			Kupac k = (Kupac) KorisnikDAO.getKorisnikByUsername(mapa.get("korisnik").toString());
			Double kartaReg = Double.parseDouble(mapa.get("kartaReg").toString());
			Double kartaVip = Double.parseDouble(mapa.get("kartaVip").toString());
			Double kartaFun = Double.parseDouble(mapa.get("kartaFun").toString());
			if ((kartaReg + kartaVip + kartaFun) > 4.0 ||m.getDatum().isBefore(LocalDateTime.now())) {
				res.status(400);
				return "Error";
			}
			Integer brojBodova = 0;
			for (int i = 0; i < kartaReg; i++) {
				Karta karta = new Karta(KartaDAO.kreiranjeId(), m, m.getDatum(), m.getCena(), k, "rezervisana",
						"regular");
				brojBodova += (int) (karta.getCena() / 1000 * 133);
				KartaDAO.dodajKartu(karta);
			}
			for (int i = 0; i < kartaVip; i++) {
				Karta karta = new Karta(KartaDAO.kreiranjeId(), m, m.getDatum(), 2 * m.getCena(), k, "rezervisana",
						"vip");
				brojBodova += (int) (karta.getCena() / 1000 * 133);
				KartaDAO.dodajKartu(karta);
			}
			for (int i = 0; i < kartaFun; i++) {
				Karta karta = new Karta(KartaDAO.kreiranjeId(), m, m.getDatum(), 4 * m.getCena(), k, "rezervisana",
						"fun");
				brojBodova += (int) (karta.getCena() / 1000 * 133);
				KartaDAO.dodajKartu(karta);
			}
			k.setBrojBodova(k.getBrojBodova() + brojBodova);
			if (k.getBrojBodova() > 3000) {
				if (k.getBrojBodova() > 4000) {
					k.getTip().setImeTipa("Zlatni");
					k.getTip().setPopust(5);
				} else {
					k.getTip().setImeTipa("Bronzani");
					k.getTip().setPopust(3);
				}

			}
			Double cena = kartaReg * m.getCena() + kartaVip * 2 * m.getCena() + kartaFun * 4 * m.getCena();
			cena = (100 - k.getTip().getPopust()) / 100.0 * cena;

			m.setBrojMesta((int) (m.getBrojMesta() - kartaReg - kartaVip - kartaFun));
			Input o = new Input("data/manifestacije.txt");
			o.snimiManifestacije(ManifestacijeDAO.getListaManifestacija());
			o = new Input("data/korisnici.txt");
			o.snimiKorisnike(KorisnikDAO.listaKorisnika);

			return g.toJson(cena);
		});

		post("/edit", (req, res) -> {
			HashMap<String, String> mapa = g.fromJson(req.body(), HashMap.class);
			Korisnik k = KorisnikDAO.getKorisnikByUsername(mapa.get("username"));
			k.setIme(mapa.get("ime"));
			k.setPassword(mapa.get("password"));
			k.setPrezime(mapa.get("prezime"));
			k.setPol(mapa.get("pol"));
			k.setDatum(mapa.get("datum"));

			Input o = new Input("data/korisnici.txt");
			o.snimiKorisnike(KorisnikDAO.listaKorisnika);
			return "OK";
		});

		post("/komentarisi", (req, res) -> {
			KomentarRequest kr =g.fromJson(req.body(), KomentarRequest.class);
			Kupac kup = (Kupac) KorisnikDAO.getKorisnikByUsername(kr.getKorisnik());
			Manifestacija m = ManifestacijeDAO.getManifestacijaByNaziv(kr.getManifestacija());
			if(m.getDatum().isAfter(LocalDateTime.now())||!KartaDAO.proveri(kup,m)) {
				res.status(400);
				return "Bad";
			}
			Komentar k = new Komentar(kup, m, kr.getKomentar(), kr.getOcena());
			m.dodajKomentar(k);
			KomentarDAO.dodajKomentar(k);
			Input o = new Input("data/komentari.txt");
			o.snimiKomentare(KomentarDAO.listaKomentara);
			return "OK";

		});

		get("/komentari/:id", (req, res) -> {
			String naziv = req.params(":id");
			ArrayList<Komentar> komentari = new ArrayList<Komentar>();
			for (Komentar kom : KomentarDAO.listaKomentara) {
				if(kom.getManifestacija().getNaziv().equals(naziv))
					komentari.add(kom);
			}
			res.type("application/json");
			return g.toJson(komentari);
		});
		
		get("/komentari", (req, res) -> {
			res.type("application/json");
			System.out.println(KomentarDAO.listaKomentara.size());
			return g.toJson(KomentarDAO.listaKomentara);
		});
		
		post("/odobri/:id", (req, res) -> {
			String naziv = req.params(":id");
			Komentar k = KomentarDAO.getKomentarById(naziv);
			k.setAktivan(true);
			Input o = new Input("data/komentari.txt");
			o.snimiKomentare(KomentarDAO.listaKomentara);
			return "OK";
		});

	}
}
