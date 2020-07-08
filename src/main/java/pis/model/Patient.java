package pis.model;
/**
 * Patient
 * @author fabia
 *
 */
public class Patient extends Person {
	private int patientID;
	private Krankenkasse krankenkasse;
	
	public Patient() {
	}

	public int getPatientenID() {
		return patientID;
	}
	public void setPatientenID(int patientID) {
		this.patientID = patientID;
	}
	@Override
	public String toString() {
		return String.format("%08d", this.patientID) + "|" +
				super.toString();
	}
	public String getPatientIDFormatted() {
		return String.format("%08d" , this.patientID);
	}

	public Krankenkasse getKrankenkasse() {
		return krankenkasse;
	}

	public void setKrankenkasse(Krankenkasse krankenkasse) {
		this.krankenkasse = krankenkasse;
	}
}
