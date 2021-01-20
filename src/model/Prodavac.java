package model;

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
}
