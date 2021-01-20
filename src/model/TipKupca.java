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
	
	public static TipKupca stringToType(String s1, String s2, String s3) {
		
		TipKupca ret = new TipKupca(s1, Integer.parseInt(s2), Integer.parseInt(s3));
		return ret;
		
	}
}
