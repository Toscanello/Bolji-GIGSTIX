package model;

import java.util.Date;

public class Karta {
	private String id;
	private Manifestacija manifestacija;
	private Date datum;
	private Double cena;
	private Kupac kupac;
	private String status; // rezervisana odustanak
	private String tip; // vip regular fan pit

	public Karta(String id, Manifestacija manifestacija, Date datum, Double cena, Kupac kupac, String status,
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

}
