package model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import DAO.KorisnikDAO;
import DAO.ManifestacijeDAO;

public class Karta {
	private String id;
	private Manifestacija manifestacija;
	private LocalDateTime datum;
	private Double cena;
	private Kupac kupac;
	private String status; // rezervisana odustanak
	private String tip; // vip regular fan-pit

	public Karta(String id, Manifestacija manifestacija, LocalDateTime datum, Double cena, Kupac kupac, String status,
			String tip) {
		super();
		this.id = id;
		this.manifestacija = manifestacija;
		this.datum = datum;
		this.cena = cena;
		this.kupac = kupac;
		this.status = status;
		this.tip = tip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Manifestacija getManifestacija() {
		return manifestacija;
	}

	public void setManifestacija(Manifestacija manifestacija) {
		this.manifestacija = manifestacija;
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

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public static Karta parseString(String line) throws UnsupportedEncodingException, FileNotFoundException {
		String tokeni[] = line.split(",");
		String id = tokeni[0];
		String manif = tokeni[1];
		Manifestacija m = ManifestacijeDAO.getManifestacijaByNaziv(manif);
		
		LocalDateTime datum = LocalDateTime.parse(tokeni[2]);
		
		Double cena = Double.valueOf(tokeni[3]);
		//String username
		KorisnikDAO.loadKorisnike();
		Kupac k = (Kupac) KorisnikDAO.getKorisnikByUsername(tokeni[4]);
		
		String status = tokeni[5];
		String tip = tokeni[6];
		Karta karta = new Karta(id, m, datum, cena, k, status, tip);
		return karta;
	}

	public static String toFileString(Karta k) {
		
		Manifestacija m = k.getManifestacija();
		String idManifestacije = m.getNaziv();
		
		Kupac kup = k.getKupac();
		String kupacUsername = kup.getUsername();
		
		return k.getId() + "," + idManifestacije + "," + k.getDatum() + "," + k.getCena() + "," + kupacUsername + "," + k.getStatus() + "," + k.getTip();
		
	}

}
