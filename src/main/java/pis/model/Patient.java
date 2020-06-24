package pis.model;

public class Patient extends Person {
	private int patientID;
	
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
		return "Patienten-ID: " + this.patientID + "\n" +
				super.toString();
	}
}
