package pis.model;
/**
 * Person
 * @author fabia
 *
 */
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
	public String getVollenNamen() {
		return this.name + ", " + this.vorname;
	}
	@Override
	public String toString() {
		return String.format("%20s", this.name)	+ "|" +
			   String.format("%20s", this.vorname) + "|" +
			   String.format("%40s", this.adresse);
	}
}
