package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Manifestacija implements Comparable<Manifestacija> {
	private String naziv;
	private String tip;
	private Integer brojMesta;
	private LocalDateTime datum;
	private Double cena;
	private String status; // aktivan neaktivan
	private Lokacija lokacija;
	private String slika; // proveriti kako izgleda slika

	public Manifestacija(String naziv, String tip, Integer brojMesta, LocalDateTime datum, Double cena,
			String status, Lokacija lokacija, String slika) {
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

	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public String getStatus() {
		return status;
	}

	//
	public static void returnManifestaciju(Integer id) {

	}

	public static Manifestacija parseString(String line) {
		String tokeni[] = line.split(",");
		String naziv = tokeni[0];
		String tip = tokeni[1];
		Integer brojMesta = Integer.parseInt(tokeni[2]);
		LocalDateTime datum = null;
		datum = LocalDateTime.parse(tokeni[3]);
		Double cena = Double.valueOf(tokeni[4]);
		String status = tokeni[5];
		Double gDuz = Double.valueOf(tokeni[6]);
		Double gSir = Double.valueOf(tokeni[7]);
		String ulica = tokeni[8];
		Integer broj = Integer.valueOf(tokeni[9]);
		String mesto = tokeni[10];
		Integer post = Integer.valueOf(tokeni[11]);
		String slika = tokeni[12]; // Slika ?

		Adresa adr = new Adresa(ulica, broj, mesto, post);
		Lokacija loc = new Lokacija(gDuz, gSir, adr);

		Manifestacija manif = new Manifestacija(naziv, tip, brojMesta, datum, cena, status, loc, slika);

		return manif;
	}

	public static String toFileString(Manifestacija k) {

		Lokacija l = k.getLokacija();
		String gDuz = Double.toString(l.getGeoDuzina());
		String gSir = Double.toString(l.getGeoSirina());

		Adresa adr = l.getAdresa();

		return k.getNaziv() + "," + k.getTip() + "," + k.getBrojMesta() + "," + k.getDatum().toString() + ","
				+ k.getCena() + "," + k.getStatus() + "," + gDuz + "," + gSir + "," + adr.getUlica() + ","
				+ adr.getBroj() + "," + adr.getMesto() + "," + adr.getPostBroj() + "," + k.getSlika();

	}

	@Override
	public int compareTo(Manifestacija o) {
		return this.datum.compareTo(o.getDatum());
	}

}
