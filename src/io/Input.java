package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import model.Administrator;
import model.Karta;
import model.Komentar;
import model.Korisnik;
import model.Kupac;
import model.Manifestacija;
import model.Prodavac;

public class Input extends OutputStreamWriter {

	private String buffer;

	public Input(String path) throws UnsupportedEncodingException, FileNotFoundException {
		super(new FileOutputStream(path, false), "UTF-8");
		this.buffer = "";
		
	}
	
	public void snimiKorisnike(ArrayList<Korisnik> lista) {
		
		String string = "";
		
		for (Korisnik k : lista) {
			
			if(k.getUloga().equals("Kupac")) {
				string = Kupac.toFileString((Kupac)k);
			}else if(k.getUloga().equals("Prodavac")) {
				string = Prodavac.toFileString((Prodavac) k);
			}else if(k.getUloga().equals("Administrator")) {
				string = Administrator.toFileString((Administrator) k);
			}else {
				
			}
			
			
			try {
				this.write(string + "\n");
				this.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
	}
	
	
	//******************************************************
	
	
	public void snimiKarte(ArrayList<Karta> lista) {
		
		String string = "";
		
		for (Karta k : lista) {
			
			string = Karta.toFileString(k);
			
			try {
				this.write(string + "\n");
				this.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
	}
	
	
	//******************************************************
	
	
		public void snimiManifestacije(ArrayList<Manifestacija> lista) throws IOException {
			
			String string = "";
			
			for (Manifestacija k : lista) {
				
				string = Manifestacija.toFileString(k);
				
				try {
					this.write(string + "\n");
					this.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
					
		}
		
	//******************************************************
	
	
		public void snimiKomentare(ArrayList<Komentar> lista) throws IOException {
			
			String string = "";
			
			for (Komentar k : lista) {
				
				string = Komentar.toFileString(k);
				
				try {
					this.write(string + "\n");
					this.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
					
		}
	
}
