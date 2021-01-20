package model;

public class Komentar {
	private Kupac kupac;
	private Manifestacija manifestacija;
	private String tekst;
	private Integer ocena; // 1-5

	public Komentar(Kupac kupac, Manifestacija manifestacija, String tekst, Integer ocena) {
		super();
		this.kupac = kupac;
		this.manifestacija = manifestacija;
		this.tekst = tekst;
		this.ocena = ocena;
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
}
