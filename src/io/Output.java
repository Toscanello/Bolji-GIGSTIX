package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import model.Administrator;
import model.Karta;
import model.Korisnik;
import model.Kupac;
import model.Manifestacija;
import model.Prodavac;

public class Output extends BufferedReader {

	private ArrayList<String> buffer;

	
	public Output(String path) throws UnsupportedEncodingException, FileNotFoundException {
		super(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		this.buffer = new ArrayList<String>();
		String line = "";
		try {
			while((line = this.readLine()) != null) {
				this.buffer.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				this.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Delimiter -> ","
	
	public void ucitajKorisnike(ArrayList<Korisnik> lista) {
		
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/korisnici.txt"), "UTF-8"));
			
			String line = "";
			
			try {
				while((line = br.readLine()) != null) {
					
					String tokeni[] = line.split(",");
					String uloga = tokeni[6]; // Uzimamo ulogu trenutne linije
					System.out.println(uloga);
					if(uloga.equals("Kupac")) {
						lista.add(Kupac.parseString(line));
					}else if(uloga.equals("Prodavac")) {
						lista.add(Prodavac.parseString(line));
					}else if(uloga.equals("Administrator")) {
						lista.add(Administrator.parseString(line));
					}else {
						System.out.println("Error.\n");
					}
					
					
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
	
	}
	
	//******************************************************
	
public void ucitajKarte(ArrayList<Karta> lista) {
		
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/korisnici.txt"), "UTF-8"));
			
			String line = "";
			
			try {
				while((line = br.readLine()) != null) {
					lista.add(Karta.parseString(line));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
	
	}

//******************************************************

public void ucitajManifestacije(ArrayList<Manifestacija> lista) {
		
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/korisnici.txt"), "UTF-8"));
			
			String line = "";
			
			try {
				while((line = br.readLine()) != null) {
					lista.add(Manifestacija.parseString(line));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
	
	}
	
}