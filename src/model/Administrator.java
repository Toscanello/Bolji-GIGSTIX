package model;

public class Administrator extends Korisnik {

	public Administrator(String username, String password, String ime, String prezime, String pol, String datum,
			String uloga) {
		super(username, password, ime, prezime, pol, datum, uloga);
	}

	public static Korisnik parseString(String line) {
		String tokeni[] = line.split(",");
		String username = tokeni[0];
		String password = tokeni[1];
		String ime = tokeni[2];
		String prezime = tokeni[3];
		String pol = tokeni[4];
		String datum = tokeni[5];
		String uloga = tokeni[6];
		// ***********************************************
		Administrator a = new Administrator(username, password, ime, prezime, pol, datum, uloga);
		return a;
	}

	public static String toFileString(Administrator k) {
		
		 return k.username + "," + k.password + "," + k.ime + "," + k.prezime + "," + k.pol + "," + k.datum + "," + k.uloga;
	
	}
}
