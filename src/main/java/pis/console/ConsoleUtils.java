package pis.console;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;


public class ConsoleUtils {
	
	private Scanner s;
	public ConsoleUtils() {
		s = new Scanner(System.in);
	}
	
	public int selectChoice(String[] choices) {
		if (choices.length==0)
			return 0;
		for (int i = 0; i < choices.length; i++)
			print((i+1) + ". " + choices[i]);
		print("Eingabe (1-" + choices.length + "):");
		while(true) {
			String ans = s.nextLine();
			try {
				int i = Integer.parseInt(ans);
				if (i < 1 || i > choices.length) {
					error("Bitte wählen Sie eine Zahl zwischen 1 und " + choices.length);
					continue;
				}
				return i;
			} catch (NumberFormatException e) {
				error("Nur Zahlenwerte erlaubt!");
				continue;
			}
		}
	}
	public boolean inputBool() {
		print("Eingabe (J = Ja / N = Nein):");
		String ans = s.nextLine();
		if (ans.toUpperCase().equals("J"))
			return true;
		else if (ans.toUpperCase().equals("N"))
			return false;
		error("Ungültige Eingabe! Wählen Sie J (Ja) oder N (Nein)!");
		return inputBool();
	}
	public int inputInt(int min, int max) {
		if (min > max)
			return 0;
		print("Eingabe (" + min + " - " + max + "):");
		String ansStr = s.nextLine();
		try {
			int ans = Integer.parseInt(ansStr);
			if (ans < min) {
				error("Eingabe zu klein (Minimum: " + min + ")");
				return inputInt(min, max);
			} else if (ans > max) {
				error("Eingabe zu groß (Maximum: " + max + ")");
				return inputInt(min, max);
			}
			return ans;
		} catch (NumberFormatException e) {
			error("Geben Sie eine Zahl ein!");
			return inputInt(min, max);
		}
	}
	public char inputChar(char[] allowed) {
		print("Eingabe " + Arrays.toString(allowed) + ":");
		String ans = s.nextLine();
		if (ans.length() == 1 && new String(allowed).toUpperCase().contains(ans.toUpperCase()))
			return ans.toUpperCase().charAt(0);
		error("Geben Sie genau ein Zeichen ein! Erlaubt: " + Arrays.toString(allowed));
		return inputChar(allowed);
	}
	public char inputChar() {
		print("Eingabe (Zeichen):");
		String ans = s.nextLine();
		if (ans.length() == 1)
			return ans.toUpperCase().charAt(0);
		error("Geben Sie genau ein Zeichen ein!");
		return inputChar();
	}
	public double inputDouble(double min, double max, int decimalPlaces) {
		print("Eingabe (" + decimalPlaces + " Dezimalstellen):" );
		String ansStr = s.nextLine();
		try {
			double ans = Double.parseDouble(ansStr);
			if (ans < min) {
				error("Eingabe zu klein (Minimum: " + min + ")");
				return inputDouble(min, max, decimalPlaces);
			} else if (ans > max) {
				error("Eingabe zu groß (Maximum: " + max + ")");
				return inputDouble(min, max, decimalPlaces);
			}
			if ((ans % 1) != 0 && ansStr.split("\\.")[1].length() > decimalPlaces) {
				error("Zu viele Nachkommastellen!");
				return inputDouble(min, max, decimalPlaces);
			}
			return ans;
			
		} catch (NumberFormatException e) {
			error("Geben Sie eine Zahl ein!");
			return inputDouble(min, max, decimalPlaces);
		}
	}
	public String inputString(int minLength, int maxLength) {
		print("Eingabe: ");
		String ans = s.nextLine();
		if(ans.length() > maxLength) {
			error("Maximal " + maxLength + " Zeichen erlaubt!");
			return inputString(minLength, maxLength);
		} else if (ans.length() < minLength) {
			error("Mindestens " + minLength + " Zeichen eingeben!");
			return inputString(minLength, maxLength);
		}
		return ans;
	}
	public LocalDate inputDate() {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
		print("Datum eingeben (" + pattern + "):");
		String ans = s.nextLine();
		LocalDate date;
		try {
			date = LocalDate.parse(ans, format);
			if (date != null)
				return date;
			error("Ungültiges Format!");
			return inputDate();
		} catch (DateTimeParseException e) {
			error("Ungültiges Format!");
			return inputDate();
		}		
	}
	public void print(String s) {
		System.out.println(s);
	}
	public void error(String s) {
		System.err.println(s);
	}
	public boolean confirm() {
		print("Sind Sie sicher?");
		return inputBool();
		//String ans = s.nextLine();
		//if (ans.toUpperCase().equals("J"))
		//	return true;
		//else if (ans.toUpperCase().equals("N"))
		//	return false;
		//error("Ungültige Eingabe! Wählen sie 'J' (Ja) oder 'N' (Nein).");
		//return confirm();
	}
	public void pressEnter() {
		s.nextLine();
	}
	
}
