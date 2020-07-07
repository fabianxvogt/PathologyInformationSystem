package pis.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileWriter;
import java.time.LocalDate;
/**
 * Fall
 * @author fabia
 *
 */
public abstract class Fall {
	private int fallID;
	private Patient patient;
	private Arzt behandelnderArzt;
	private FallStatus status;
	private String fallName;
	private String fallBeschreibung;
	public Fall() {
	}
	public int getFallID() {
		return fallID;
	}
	public void setFallID(int fallID) {
		this.fallID = fallID;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Arzt getBehandelnderArzt() {
		return behandelnderArzt;
	}
	public void setBehandelnderArzt(Arzt behandelnderArzt) {
		this.behandelnderArzt = behandelnderArzt;
	}
	public FallStatus getStatus() {
		return status;
	}
	public void setStatus(FallStatus status) {
		this.status = status;
	}
	public String getFallName() {
		return fallName;
	}
	public void setFallName(String fallName) {
		this.fallName = fallName;
	}
	public String getFallBeschreibung() {
		return fallBeschreibung;
	}
	public void setFallBeschreibung(String fallBeschreibung) {
		this.fallBeschreibung = fallBeschreibung;
	}
	public String getFallIDFormatted() {
		return String.format("%08d", this.fallID);
	}	
	public abstract MaterialArt getMaterialArt();
	
	public String getAnalyse() {
		String analyse = 
				"Analyse zum Fall " + this.getFallID() + " (" + this.getMaterialArt().toString() + "): \n" + 
				"\n" +
				"DATEN ZUM FALL: " +
				"Name des Falls:  " + this.getFallName() + "\n" +
				"Beschreibung:    " + this.getFallBeschreibung()  + "\n" +
				"Material-Art:    " + this.getMaterialArt().toString()  + "\n" +
				"Patient:         " + this.getPatient().getVollenNamen() + "\n" +
				"Behandeln. Arzt: " + this.getBehandelnderArzt().getVollenNamen()  + "\n" +
				"\n" +
				this.getAnalyseDetails();
		return analyse;
	}
	protected abstract String getAnalyseDetails();
	
	public void exportJSON() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String s = null;
        try {
            s = mapper.writeValueAsString(this);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(
            		"exporte/"+
            		this.getFallIDFormatted() +"_"+ 
            		this.getMaterialArt().toString() +"_"+
            		LocalDate.now().toString() +
            		".json"
            );
            fileWriter.write(s.toString());
            fileWriter.flush();
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
	}
}
