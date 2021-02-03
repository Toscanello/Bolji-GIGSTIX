package DAO;

import java.util.ArrayList;
import java.util.stream.Collectors;

import model.Karta;
import model.Kupac;

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
	
	public static ArrayList<Karta> getKarteByKupac(Kupac k){
		return (ArrayList<Karta>) listaKarata.stream().filter(karta->karta.getKupac().equals(k)).collect(Collectors.toList());
	}
	
	public static Karta getKartaById(String id) {
		for (Karta karta : listaKarata) {
			if(karta.getId().equals(id))
				return karta;
		}
		return null;
	}
}
