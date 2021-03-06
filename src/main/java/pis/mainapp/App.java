package pis.mainapp;

import pis.console.ConsoleUtils;
import pis.model.*;
import pis.model.biopsie.Biopsie;
import pis.model.resektat.*;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.List;
/**
 * Main App
 * @author fabia
 *
 */
public class App {
	private static final String[] MAIN_MENU = {
			"Neue Aerzt*in einstellen",
			"Aerzteliste",
			"Neue Patient*in aufnehmen",
			"Patientenliste",
			"Neuen Fall erfassen",
			"Fall im Labor bearbeiten",
			"Fall exportieren",
			"Fall analysieren",
			"Beenden"
	};
	private static final String[] SCHNITT_FARBEN = {
			"HE",
			"PHH3",
			"PD-L1",
			"Fuchsin",
			"PAP"
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
					C.error("Bisher wurden keine Aerzt*innen eingestellt!");
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
					C.error("->  Bisher wurden keine Patient*innen erfasst!");
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
			C.print("==> Druecken Sie 'Enter' um zurueck zum Hauptmenu zu kommmen.");
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
	/**
	 * Neuen Arzt einstellen
	 */
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
			C.print("==> Neue Aerzt*in eingestellt! (Personalnummer: " + arzt.getArztIDFormatted() + ")");
		} catch (InvalidKeyException e) {
			C.error(e.getMessage());
		}
	}
	/**
	 * Neuen Patienten erfassen
	 * @return Patient
	 */
	private static Patient neuerPatient() {
		Patient patient = new Patient();
		C.print("==> Neue Patient*in aufnehmen:");
		C.print("->  Nachnamen eingeben");
		patient.setName(C.inputString(1, 20));
		C.print("->  Vornamen eingeben");
		patient.setVorname(C.inputString(1, 20));
		C.print("->  Adresse eingeben");
		patient.setAdresse(C.inputString(1, 40));
		C.print("->  Krankenkasse auswaehlen");
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
	/**
	 * Neuen Fall erfassen
	 */
	private static void neuerFall() {
		C.print("==> Neuen Fall erfassen");
		// schauen ob mindestens ein Arzt vorhanden ist
		HashMap<Integer, Arzt> aerzte = PIS.getAerzte();
		if (aerzte.size() == 0) {
			C.error("->  Bisher wurde kein Arzt eingestellt!");
			return;
		}
		Fall f;
		// Material-Art auswaehlen (bestimmt welche Fallart erzeugt wird)
		C.print("->  Material-Art auswaehlen");
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
		
		// Patienten auswaehlen
		C.print("->  Patient*in zuordnen");
		Patient patient;
		// Neuer Patient oder bereits erfasst?
		int opt = C.selectChoice(new String[] {
				"Neue Patient*in erfassen", 
				"Bereits erfasste Patient*in auswaehlen"
				});
		if (opt == 1 )
			patient = neuerPatient();	
		else {
			HashMap<Integer, Patient> patienten = PIS.getPatienten();
			if (patienten.size() == 0) {
				C.error("->  Bisher wurde kein Patient erfasst!");
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
		C.print("->  Behandelden Arzt auswaehlen");
		String[] arztChoices = new String[aerzte.size()];
		for (int i = 0; i < aerzte.size(); i++)
			arztChoices[i] = aerzte.get(i+1).getName() + ", " + aerzte.get(i+1).getVorname();
		f.setBehandelnderArzt(aerzte.get(C.selectChoice(arztChoices)));
		
		// Neue Fall-ID generieren
		f.setFallID(PIS.getFaelle().size()+1);
		// Fall Status setzen
		f.setStatus(FallStatus.NEU);
		
		// Fall hinzufuegen
		try {
			PIS.addFall(f);
		} catch (InvalidKeyException e) {
			C.error(e.getMessage());
		}
		C.print("==> Neuer Fall wurde erfasst! (ID: " + f.getFallIDFormatted() + ")");
	}
	/**
	 * Fall mit status 'neu' bearbeiten
	 */
	private static void fallBearbeiten() {
		C.print("==> Unbearbeiteten Fall auswaehlen");
		Fall f = fallAuswaehlen(PIS.getFaelleOfStatus(FallStatus.NEU));
		if (f==null) {
			C.error("->  Es wurden keine Faelle mit dem Status 'Neu' gefunden!");
			return;
		}
		f.setStatus(FallStatus.IN_BEARBEITUNG);
		C.print("->  Fall " + f.getFallIDFormatted() + " wird in Bearbeitung genommen...");
		switch (f.getMaterialArt()) {
		case Biopsie:
			biopsieDokumentieren((Biopsie)f);
			break;
		case Resektat:
			resektatDokumentieren((Resektat)f);
		default:
			break;
		}
	}
	/**
	 * Falls als JSON exportieren
	 */
	private static void fallExportieren() {
		C.print("==> Fall in Bearbeitung auswaehlen");
		Fall f = fallAuswaehlen(PIS.getFaelleOfStatus(FallStatus.IN_BEARBEITUNG));
		if (f==null) {
			C.error("->  Es wurden keine Faelle mit dem Status 'In Bearbeitung' gefunden!");
			return;
		}
		f.setStatus(FallStatus.IN_BEARBEITUNG);
		C.print("->  Fall " + f.getFallIDFormatted() + " wird in .JSON File exportiert");
		f.exportJSON();
		C.print("==> Fall erfolgreich Exportiert!");

	}
	/**
	 * Bearbeiteten Fall analysieren
	 */
	private static void fallAnalysieren() {
		C.print("==> Fall in Bearbeitung auswaehlen");
		Fall f = fallAuswaehlen(PIS.getFaelleOfStatus(FallStatus.IN_BEARBEITUNG));
		if (f==null) {
			C.error("->  Es wurden keine Faelle mit dem Status 'In Bearbeitung' gefunden!");
			return;
		}	
		C.print(f.getAnalyse());
	}
	/**
	 * Auswahl eines Falls in der Console
	 * @param faelle F�lle zur Auswahl
	 * @return ausgew�hlter Fall
	 */
	private static Fall fallAuswaehlen(List<Fall> faelle) {
		if(faelle.size() == 0)
			return null;
		// Auswahlliste erstellen
		String[] fallChoices = new String[faelle.size()];
		for (int i = 0; i < faelle.size(); i++) 
			fallChoices[i] = faelle.get(i).getFallIDFormatted() + ", " + 
					faelle.get(i).getFallName();
		return faelle.get(C.selectChoice(fallChoices)-1);
	}
	/**
	 * Dokumentation des Resektats
	 * @param r zu dokumentierendes Resektat
	 */
	private static void resektatDokumentieren(Resektat r) {
		C.print("==> Resektat dokumentieren");
		C.print("->  Geben Sie das Gewicht der Prostata in Gramm an:");
		r.setGewicht(C.inputDouble(1, 1000, 3));
		C.print("->  Geben Sie die apiko-basale Laenge in Millimetern an:");
		r.setApikoBasal(C.inputDouble(1, 1000, 3));
		C.print("->  Geben Sie die horizontale Laenge in Millimetern an:");
		r.setHorizontal(C.inputDouble(1, 1000, 3));
		C.print("->  Geben Sie die antero-dorsale Laenge in Millimetern an:");
		r.setAnteroDorsal(C.inputDouble(1, 1000, 3));
		
		C.print("==> Prostata-Eigenschaften erfasst! Beginnen Sie mit dem Zuschnitt.");
		C.print("->  Schneiden Sie den Apex ab und zerteilen Sie ihn in 2 Haelften...");
		C.print("->  Anschliessend zerschneiden Sie die beiden Teilhaelften in beliebig viele St�cke.");
		C.print("->  Fortfahren (Enter)");
		C.pressEnter();
		
		C.print("->  Beginne mit der Erfassung der Objekttraeger fuer den Apex...");
		r.setApex(new Apex());
		objekttraegerErfassen(r.getApex(), true);
		objekttraegerErfassen(r.getApex(), false);
		
		C.print("==> Apex-Zuschnitt wurde erfasst! Nun schneiden Sie die Prostata bis zur Basis in Scheiben...");
		C.print("->  Wie viele Scheiben wurden erzeugt?");
		int anzahlScheiben = C.inputInt(1, 10);
		C.print("==> Anzahl der Scheiben wurde erfasst. Nun zerteilen Sie alle Scheiben in Haelften...");
		C.print("->  Anschliessend zerschneiden Sie die Teilhaelften jeder Scheibe in beliebig viele Stuecke.");
		C.print("->  Fortfahren (Enter)");
		C.pressEnter();
		
		for (int i = 0; i < anzahlScheiben; i++) {
			C.print("->  Beginne mit der Erfassung der Objekttraeger fuer Scheibe " + (i+1) + "...");
			Scheibe s = new Scheibe();
			objekttraegerErfassen(s, true);
			objekttraegerErfassen(s, false);
			C.print("==> Zuschnitt von Scheibe " + (i+1) + " wurde erfasst!");
			r.getScheiben().add(s);
		}
		C.print("==> Scheiben wurden erfasst! Zuletzt zerteilen Sie die Basis in 2 Haelften.");
		C.print("->  Anschliessend zerschneiden Sie die Teilhaelften in beliebig viele Stuecke.");
		C.print("->  Fortfahren (Enter)");
		C.pressEnter();
		
		C.print("->  Beginne mit der Erfassung der Objekttraeger fuer die Basis...");
		r.setBasis(new Basis());
		objekttraegerErfassen(r.getBasis(), true);
		objekttraegerErfassen(r.getBasis(), false);
		C.print("==> Basis-Zuschnitt wurde erfasst!");
		C.print("==> Resektat wurde im Fall " + r.getFallIDFormatted() + " dokumentiert!");
	}
	/**
	 * Dokumentation der Biospie
	 * @param b zu dokumentierende Biopsie
	 */
	private static void biopsieDokumentieren(Biopsie b) {
		C.print("==> Biopsie dokumentieren");
		C.print("->  Wie viele Schnitte wurden erzeugt?");
		int anzahlSchnitte = C.inputInt(1, 10);
		for (int i = 0; i < anzahlSchnitte; i++) {
			C.print("->  Farbe fuer Schnitt " + (i+1) + " festlegen");
			b.schnittErzeugen(SCHNITT_FARBEN[C.selectChoice(SCHNITT_FARBEN)-1]);
		}
		C.print("==> Schnitte wurden erfasst!");
		C.print("==> Biopsie wurde im Fall " + b.getFallIDFormatted() + " dokumentiert!");
	}
	/**
	 * Objekttr�ger f�r Scheibe erfassen
	 * @param s
	 * @param istRechteHaelfte
	 */
	private static void objekttraegerErfassen(Scheibe s, boolean istRechteHaelfte) {
		// Information um welche Haelfte es geht (fuer console output)
		String haelfteInfo;
		if(istRechteHaelfte)
			haelfteInfo = "rechte Haelfte";
		else
			haelfteInfo = "linke Haelfte";
		// Wie viel Stuecke wurden erzeugt?
		C.print("->  In wie viele Stuecke haben Sie die "+haelfteInfo+" zerteilt?");
		int anzahl = C.inputInt(1, 10);
		// Objekttraeger erfassen
		C.print("->  Geben Sie die Objekttraeger fuer die einzelnen Stuecke an:");
		for (int i = 0; i < anzahl; i++) {
			C.print("->  Objekttraeger fuer Stueck "+(i+1)+" ("+haelfteInfo+"):");
			char objektTraeger = C.inputChar();
			if(istRechteHaelfte)
				s.addStueckRechts(new Stueck(objektTraeger));
			else 
				s.addStueckLinks(new Stueck(objektTraeger));
		}
		C.print("==> Objekttraeger wurden fuer die "+haelfteInfo+" erfasst!");
	}
}
