package main;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;

import model.KupacToCheck;

public class Main {

	public static void main(String[] args) throws IOException {
		port(9090);
		Gson g = new Gson();
		// Kupac k = new Kupac("ika", "ika", "ïvan", "luburic", Pol.MUSKI,
		// LocalDate.parse("10.12.1999", DateTimeFormatter.ofPattern("dd.MM.yyyy")),
		// "kupac", 10, null);
		// KupacDAO.dodajKupca(k);
		staticFiles.externalLocation(new File("./static").getCanonicalPath());

		get("/test", (req, res) -> {
			return "Works";
		});
		post("/login", (req, res) -> {
			KupacToCheck ktc = g.fromJson(req.body(), KupacToCheck.class);
			// Kupac k1 = KupacDAO.getKupacByUsername(ktc.getUsername());
			// return k1.getUsername();
			return "200 OK";
		});
	}

}
