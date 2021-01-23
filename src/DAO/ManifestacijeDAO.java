package DAO;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

import io.Output;
import model.Manifestacija;

public class ManifestacijeDAO {

	public static ArrayList<Manifestacija> listaManifestacija = new ArrayList<Manifestacija>();

	public static boolean loadManifestacije() throws UnsupportedEncodingException, FileNotFoundException {

		Output o = new Output("data/manifestacije.txt");
		o.ucitajManifestacije(listaManifestacija);
		setListaManifestacija(listaManifestacija);
		if (listaManifestacija.isEmpty())
			return false;
		return true;
	}

	public static ArrayList<Manifestacija> getListaManifestacija() {
		return listaManifestacija;
	}

	public static void setListaManifestacija(ArrayList<Manifestacija> listaManifestacija) {
		ManifestacijeDAO.listaManifestacija = listaManifestacija;
	}

	public static Manifestacija getManifestacijaByID(Integer idManifestacije) {

		for (Manifestacija manifestacija : listaManifestacija) {
			if (idManifestacije == manifestacija.getId()) {
				return manifestacija;
			}
		}
		return null;
	}

	public static void dodajManifestaciju(Manifestacija m) {
		listaManifestacija.add(m);
		sort();
	}

	public static void sort() {
		Collections.sort(listaManifestacija);
		Collections.reverse(listaManifestacija);
	}

}
