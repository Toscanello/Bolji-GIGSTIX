package main;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import com.google.gson.Gson;

import DAO.ManifestacijeDAO;
import model.Adresa;
import model.Korisnik;
import model.Lokacija;
import model.Manifestacija;

public class Main {

	public static void main(String[] args) throws IOException {
		port(9090);
		Gson g = new Gson();
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		after((req, res) -> res.type("application/json"));
		ManifestacijeDAO.dodajManifestaciju(new Manifestacija(1, "koncert Zdravka Colica", "koncert", 250, LocalDateTime.parse("2007-12-03T10:15:30."), 2000.0,
				"aktivan", new Lokacija(10.0,10.0, new Adresa("4. juli", 27, "Zrenjanin", 23000)), "zdravko"));
		ManifestacijeDAO.dodajManifestaciju(new Manifestacija(2, "koncert Zdravka Colica", "koncert", 250, LocalDateTime.parse("2009-12-03T10:15:30."), 2000.0,
				"aktivan", new Lokacija(10.0,10.0, new Adresa("4. juli", 27, "Zrenjanin", 23000)), "zdravko"));
		ManifestacijeDAO.dodajManifestaciju(new Manifestacija(3, "koncert Zdravka Colica", "koncert", 250, LocalDateTime.parse("2008-12-03T10:15:30."), 2000.0,
				"aktivan", new Lokacija(10.0,10.0, new Adresa("4. juli", 27, "Zrenjanin", 23000)), "zdravko"));
		get("/test", (req, res) -> {
			return "Works";
		});
		post("/login", (req, res) -> {
			Korisnik k = g.fromJson(req.body(), Korisnik.class);
			System.out.println(k.getUsername());
			return g.toJson(k);
		});
		post("/registruj", (req, res) -> {
			Korisnik k = g.fromJson(req.body(), Korisnik.class);
			res.status(200);
			return g.toJson(k);
		});
		
		get("/manifestacije/getAll",(req,res)->{
			res.type("application/json");
			return g.toJson(ManifestacijeDAO.listaManifestacija);
		});
	}

}
