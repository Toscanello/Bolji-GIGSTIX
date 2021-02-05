package DAO;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import io.Output;
import model.Karta;
import model.Kupac;
import model.Manifestacija;

public class KartaDAO {
	public static ArrayList<Karta> listaKarata = new ArrayList<Karta>();

	public static void dodajKartu(Karta k) {
		listaKarata.add(k);
	}

	public static String kreiranjeId() {
		Integer newId = null;
		if (listaKarata.isEmpty()) {
			newId = 1;
		} else {
			String id = listaKarata.get(listaKarata.size() - 1).getId();
			newId = Integer.parseInt(id) + 1;
		}
		return newId.toString();
	}

	public static ArrayList<Karta> getKarteByKupac(Kupac k) {
		return (ArrayList<Karta>) listaKarata.stream().filter(karta -> karta.getKupac().equals(k))
				.collect(Collectors.toList());
	}

	public static Karta getKartaById(String id) {
		for (Karta karta : listaKarata) {
			if (karta.getId().equals(id))
				return karta;
		}
		return null;
	}

	public static boolean proveri(Kupac k, Manifestacija m) {
		for (Karta karta : listaKarata) {
			if (karta.getKupac().getUsername().equals(k.getUsername())
					&& karta.getManifestacija().getNaziv().equals(m.getNaziv()))
				return true;
		}

		return false;
	}

	public static ArrayList<Karta> getListaKarata() {
		return listaKarata;
	}

	public static void setListaKarata(ArrayList<Karta> listaKarata) {
		KartaDAO.listaKarata = listaKarata;
	}

	public static boolean loadKarte() throws UnsupportedEncodingException, FileNotFoundException {
		
		@SuppressWarnings("resource")
		Output o = new Output("data/karte.txt");
		o.ucitajKarte(listaKarata);
		if (listaKarata.isEmpty())
			return false;
		return true;
		
	}
	
}
