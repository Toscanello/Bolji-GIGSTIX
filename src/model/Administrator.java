package model;

import java.util.Date;

public class Administrator extends Korisnik {

	public Administrator(String username, String password, String ime, String prezime, Pol pol, Date datum,
			String uloga) {
		super(username, password, ime, prezime, pol, datum, uloga);
	}

}
