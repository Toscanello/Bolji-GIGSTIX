package main;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Korisnik;
import model.Manifestacija;

public class Main {

	public static void main(String[] args) throws IOException {
		port(9090);
		Gson g = new Gson();
		JsonParser parser = new JsonParser();  
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
		post("/regManifestacije", (req, res) -> {
			JsonObject jsonObject = (JsonObject) parser.parse(req.body());  
			//Manifestacija m = new Manifestacija()
			System.out.println(jsonObject.get("naziv"));
			return "Pusi ga";
		});

	}

}
