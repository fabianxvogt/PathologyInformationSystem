package pis.model.biopsie;

import java.util.ArrayList;
import java.util.List;

import pis.model.Fall;
import pis.model.MaterialArt;

public class Biopsie extends Fall {

	private List<String> schnittFarben;
	public Biopsie() {
		super();
		this.schnittFarben = new ArrayList<String>();
	}

	@Override
	public MaterialArt getMaterialArt() {
		return MaterialArt.Biopsie;
	}
	public void schnittErzeugen(String farbe) {
		this.schnittFarben.add(farbe);
	}
	public List<String> getSchnitte() {
		return this.schnittFarben;
	}
	@Override
	protected String getAnalyseDetails() {
		String analyse =
				"DATEN ZUR BIOPSIE" + "\n" +
				"SCHNITTE: " + "\n" +
				"Anzahl Schnitte: " + this.schnittFarben.size() + "\n";
		for (int i = 0; i < this.schnittFarben.size(); i++) {
			analyse += "Farbe Schnitt " + (i+1) + ": " + this.schnittFarben.get(i) + "\n";
		}
		return analyse;
	}

}
