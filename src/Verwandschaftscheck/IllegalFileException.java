package Verwandschaftscheck;

import javax.swing.JFrame;

import Allgemein.Warndialog;

@SuppressWarnings("serial")
public class IllegalFileException extends Exception{
	private String message;
	
	public IllegalFileException(boolean fatal){
		message = "";
		if(fatal){
			message += "Teils ungültige";
		} else{
			message += "Veraltete";
		}
		message += " Datenbank gefunden.\n";
		if(fatal){
			message += "Ungültige Abschnitte wurden gelöscht.";
		} else{
			message += "Datenbank wurde angepasst:\nUnpassende Pferde standardmäßig männlich.";
		}
	}
	
	public void popUp(JFrame owner){
		new Warndialog(owner, message);
	}
}
