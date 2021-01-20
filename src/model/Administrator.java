package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Administrator extends Korisnik {

	public Administrator(String username, String password, String ime, String prezime, Pol pol, Date datum,
			String uloga) {
		super(username, password, ime, prezime, pol, datum, uloga);
	}
	
	public static Korisnik parseString(String line) {
		String tokeni[] = line.split(",");
		String username = tokeni[0];
		String password = tokeni[1];
		String ime = tokeni[2];
		String prezime = tokeni[3];
		Pol pol;
		if(tokeni[4].equals(Pol.MUSKI)){
			pol = Pol.MUSKI;
		}else {
			pol = Pol.ZENSKI;
		}
		Date datum = null;
		try {
			datum = new SimpleDateFormat("dd.MM.yyyy").parse(tokeni[5]);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		String uloga = tokeni[6];
		// ***********************************************
		Administrator a = new Administrator(username, password, ime, prezime, pol, datum, uloga);
		return a;
	}
}
