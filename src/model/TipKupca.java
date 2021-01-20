package model;

public class TipKupca {
	private String imeTipa;
	private Integer popust;
	private Integer brojBodova; // potreban broj bodova da bi se rank-up

	public TipKupca(String imeTipa, Integer popust, Integer brojBodova) {
		super();
		this.imeTipa = imeTipa;
		this.popust = popust;
		this.brojBodova = brojBodova;
	}

	public String getImeTipa() {
		return imeTipa;
	}

	public void setImeTipa(String imeTipa) {
		this.imeTipa = imeTipa;
	}

	public Integer getPopust() {
		return popust;
	}

	public void setPopust(Integer popust) {
		this.popust = popust;
	}

	public Integer getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(Integer brojBodova) {
		this.brojBodova = brojBodova;
	}
}
