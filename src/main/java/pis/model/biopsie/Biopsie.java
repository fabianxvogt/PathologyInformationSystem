package pis.model.biopsie;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import pis.model.Fall;
import pis.model.MaterialArt;

public class Biopsie extends Fall {
	public Biopsie() {
		super();
		this.schnitte = new ArrayList<Color>();
	}

	private List<Color> schnitte;
	@Override
	public MaterialArt getMaterialArt() {
		return MaterialArt.Biopsie;
	}
	public void schnittErzeugen(Color c) {
		this.schnitte.add(c);
	}
	@Override
	protected String getAnalyseDetails() {
		String analyse =
				"SCHNITTE: " + "\n" +
				"Anzahl Schnitte: " + this.schnitte.size() + "\n";
		for (int i = 0; i < this.schnitte.size(); i++) {
			analyse += "Farbe Schnitt " + (i+1) + ": " + this.schnitte.get(i) + "\n";
		}
		return analyse;
	}

}
