package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kupac extends Korisnik {
	private List<Karta> karte;
	private Integer brojBodova;
	private TipKupca tip;

	public Kupac(String username, String password, String ime, String prezime, Pol pol, Date datum, String uloga, Integer brojBodova, TipKupca tip) {
		super(username, password, ime, prezime, pol, datum, uloga);
		this.karte = new ArrayList<Karta>();
		this.brojBodova = brojBodova;
		this.tip = tip;
	}
	
	public Kupac() {
		super();
	}

	public List<Karta> getKarte() {
		return karte;
	}

	public void setKarte(List<Karta> karte) {
		this.karte = karte;
	}
	
	public void dodajKartu(Karta k) {
		this.karte.add(k);
	}

	public Integer getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(Integer brojBodova) {
		this.brojBodova = brojBodova;
	}

	public TipKupca getTip() {
		return tip;
	}

	public void setTip(TipKupca tip) {
		this.tip = tip;
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
		Integer brojBodova = Integer.parseInt(tokeni[7]);
		TipKupca tp = TipKupca.stringToType(tokeni[8], tokeni[9], tokeni[10]);
		Kupac k = new Kupac(username, password, ime, prezime, pol, datum, uloga, brojBodova, tp);
		return k;
	}

	
	
}
