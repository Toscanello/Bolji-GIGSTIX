package model;

import java.time.LocalDate;

public abstract class Korisnik {
	protected String username;
	protected String password;
	protected String ime;
	protected String prezime;
	protected Pol pol;
	protected LocalDate datum;
	protected String uloga; //admin prodavac kupac
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public Pol getPol() {
		return pol;
	}
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public Korisnik(String username, String password, String ime, String prezime, Pol pol, LocalDate datum, String uloga) {
		super();
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datum = datum;
		this.uloga = uloga;
	}
	
	public Korisnik() {
		
	}
	
}
