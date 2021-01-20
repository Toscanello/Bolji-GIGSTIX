package DAO;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import io.Output;
import model.Manifestacija;

public class ManifestacijeDAO {
	
	public static ArrayList<Manifestacija> listaManifestacija;
	
	public static boolean loadManifestacije() throws UnsupportedEncodingException, FileNotFoundException {
		
		Output o = new Output("data/manifestacije.txt");
		ArrayList<Manifestacija> listaManifestacija = new ArrayList<Manifestacija>();
		o.ucitajManifestacije(listaManifestacija);
		setListaManifestacija(listaManifestacija);
		if(listaManifestacija.isEmpty()) return false;
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
			if(idManifestacije == manifestacija.getId()) {
				return manifestacija;
			}
		}
		return null;
	}
	
}
