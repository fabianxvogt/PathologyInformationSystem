package pis.model;

public class Fall {
	private int fallID;
	private Patient patient;
	private Arzt behandelnderArzt;
	private MaterialArt materialArt;
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
	public MaterialArt getMaterialArt() {
		return materialArt;
	}
	public void setMaterialArt(MaterialArt materialArt) {
		this.materialArt = materialArt;
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
}
