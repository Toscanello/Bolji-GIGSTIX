package DAO;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import io.Output;
import model.Korisnik;
import model.Kupac;

public class KorisnikDAO {

	public static ArrayList<Kupac> listaKorisnika;

	public static boolean loadKupce() throws UnsupportedEncodingException, FileNotFoundException {

		@SuppressWarnings("resource")
		Output o = new Output("data/korisnici.txt");
		ArrayList<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
		o.ucitajKorisnike(listaKorisnika);
		if (listaKorisnika.isEmpty())
			return false;
		return true;
	}

	public static ArrayList<Kupac> getListaKorisnika() {
		return listaKorisnika;
	}

	public static void setListaKorisnika(ArrayList<Kupac> listaKorisnika) {
		KorisnikDAO.listaKorisnika = listaKorisnika;
	}

	public static Kupac getKupacByUsername(String username) {

		for (Kupac kupac : listaKorisnika) {
			if (kupac.getUsername().equals(username)) {
				return kupac;
			}
		}
		return null;
	}

}
