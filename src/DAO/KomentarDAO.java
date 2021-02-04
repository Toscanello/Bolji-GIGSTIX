package DAO;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import io.Output;
import model.Komentar;

public class KomentarDAO {
	public static ArrayList<Komentar> listaKomentara = new ArrayList<Komentar>();

	public static void dodajKomentar(Komentar k) {
		KomentarDAO.listaKomentara.add(k);
	}

	public static Komentar getKomentarById(String naziv) {
		for (Komentar komentar : listaKomentara) {
			if (komentar.getTekst().equals(naziv))
				return komentar;
		}
		return null;
	}

	public static boolean loadKomentare() throws UnsupportedEncodingException, FileNotFoundException {
		Output o = new Output("data/komentari.txt");
		o.ucitajKomentare(listaKomentara);
		if (listaKomentara.isEmpty())
			return false;
		return true;

	}

}
