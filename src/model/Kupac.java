package model;

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

}
