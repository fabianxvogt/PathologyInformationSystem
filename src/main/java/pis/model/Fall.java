package pis.model;

public class Fall {
	private int fallID;
	private Patient patient;
	private Arzt behandelnerArzt;
	private MaterialArt materialArt;
	private FallStatus status;
	public Fall(int fallID) {
		this.fallID = fallID;
	}
	public int getFallID() {
		return fallID;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Arzt getBehandelnerArzt() {
		return behandelnerArzt;
	}
	public void setBehandelnerArzt(Arzt behandelnerArzt) {
		this.behandelnerArzt = behandelnerArzt;
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
}
