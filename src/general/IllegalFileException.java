package general;

import javax.swing.JFrame;

import general.WarningDialog;

@SuppressWarnings("serial")
public class IllegalFileException extends Exception {
	private String message;

	public IllegalFileException(boolean fatal) {
		message = "";
		if (fatal) {
			message += "Teils ung�ltige";
		} else {
			message += "Veraltete";
		}
		message += " Datenbank gefunden.\n";
		if (fatal) {
			message += "Ung�ltige Abschnitte wurden gel�scht.";
		} else {
			message += "Datenbank wurde angepasst:\nUnpassende Pferde standardm��ig m�nnlich.";
		}
	}

	public void popUp(JFrame owner) {
		new WarningDialog(owner, message);
	}
}
