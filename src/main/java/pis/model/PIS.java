package pis.model;

import java.security.InvalidKeyException;
import java.util.HashMap;

public class PIS {
	private HashMap<Integer, Patient> patienten;
	private HashMap<Integer, Arzt> aerzte;
	private HashMap<Integer, Fall> faelle;
	public PIS() {
		this.patienten = new HashMap<Integer, Patient>();
		this.aerzte = new HashMap<Integer, Arzt>();
		this.faelle = new HashMap<Integer, Fall>();
	}
	public HashMap<Integer, Patient> getPatienten() {
		return patienten;
	}
	public void addPatient(Patient patient) throws InvalidKeyException {
		if (this.patienten.containsKey(patient.getPatientenID())) {
			Patient x = this.getPatient(patient.getPatientenID());
			throw new InvalidKeyException(
					"Patienten-ID ist bereits vergeben an: " + 
							x.getName() + ", " + x.getVorname()
			);
		}
		this.patienten.put(patient.getPatientenID(), patient);
	}
	public Patient getPatient(int patientID) throws InvalidKeyException {
		if (!this.patienten.containsKey(patientID))
			throw new InvalidKeyException("Patienten-ID wurde nicht gefunden!");
		return this.patienten.get(patientID);
	}
	public void deletePatient(int patientID) throws InvalidKeyException {
		if (!this.patienten.containsKey(patientID))
			throw new InvalidKeyException("Patienten-ID wurde nicht gefunden!");
		this.patienten.remove(patientID);
	}
	public HashMap<Integer, Arzt> getAerzte() {
		return aerzte;
	}
	public void addArzt(Arzt arzt) throws InvalidKeyException {
		if (this.aerzte.containsKey(arzt.getArztID())) {
			Arzt x = this.getArzt(arzt.getArztID());
			throw new InvalidKeyException(
					"Arzt-ID ist bereits vergeben an: " + 
							x.getName() + ", " + x.getVorname()
			);
		}
		this.aerzte.put(arzt.getArztID(), arzt);
	}
	public Arzt getArzt(int arztID) throws InvalidKeyException {
		if (!this.aerzte.containsKey(arztID))
			throw new InvalidKeyException("Arzt-ID wurde nicht gefunden!");
		return this.aerzte.get(arztID);
	}
	public void deleteArzt(int arztID) throws InvalidKeyException {
		if (!this.aerzte.containsKey(arztID))
			throw new InvalidKeyException("Arzt-ID wurde nicht gefunden!");
		this.aerzte.remove(arztID);
	}
	public HashMap<Integer, Fall> getFaelle() {
		return faelle;
	}
	public void addFall(Fall fall) throws InvalidKeyException {
		if (this.faelle.containsKey(fall.getFallID()))
			throw new InvalidKeyException("Fall-ID ist bereits vergeben");
		this.faelle.put(fall.getFallID(), fall);
	}
	public Fall getFall(int fallID) throws InvalidKeyException {
		if (!this.faelle.containsKey(fallID))
			throw new InvalidKeyException("Fall-ID wurde nicht gefunden!");
		return this.faelle.get(fallID);
	}
	public void deleteFall(int fallID) throws InvalidKeyException {
		if (!this.faelle.containsKey(fallID))
			throw new InvalidKeyException("Fall-ID wurde nicht gefunden!");
		this.faelle.remove(fallID);
	}
	
}
