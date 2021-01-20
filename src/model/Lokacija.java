package model;

public class Lokacija {
	private Double geoDuzina;
	private Double geoSirina;
	private Adresa adresa;

	public Lokacija(Double geoDuzina, Double geoSirina, Adresa adresa) {
		super();
		this.geoDuzina = geoDuzina;
		this.geoSirina = geoSirina;
		this.adresa = adresa;
	}

	public Double getGeoDuzina() {
		return geoDuzina;
	}

	public void setGeoDuzina(Double geoDuzina) {
		this.geoDuzina = geoDuzina;
	}

	public Double getGeoSirina() {
		return geoSirina;
	}

	public void setGeoSirina(Double geoSirina) {
		this.geoSirina = geoSirina;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
}
