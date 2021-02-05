package main;

public class KomentarRequest {

	private String komentar;
	private int ocena;
	private String manifestacija;
	private String korisnik;
	
	public KomentarRequest(String komentar, int ocena, String manifestacija, String korisnik) {
		super();
		this.komentar = komentar;
		this.ocena = ocena;
		this.manifestacija = manifestacija;
		this.korisnik = korisnik;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public String getManifestacija() {
		return manifestacija;
	}

	public void setManifestacija(String manifestacija) {
		this.manifestacija = manifestacija;
	}

	public String getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}
	
}
