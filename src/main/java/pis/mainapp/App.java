package pis.mainapp;

import java.security.InvalidKeyException;
import java.util.HashMap;

import pis.console.ConsoleUtils;
import pis.model.Arzt;
import pis.model.Biopsie;
import pis.model.Fall;
import pis.model.FallStatus;
import pis.model.Krankenkasse;
import pis.model.MaterialArt;
import pis.model.PIS;
import pis.model.Patient;
import pis.model.Resektat;

public class App {
	private static final String[] MAIN_MENU = {
			"Neue �rzt*in einstellen",
			"�rzteliste",
			"Neue Patient*in aufnehmen",
			"Patientenliste",
			"Neuen Fall erfassen",
			"Fall im Labor bearbeiten",
			"Fall exportieren",
			"Fall analysieren",
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
				fallBearbeiten();
				break;
			case 7:
				fallExportieren();
				break;
			case 8:
				fallAnalysieren();
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
		Fall f;
		// Material-Art ausw�hlen (bestimmt welche Fallart erzeugt wird)
		C.print("->  Material-Art ausw�hlen");
		switch (MaterialArt.values[
		                           C.selectChoice(
		                        		   new String[] {MaterialArt.Biopsie.toString(), 
		                        				   MaterialArt.Resektat.toString()}
				                		   )-1
				                   ]) {
		case Biopsie:
			f = new Biopsie();
			break;
		case Resektat:
			f = new Resektat();
			break;
		default:
			f = new Biopsie();
			break;
		}
		C.print("->  Name des Falls");
		f.setFallName(C.inputString(1, 20));
		C.print("->  Beschreibung (optional)");
		f.setFallBeschreibung(C.inputString(0, 100));
		// Material-Art setzen
		
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
		f.setBehandelnderArzt(aerzte.get(C.selectChoice(arztChoices)));
		
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
		C.print("Unbearbeiteten Fall ausw�hlen");
		Fall f = fallAusw�hlen(getFaelleOfStatus(FallStatus.NEU));
		if (f==null) {
			C.error("Es wurden keine F�lle mit dem Status 'Neu' gefunden!");
			return;
		}
			
		switch (f.getMaterialArt()) {
		case Biopsie:
			// Wie viele Schnitte wurden erzeugt?
			
			// for each Schnitt in Schnitte
			// Wie wurde Schnitt gef�rbt?
			
			break;
		case Resektat:
			// Zuschnitt dokumentieren
			
			// Bl�cke aus Apex oder Basis 
			// Scheiben und Unterteilungen erfassen
			break;
		}
	}
	
	private static void fallExportieren() {
		C.print("Fall in Bearbeitung ausw�hlen");
		Fall f = fallAusw�hlen(getFaelleOfStatus(FallStatus.IN_BEARBEITUNG));
		if (f==null) {
			C.error("Es wurden keine F�lle mit dem Status 'In Bearbeitung' gefunden!");
			return;
		}
	}

	private static void fallAnalysieren() {
		C.print("Fall in Bearbeitung ausw�hlen");
		Fall f = fallAusw�hlen(getFaelleOfStatus(FallStatus.IN_BEARBEITUNG));
		if (f==null) {
			C.error("Es wurden keine F�lle mit dem Status 'In Bearbeitung' gefunden!");
			return;
		}		
	}
	
	private static Fall fallAusw�hlen(HashMap<Integer, Fall> faelle) {
		if(faelle.size() == 0)
			return null;
		// Auswahlliste erstellen
		String[] fallChoices = new String[faelle.size()];
		for (int i = 0; i < faelle.size(); i++) 
			fallChoices[i] = faelle.get(i+1).getFallID() + ", " + 
					faelle.get(i+1).getFallName();
		return faelle.get(C.selectChoice(fallChoices));
	}
	private static HashMap<Integer, Fall> getFaelleOfStatus(FallStatus status) {
		HashMap<Integer, Fall> faelle = new HashMap<Integer, Fall>();
		for (Fall f : PIS.getFaelle().values())
			if (f.getStatus() == status)
				faelle.put(f.getFallID(), f);
		return faelle;
	}
}
