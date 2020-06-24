package pis.mainapp;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import pis.console.ConsoleUtils;
import pis.model.Arzt;
import pis.model.PIS;
import pis.model.Patient;

public class App {
	private static final String[] MAIN_MENU = {
			"Neue Ärzt*in einstellen",
			"Ärzteliste",
			"Neue Patient*in aufnehmen",
			"Patientenliste",
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
			C.print("---------------- Hauptmenu --------------");
			int menu = C.selectChoice(MAIN_MENU);
			switch (menu) {
			case 1:
				neuerArzt();
				break;
			case 2:
				C.print(PIS.getAerzte().values().toString());
				break;
			case 3:
				neuerPatient();
				break;
			case 4:
				C.print(PIS.getPatienten().values().toString());
				break;
			case 8:
				return;
			}
			C.print("Drücken Sie 'Enter' um zurück zum Hauptmenu zu kommmen.");
			C.pressEnter();
		}
	}
	


	private static void welcomeDialog() {
		C.print("             _,.-------------.._            ");
		C.print("          ,-'        j          `-.         ");
		C.print("        ,'        .-'               `.      ");
		C.print("       /          |    PATHOLOGIE     '     ");
		C.print("      /         ,-'                    `    ");
		C.print("     .         j   INFORMATIONSSYSTEM    \\  ");
		C.print("    .          |                          \\ ");
		C.print("    : ._       |   _....._                 .");
		C.print("    |   -.     L-''       `.               :");
		C.print("    | `.  \\  .'             `.             |");
		C.print("   /.\\  `, Y'                 :           ,|");
		C.print("  /.  :  | \\                  |         ,' |");
		C.print(" \\.    * :  `\\                |      ,--   |");
		C.print("  \\    .'     '-..___,..      |    _/      :");
		C.print("   \\  `.      ___   ...._     '-../        '");
		C.print(" .-'    \\    /| \\_/ | | |      ,'         / ");
		C.print(" |       `--' |    '' |'|     /         .'  ");
		C.print(" |            |      /. |    /       _,'    ");
		C.print(" |-.-.....__..|     Y-dp`...:...--'''       ");
		C.print(" |_|_|_L.L.T._/     |                       ");
		C.print(" \\_|_|_L.T-''/      |  Von Jan Paul Peters  ");
		C.print("  |                /         	   s0571610 ");
		C.print(" /             _.-'                         ");
		C.print(" :         _..'        und Fabian Vogt     ");
		C.print(" \\__...--''                        s0570800  ");
		C.print("");
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
				C.print("Neue Mitarbeiter*in '" + arzt.getArztID() + "' eingestellt!");
				break;
			} catch (InvalidKeyException e) {
				C.error(e.getMessage());
				C.error("Bitte wählen Sie eine andere Personalnummer");
				C.print("Personalnummer (Arzt-ID)");
				arzt.setArztID(C.inputInt(1, 999999));
				continue;
			}
	}
	
	private static void neuerPatient() {
		Patient patient = new Patient();
		C.print("Neue Patient*in aufnehmen:");
		C.print("Nachnamen eingeben");
		patient.setName(C.inputString(1, 40));
		C.print("Vornamen eingeben");
		patient.setVorname(C.inputString(1, 40));
		C.print("Adresse eingeben");
		patient.setAdresse(C.inputString(1, 100));
		C.print("Patienten-ID eingeben");
		patient.setPatientenID(C.inputInt(1, 999999));
		while(true)
			try {
				PIS.addPatient(patient);
				C.print("Neue Patient*in '" + patient.getPatientenID() + "' aufgenommen!");
				break;
			} catch (InvalidKeyException e) {
				C.error(e.getMessage());
				C.error("Bitte wählen Sie eine andere Patienten-ID!");
				C.print("Patienten-ID eingeben");
				patient.setPatientenID(C.inputInt(1, 999999));
				continue;
			}
	}
}
