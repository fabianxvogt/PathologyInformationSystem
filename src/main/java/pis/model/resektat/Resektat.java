package pis.model.resektat;

import java.util.ArrayList;
import java.util.List;

import pis.model.Fall;
import pis.model.MaterialArt;

public class Resektat extends Fall {

	private double gewicht, apikoBasal, horizontal, anteroDorsal;
	private Apex apex;
	private Basis basis;
	private List<Scheibe> scheiben;
	
	public Resektat() {
		super();
		this.scheiben = new ArrayList<Scheibe>();
	}
	@Override
	public MaterialArt getMaterialArt() {
		return MaterialArt.Resektat;
	}
	public Apex getApex() {
		return apex;
	}
	public void setApex(Apex apex) {
		this.apex = apex;
	}
	public Basis getBasis() {
		return basis;
	}
	public void setBasis(Basis basis) {
		this.basis = basis;
	}
	public List<Scheibe> getScheiben() {
		return scheiben;
	}
	public void setScheiben(List<Scheibe> scheiben) {
		this.scheiben = scheiben;
	}
	public double getGewicht() {
		return gewicht;
	}
	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}
	public double getApikoBasal() {
		return apikoBasal;
	}
	public void setApikoBasal(double apikoBasal) {
		this.apikoBasal = apikoBasal;
	}
	public double getHorizontal() {
		return horizontal;
	}
	public void setHorizontal(double horizontal) {
		this.horizontal = horizontal;
	}
	public double getAnteroDorsal() {
		return anteroDorsal;
	}
	public void setAnteroDorsal(double anteroDorsal) {
		this.anteroDorsal = anteroDorsal;
	}

}
