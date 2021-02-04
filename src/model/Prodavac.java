package model;

import java.util.ArrayList;
import java.util.List;

public class Prodavac extends Korisnik {
	private List<Manifestacija> manifestacija;

	public Prodavac(String username, String password, String ime, String prezime, String pol, String datum,
			String uloga) {
		super(username, password, ime, prezime, pol, datum, uloga);
		this.manifestacija = new ArrayList<Manifestacija>();
	}

	public List<Manifestacija> getManifestacija() {
		return manifestacija;
	}

	public void setManifestacija(List<Manifestacija> manifestacija) {
		this.manifestacija = manifestacija;
	}

	public void dodajManifestaciju(Manifestacija m) {
		this.manifestacija.add(m);
	}

	public static Korisnik parseString(String line) {
		String tokeni[] = line.split(",");
		String username = tokeni[0];
		String password = tokeni[1];
		String ime = tokeni[2];
		String prezime = tokeni[3];
		String pol = tokeni[4];
		String datum = tokeni[5];
		String uloga = tokeni[6];
		// ***********************************************
		Prodavac p = new Prodavac(username, password, ime, prezime, pol, datum, uloga);
		return p;
	}

	public static String toFileString(Prodavac k) {
		
		return k.username + "," + k.password + "," + k.ime + "," + k.prezime + "," + k.pol + "," + k.datum + "," + k.uloga;
		
	}
}
