package model;

public class Adresa {
	private String ulica;
	private Integer broj;
	private String mesto;
	private Integer postBroj;

	public Adresa(String ulica, Integer broj, String mesto, Integer postBroj) {
		super();
		this.ulica = ulica;
		this.broj = broj;
		this.mesto = mesto;
		this.postBroj = postBroj;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public Integer getBroj() {
		return broj;
	}

	public void setBroj(Integer broj) {
		this.broj = broj;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public Integer getPostBroj() {
		return postBroj;
	}

	public void setPostBroj(Integer postBroj) {
		this.postBroj = postBroj;
	}
}
