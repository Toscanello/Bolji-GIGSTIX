package main;

import static spark.Spark.*;

import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		port(9090);
		
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		get("/test",(req,res)->{
			return "Works";
		});
	}

}
