package pis.mainapp;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import pis.console.ConsoleUtils;
import pis.model.Arzt;
import pis.model.Fall;
import pis.model.FallStatus;
import pis.model.Krankenkasse;
import pis.model.MaterialArt;
import pis.model.PIS;
import pis.model.Patient;

public class App {
	private static final String[] MAIN_MENU = {
			"Neue �rzt*in einstellen",
			"�rzteliste",
			"Neue Patient*in aufnehmen",
			"Patientenliste",
			"Neuen Fall erfassen",
			"Fall im Labor bearbeiten",
			"F�lle in Bearbeitung ansehen",
			"Geschlossene F�lle ansehen",
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
				HashMap<Integer, Arzt> aerzte = PIS.getAerzte();
				if (aerzte.size() == 0) {
					C.error("Bisher wurden keine �rzt*innen eingestellt!");
					break;
				}
				C.print(String.format("%-8s", "Pers.Nr.")+ "|" +String.format("%-20s", "Name")+ "|" +String.format("%-20s", "Vorname")+ "|" +String.format("%-40s", "Adresse"));
				C.print("-".repeat(93));
				for (Arzt a : aerzte.values())
					C.print(a.toString());
				C.print("");
				break;
			case 3:
				neuerPatient();
				break;
			case 4:
				HashMap<Integer, Patient> patienten = PIS.getPatienten();
				if (patienten.size() == 0) {
					C.error("Bisher wurden keine Patient*innen erfasst!");
					break;
				}
				C.print(String.format("%-8s", "Patient")+ "|" +String.format("%-20s", "Name")+ "|" +String.format("%-20s", "Vorname")+ "|" +String.format("%-40s", "Adresse"));
				C.print("-".repeat(93));
				for (Patient p : patienten.values())
					C.print(p.toString());
				C.print("");
				break;
			case 5:
				neuerFall();
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				return;
			}
			C.print("Dr�cken Sie 'Enter' um zur�ck zum Hauptmenu zu kommmen.");
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
		C.print("==> Neue Mitarbeiter*in einstellen:");
		C.print("->  Nachnamen eingeben");
		arzt.setName(C.inputString(1, 20));
		C.print("->  Vornamen eingeben");
		arzt.setVorname(C.inputString(1, 20));
		C.print("->  Adresse eingeben");
		arzt.setAdresse(C.inputString(1, 40));
		arzt.setArztID(PIS.getAerzte().size()+1);
		try {
			PIS.addArzt(arzt);
			C.print("==> Neue �rzt*in eingestellt! (Personalnummer: " + arzt.getArztIDFormatted() + ")");
		} catch (InvalidKeyException e) {
			C.error(e.getMessage());
		}
	}
	
	private static Patient neuerPatient() {
		Patient patient = new Patient();
		C.print("==> Neue Patient*in aufnehmen:");
		C.print("->  Nachnamen eingeben");
		patient.setName(C.inputString(1, 20));
		C.print("->  Vornamen eingeben");
		patient.setVorname(C.inputString(1, 20));
		C.print("->  Adresse eingeben");
		patient.setAdresse(C.inputString(1, 40));
		C.print("->  Krankenkasse ausw�hlen");
		String[] krankenkassenChoices = new String[Krankenkasse.values.length];
		for (int i = 0; i < Krankenkasse.values.length; i++)
			krankenkassenChoices[i] = Krankenkasse.values[i].toString();
		patient.setKrankenkasse(Krankenkasse.values[C.selectChoice(krankenkassenChoices)-1]);
		patient.setPatientenID(PIS.getPatienten().size()+1);
		try {
			PIS.addPatient(patient);
			C.print("==> Neue Patient*in aufgenommen! Patienten-ID: "  + patient.getPatientIDFormatted() + ")");
			return patient;
		} catch (InvalidKeyException e) {
			C.error(e.getMessage());
			return null;
		}
	}

	private static void neuerFall() {
		C.print("==> Neuen Fall erfassen");
		// schauen ob mindestens ein Arzt vorhanden ist
		HashMap<Integer, Arzt> aerzte = PIS.getAerzte();
		if (aerzte.size() == 0) {
			C.error("Bisher wurde kein Arzt eingestellt!");
			return;
		}
		// Neuen Fall erzeugen
		Fall f = new Fall();
		C.print("->  Name des Falls");
		f.setFallName(C.inputString(1, 20));
		C.print("->  Beschreibung (optional)");
		f.setFallBeschreibung(C.inputString(0, 100));
		// Material-Art setzen
		C.print("->  Material-Art ausw�hlen");
		f.setMaterialArt(
				MaterialArt.values[
				                   C.selectChoice(
				                		   new String[] {MaterialArt.Biopsie.toString(), MaterialArt.Resektat.toString()}
				                		   )-1
				                   ]);
		// Patienten ausw�hlen
		C.print("->  Patient*in zuordnen");
		Patient patient;
		// Neuer Patient oder bereits erfasst?
		int opt = C.selectChoice(new String[] {
				"Neue Patient*in erfassen", 
				"Bereits erfasste Patient*in ausw�hlen"
				});
		if (opt == 1 )
			patient = neuerPatient();	
		else {
			HashMap<Integer, Patient> patienten = PIS.getPatienten();
			if (patienten.size() == 0) {
				C.error("Bisher wurde kein Patient erfasst!");
				patient = neuerPatient();
			} else {
				String[] patientChoices = new String[patienten.size()];
				for (int i = 0; i < patienten.size(); i++)
					patientChoices[i] = patienten.get(i+1).getName() + ", " + patienten.get(i+1).getVorname();
				patient = patienten.get(C.selectChoice(patientChoices));
			}
			
		}
		f.setPatient(patient);
		C.print("->  Patient*in '" + patient.getName() + ", " + patient.getVorname() + "' wurde dem Fall zugeordnet!");
		C.print("");
		// Arzt zuweisen
		C.print("Behandelden Arzt ausw�hlen");
		String[] arztChoices = new String[aerzte.size()];
		for (int i = 0; i < aerzte.size(); i++)
			arztChoices[i] = aerzte.get(i+1).getName() + ", " + aerzte.get(i+1).getVorname();
		f.setBehandelnerArzt(aerzte.get(C.selectChoice(arztChoices)));
		
		// Neue Fall-ID generieren
		f.setFallID(PIS.getFaelle().size()+1);
		// Fall Status setzen
		f.setStatus(FallStatus.NEU);
		
		// Fall hinzuf�gen
		try {
			PIS.addFall(f);
		} catch (InvalidKeyException e) {
			C.error(e.getMessage());
		}
		C.print("==> Neuer Fall wurde erfasst! (ID: " + f.getFallIDFormatted() + ")");
	}
	
	private static void fallBearbeiten() {
		// Unbearbeitete F�lle holen
		HashMap<Integer, Fall> faelle = PIS.getFaelle();
		HashMap<Integer, Fall> offen = new HashMap<Integer, Fall>();
		for (Fall f : faelle.values())
			if (f.getStatus() == FallStatus.NEU)
				offen.put(f.getFallID(), f);
		// Auswahlliste erstellen
		String[] fallChoices = new String[offen.size()];
		for (int i = 0; i < offen.size(); i++) 
			fallChoices[i] = offen.get(i+1).getFallID() + ", " + 
								offen.get(i+1).getFallName();
		Fall f = offen.get(C.selectChoice(fallChoices));
	}
}
