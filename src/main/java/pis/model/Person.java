package pis.model;

public class Person {
	private String name, vorname, adresse;
	public Person() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	@Override
	public String toString() {
		return 	"Name: " + this.name + "\n" +
				"Vorname: " + this.vorname + "\n" + 
				"Adresse: " + this.adresse;
	}
}
