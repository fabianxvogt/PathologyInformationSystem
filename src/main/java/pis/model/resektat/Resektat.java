package pis.model.resektat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pis.model.Fall;
import pis.model.MaterialArt;
/**
 * Resektat
 * @author fabia
 *
 */
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
	protected String getAnalyseDetails() {
		String analyse =
				"DATEN ZUM RESEKTAT: \n" + 
				"Gewicht:             " + this.gewicht + "\n" +
				"Apiko-Basal:         " + this.apikoBasal + "\n" +
				"Horizontal:          " + this.horizontal + "\n" +
				"Antero-Dorsal:       " + this.anteroDorsal + "\n" +
				"Anzahl der Scheiben: " + this.scheiben.size() + "\n" +
				// Durchschnittliche Dicke pro Scheibe = ApikoBasal / (AnzahlScheiben+2)
				// Apex und Basis werden einzeln erfasst und zählen nicht als Scheibe. Daher: AnzahlScheiben+2
				"Durchschn. Dicke:    " + this.apikoBasal / (this.scheiben.size()+2) +
				"\n";
		analyse="OBJEKTTRAEGER:" + "\n" +
				"Apex: " + "\n" +
				getObjekttraegerString(this.getApex()) + "\n" +
				"Scheiben: " + "\n";
		for (Scheibe s : this.getScheiben()) {
			analyse+=getObjekttraegerString(s) + "\n";
		}
		analyse += "Basis: " + "\n" +
				getObjekttraegerString(this.getBasis()) + "\n" +
				"Anzahl Objekttraeger: " + this.getObjektTraeger().size() + "\n";
		return analyse;
	}
	private static String getObjekttraegerString(Scheibe s) {
		String r = new String();
		//rechte seite
		for (int i = 0; i < 10; i++) {
			if(s.getRechteStuecke().size() < i+1)
				r+="  ";
			else
				r+=(s.getRechteStuecke().get(i).getObjektTraeger() + " ");
		}
		// Trenner
		r+=" | ";
		// linke seite
		for (int i = 0; i < 10; i++) {
			if(s.getLinkeStuecke().size() < i+1)
				r+="  ";
			else
				r+=(s.getLinkeStuecke().get(i).getObjektTraeger() + " ");
		}
		return r;
	}
	public List<Character> getObjektTraeger() {
		// Objekttraeger der Scheiben sammeln
		List<Character> objektraegerScheiben = new ArrayList<Character>();
		for (Scheibe s : this.scheiben) 
			objektraegerScheiben.addAll(s.getObjekttraeger());
		// Eindeutige Objekttraeger von Scheiben, Apex und Basis zurueckgeben
		return Stream.of(this.apex.getObjekttraeger(), 
							this.basis.getObjekttraeger(), 
							objektraegerScheiben)
    			.flatMap(Collection::stream)
    			.distinct()
    			.collect(Collectors.toList());
	}
}
