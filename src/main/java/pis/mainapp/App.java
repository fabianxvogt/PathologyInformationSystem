package pis.mainapp;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import pis.console.ConsoleUtils;
import pis.model.Arzt;
import pis.model.PIS;

public class App {
	private static final String[] MAIN_MENU = {
			"Neue Mitarbeiter*in einstellen",
			"Mitarbeiterliste",
			"Neue Patient*in aufnehmen",
			"Neuen Fall erfassen",
			"Offene Fälle ansehen",
			"Fälle in Bearbeitung ansehen",
			"Geschlossene Fälle ansehen",
			"Beenden"
	};
	private static final ConsoleUtils C = new ConsoleUtils();
	
	private static PIS PIS = new PIS();
	
	public static void main(String[] args) {
		welcomeDialog();
		while(true) {
			int menu = C.selectChoice(MAIN_MENU);
			switch (menu) {
			case 1:
				neuerArzt();
				break;
			case 2:
				C.print(PIS.getAerzte().values().toString());
				break;
			case 8:
				return;
			}
		}
	}
	
	private static void welcomeDialog() {
		C.print("Pathologie Informationssystem");
		C.print("---------------------------------");
		C.print("");
		C.print("Von Jan Paul Peters     -s0571610");
		C.print("und Fabian Vogt         -s0570800");
		C.print("");
	}
	
	private static void neuerArzt() {
		Arzt arzt = new Arzt();
		C.print("Neue Mitarbeiter*in einstellen:");
		C.print("Nachnamen eingeben");
		arzt.setName(C.inputString(1, 40));
		C.print("Vornamen eingeben");
		arzt.setVorname(C.inputString(1, 40));
		C.print("Adresse eingeben");
		arzt.setAdresse(C.inputString(1, 100));
		C.print("Personalnummer (Arzt-ID)");
		arzt.setArztID(C.inputInt(1, 999999));
		while(true)
			try {
				PIS.addArzt(arzt);
				break;
			} catch (InvalidKeyException e) {
				C.print(e.getMessage());
			}
	}
}
