package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Administrator extends Korisnik {

	public Administrator(String username, String password, String ime, String prezime, Pol pol, LocalDate datum,
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
		if (tokeni[4].equals(Pol.MUSKI)) {
			pol = Pol.MUSKI;
		} else {
			pol = Pol.ZENSKI;
		}
		LocalDate datum = LocalDate.parse(tokeni[5], DateTimeFormatter.ofPattern("dd.MM.yyyy"));

		String uloga = tokeni[6];
		// ***********************************************
		Administrator a = new Administrator(username, password, ime, prezime, pol, datum, uloga);
		return a;
	}
}
