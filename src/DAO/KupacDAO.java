package DAO;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import io.Output;
import model.Korisnik;
import model.Kupac;
import model.Manifestacija;

public class KupacDAO {

	public static ArrayList<Kupac> listaKupaca;

	public static boolean loadKupce() throws UnsupportedEncodingException, FileNotFoundException {

		Output o = new Output("data/korisnici.txt");
		ArrayList<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
		ArrayList<Kupac> listaKupaca = new ArrayList<Kupac>();
		o.ucitajKorisnike(listaKorisnika);

		for (Korisnik korisnik : listaKorisnika) {
			if (korisnik.getUloga().equals("Kupac")) {
				listaKupaca.add((Kupac) korisnik);
			}
		}
		setListaKupaca(listaKupaca);
		if (listaKupaca.isEmpty())
			return false;
		return true;
	}

	public static ArrayList<Kupac> getListaKupaca() {
		return listaKupaca;
	}

	public static void setListaKupaca(ArrayList<Kupac> listaKupaca) {
		KupacDAO.listaKupaca = listaKupaca;
	}

	public static Kupac getKupacByUsername(String username) {

		for (Kupac kupac : listaKupaca) {
			if(kupac.getUsername().equals(username)) {
				return kupac;
			}
		}
		return null;
	}

}
