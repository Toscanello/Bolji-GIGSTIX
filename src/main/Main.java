package main;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;

import model.Korisnik;

public class Main {

	public static void main(String[] args) throws IOException {
		port(9090);
		Gson g = new Gson();
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		after((req, res) -> res.type("application/json"));
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
			
			return g.toJson(k);
		});
	}

}
