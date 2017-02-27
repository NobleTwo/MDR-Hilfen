package relativeCheck;

import javax.swing.JFrame;

import general.WarningDialog;

public class IllegalFileException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
