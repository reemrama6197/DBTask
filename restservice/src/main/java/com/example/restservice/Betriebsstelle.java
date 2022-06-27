package com.example.restservice;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.json.JSONException;
import com.google.gson.JsonObject;


public class Betriebsstelle {

	private final String id;
	private final String Name;
	private final String Kurzname;
	private final String Typ;
	

	public Betriebsstelle( String id) {
		// used to 
		this.id=id;
		this.Name="";
		this.Kurzname="";
		this.Typ="";
	}
	
	
	public Betriebsstelle( String id, String Name, String Kurzname, String Typ) {
		// TODO Auto-generated constructor stub
	
		this.id=id;
		this.Name=Name;
		this.Kurzname=Kurzname;
		this.Typ=Typ;
	}
	
	public String searchForBetriebsstelle(String id) throws FileNotFoundException, JSONException
	{
				
		Scanner fileScanner = null;

		File f = new File("betriebsstelle.csv");
		// checking if file exists
		boolean found=false;
	
		//check if file isnt empty
		if(f.exists() && !f.isDirectory()) { 
			fileScanner = new Scanner(new FileReader("betriebsstelle.csv"));
		}
			
		fileScanner.useDelimiter(";");

            while (fileScanner.hasNextLine()) {
            	
                String line = fileScanner.nextLine();
                String[] data = line.split(";");
                String betriebsstelleID = data[1];
                
                //check if the entered id is in the csv file
                if(betriebsstelleID.equals(id))
                {
                	// attach name, kurzname, typ to be able to print it
                	String Name=data[2];
                	String Kurzname=data[3];
                	String Typ=data[4];             	
            		Betriebsstelle betriebsstelle = new Betriebsstelle(betriebsstelleID, Name, Kurzname,Typ);
            		found=true;
            		fileScanner.close();
            		return betriebsstelle.tojson();
                }
            }
            
            if(!found)
            {
        		fileScanner.close();
            	return "null";
            }
			return "null";
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return Name;
	}
	 
	public String getKurzname() {
		return Kurzname;
	}
	public String getTyp() {
		return Typ;
	}
	
	 public String tojson() throws JSONException{
		 
		// using JsonObject from Gson library in order to preserve order
		// JSONObject had unreliable order
        JsonObject obj = new JsonObject();
        obj.addProperty("Name",this.Name);
        obj.addProperty("Kurzname",this.Kurzname);
        obj.addProperty("Typ",this.Typ);

        return obj.toString();
			    
	 }
	
}