package model;

import java.util.ArrayList;

import DAO.KorisnikDAO;
import DAO.ManifestacijeDAO;

public class Komentar {
	private Kupac kupac;
	private Manifestacija manifestacija;
	private String tekst;
	private Integer ocena; // 1-5
	private boolean aktivan;

	public Komentar(Kupac kupac, Manifestacija manifestacija, String tekst, Integer ocena) {
		super();
		this.kupac = kupac;
		this.manifestacija = manifestacija;
		this.tekst = tekst;
		this.ocena = ocena;
		this.aktivan = false;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public Manifestacija getManifestacija() {
		return manifestacija;
	}

	public void setManifestacija(Manifestacija manifestacija) {
		this.manifestacija = manifestacija;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	public static Komentar parseString(String line) {
		String tokeni[] = line.split(",");
		String usernameKupca = tokeni[0];
		String manifestacija = tokeni[1];
		String tekst = tokeni[2];
		Integer ocena = Integer.parseInt(tokeni[3]);
		String akt = tokeni[4];
		boolean aktivan = false;
		if(akt.equals("true")) {
			aktivan = true;
		}else {
			aktivan = false;
		}
		Kupac k = (Kupac)KorisnikDAO.getKorisnikByUsername(usernameKupca);
		
		Manifestacija m = ManifestacijeDAO.getManifestacijaByNaziv(manifestacija);
		
		Komentar kom = new Komentar(k, m, tekst, ocena);
		kom.setAktivan(aktivan);
		
		return kom;
		
	}

	public static String toFileString(Komentar k) {
		
		return k.getKupac().getUsername() + "," + k.getManifestacija().getNaziv() + "," + k.getTekst() + "," + k.getOcena()+","+k.isAktivan();
		
	}
}
