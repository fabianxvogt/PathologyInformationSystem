package pis.model.biopsie;

import pis.model.Fall;
import pis.model.MaterialArt;

public class Biopsie extends Fall {

	@Override
	public MaterialArt getMaterialArt() {
		return MaterialArt.Biopsie;
	}

}
