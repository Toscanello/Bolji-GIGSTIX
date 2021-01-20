package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prodavac extends Korisnik{
	private List<Manifestacija> manifestacija;

	public Prodavac(String username, String password, String ime, String prezime, Pol pol, Date datum, String uloga) {
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
		Pol pol;
		if(tokeni[4].equals(Pol.MUSKI)){
			pol = Pol.MUSKI;
		}else {
			pol = Pol.ZENSKI;
		}
		Date datum = null;
		try {
			datum = new SimpleDateFormat("dd.MM.yyyy").parse(tokeni[5]);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		String uloga = tokeni[6];
		// ***********************************************
		Prodavac p = new Prodavac(username, password, ime, prezime, pol, datum, uloga);
		return p;
	}
}
