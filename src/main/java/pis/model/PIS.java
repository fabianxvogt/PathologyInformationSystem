package pis.model;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
/**
 * PIS Anwendung
 * @author fabia
 *
 */
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
	/**
	 * Fügt einen neuen Patienten zum PIS hinzu
	 * @param patient neuer Patient
	 * @throws InvalidKeyException Falls Patienten-ID bereits vergeben
	 */
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
	/**
	 * Gibt einen Patienten anhand der ID zurueck
	 * @param patientID ID
	 * @return Patient
	 * @throws InvalidKeyException Falls Patienten-ID nicht gefunden
	 */
	public Patient getPatient(int patientID) throws InvalidKeyException {
		if (!this.patienten.containsKey(patientID))
			throw new InvalidKeyException("Patienten-ID wurde nicht gefunden!");
		return this.patienten.get(patientID);
	}
	/**
	 * Loescht einen Patienten aus dem PIS anhand der ID
	 * @param patientID ID
	 * @throws InvalidKeyException Falls ID nicht gefunden
	 */
	public void deletePatient(int patientID) throws InvalidKeyException {
		if (!this.patienten.containsKey(patientID))
			throw new InvalidKeyException("Patienten-ID wurde nicht gefunden!");
		this.patienten.remove(patientID);
	}
	public HashMap<Integer, Arzt> getAerzte() {
		return aerzte;
	}
	/**
	 * Fügt einen neuen Arzt zum PIS hinzu
	 * @param arzt neuer Arzt
	 * @throws InvalidKeyException Falls Arzt-ID bereits vergeben
	 */
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
	/**
	 * Gibt einen Arzt anhand der ID zurueck
	 * @param arztID ID
	 * @return Arzt
	 * @throws InvalidKeyException Falls Arzt-ID nicht gefunden
	 */
	public Arzt getArzt(int arztID) throws InvalidKeyException {
		if (!this.aerzte.containsKey(arztID))
			throw new InvalidKeyException("Arzt-ID wurde nicht gefunden!");
		return this.aerzte.get(arztID);
	}
	/**
	 * Loescht einen Arzt aus dem PIS anhand der ID
	 * @param arztID ID
	 * @throws InvalidKeyException Falls ID nicht gefunden
	 */
	public void deleteArzt(int arztID) throws InvalidKeyException {
		if (!this.aerzte.containsKey(arztID))
			throw new InvalidKeyException("Arzt-ID wurde nicht gefunden!");
		this.aerzte.remove(arztID);
	}
	public HashMap<Integer, Fall> getFaelle() {
		return faelle;
	}
	/**
	 * Neuen Fall zum PIS hinzufuegen
	 * @param fall neuer Fall
	 * @throws InvalidKeyException Falls Fall ID bereits vergeben
	 */
	public void addFall(Fall fall) throws InvalidKeyException {
		if (this.faelle.containsKey(fall.getFallID()))
			throw new InvalidKeyException("Fall-ID ist bereits vergeben");
		this.faelle.put(fall.getFallID(), fall);
	}
	/**
	 * Gibt einen Fall anhand der ID zurueck
	 * @param fallID ID
	 * @return Fall
	 * @throws InvalidKeyException falls Fall-ID nicht gefunden
	 */
	public Fall getFall(int fallID) throws InvalidKeyException {
		if (!this.faelle.containsKey(fallID))
			throw new InvalidKeyException("Fall-ID wurde nicht gefunden!");
		return this.faelle.get(fallID);
	}
	/**
	 * Loescht einen Fall anhand der ID
	 * @param fallID ID
	 * @return Fall
	 * @throws InvalidKeyException falls Fall-ID nicht gefunden
	 */
	public void deleteFall(int fallID) throws InvalidKeyException {
		if (!this.faelle.containsKey(fallID))
			throw new InvalidKeyException("Fall-ID wurde nicht gefunden!");
		this.faelle.remove(fallID);
	}
	/**
	 * Gibt eine Liste aller Faelle in einem bestimmten Status zurueck
	 * @param status Status
	 * @return Fälle
	 */
	public List<Fall> getFaelleOfStatus(FallStatus status) {
		return this.faelle.values()
				.stream()
				.filter(fall -> fall.getStatus() == status)
				.collect(Collectors.toList());
	}
	
}
