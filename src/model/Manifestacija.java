package model;

import java.util.Date;

public class Manifestacija {
	private String naziv;
	private String tip;
	private Integer brojMesta;
	private Date datum;
	private Double cena;
	private boolean status; // aktivan neaktivan
	private Lokacija lokacija;
	private String slika; // proveriti kako izgleda slika

	public Manifestacija(String naziv, String tip, Integer brojMesta, Date datum, Double cena, boolean status,
			Lokacija lokacija, String slika) {
		super();
		this.naziv = naziv;
		this.tip = tip;
		this.brojMesta = brojMesta;
		this.datum = datum;
		this.cena = cena;
		this.status = status;
		this.lokacija = lokacija;
		this.slika = slika;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Integer getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(Integer brojMesta) {
		this.brojMesta = brojMesta;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

}
