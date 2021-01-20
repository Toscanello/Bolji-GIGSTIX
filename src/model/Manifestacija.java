package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Manifestacija {
	private Integer id;
	private String naziv;
	private String tip;
	private Integer brojMesta;
	private Date datum;
	private Double cena;
	private String status; // aktivan neaktivan
	private Lokacija lokacija;
	private String slika; // proveriti kako izgleda slika

	public Manifestacija(Integer id, String naziv, String tip, Integer brojMesta, Date datum, Double cena, String status,
			Lokacija lokacija, String slika) {
		super();
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	//
	public static void returnManifestaciju(Integer id) {
		
		
		
	}

	public static Manifestacija parseString(String line) {
		String tokeni[] = line.split(",");
		Integer id = Integer.parseInt(tokeni[0]);
		String naziv = tokeni[1];
		String tip = tokeni[2];
		Integer brojMesta = Integer.parseInt(tokeni[3]);
		Date datum = null;
		try {
			datum = new SimpleDateFormat("dd.MM.yyyy").parse(tokeni[4]);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		Double cena = Double.valueOf(tokeni[5]);
		String status = tokeni[6];
		Double gDuz = Double.valueOf(tokeni[7]);
		Double gSir = Double.valueOf(tokeni[8]);
		String ulica = tokeni[9];
		Integer broj = Integer.valueOf(tokeni[10]);
		String mesto = tokeni[11];
		Integer post = Integer.valueOf(tokeni[12]);
		String slika = tokeni[13]; // Slika ?
		
		Adresa adr = new Adresa(ulica, broj, mesto, post);
		Lokacija loc = new Lokacija(gDuz, gSir, adr);
		
		Manifestacija manif = new Manifestacija(id, naziv, tip, brojMesta, datum, cena, status, loc, slika);
		
		return manif;
	}

}
