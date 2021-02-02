package DAO;

import java.util.ArrayList;

import model.Karta;

public class KartaDAO {
	public static ArrayList<Karta> listaKarata=new ArrayList<Karta>();
	
	public static void dodajKartu(Karta k) {
		listaKarata.add(k);
	}
	
	public static String kreiranjeId() {
		Integer newId = null;
		if(listaKarata.isEmpty()) {
			newId = 1;
		}else {
			String id=listaKarata.get(listaKarata.size()-1).getId();
			newId = Integer.parseInt(id)+1;
		}
		return newId.toString();
	}
}
