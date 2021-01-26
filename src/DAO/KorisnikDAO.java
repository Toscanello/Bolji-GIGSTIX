package DAO;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import io.Output;
import model.Korisnik;
import model.Kupac;

public class KorisnikDAO {

	public static ArrayList<Korisnik> listaKorisnika = new ArrayList<Korisnik>();

	public static boolean loadKorisnike() throws UnsupportedEncodingException, FileNotFoundException {

		@SuppressWarnings("resource")
		Output o = new Output("data/korisnici.txt");
		o.ucitajKorisnike(listaKorisnika);
		if (listaKorisnika.isEmpty())
			return false;
		return true;
	}

	public static ArrayList<Korisnik> getListaKorisnika() {
		return listaKorisnika;
	}

	public static void setListaKorisnika(ArrayList<Korisnik> listaKorisnika) {
		KorisnikDAO.listaKorisnika = listaKorisnika;
	}

	public static Korisnik getKorisnikByUsername(String username) {

		for (Korisnik korisnik : listaKorisnika) {
			if (korisnik.getUsername().equals(username)) {
				return korisnik;
			}
		}
		return null;
	}

}
