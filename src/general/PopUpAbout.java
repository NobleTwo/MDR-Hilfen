package general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class PopUpAbout extends MDRDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PopUpAbout(MainMenu mainMenuFrame) {
		// Frame-Initialisierung
		super(mainMenuFrame, "Wird die Datenbank nicht gefunden?");
		// Komponenten
		final int widthLabel = 355;

		JTextArea helpText = new JTextArea("Die Datenbank für MDR-Hilfen ist eine versteckte Datei. Diese können im \nWindows-Explorer unter \"Ansicht \u2192 Ausgeblendete Elemente\" angezeigt \nwerden.");
		helpText.setBounds(gridButtonGap, yTop, widthLabel, 50);
		helpText.setFont(fontLabel);
		helpText.setLineWrap(true);
		helpText.setOpaque(false);
		helpText.setEditable(false);
		helpText.setHighlighter(null);
		cp.add(helpText);

		JLabel labelVersionInfo = new JLabel("Versionsinformationen");
		labelVersionInfo.setBounds(gridButtonGap, 80, widthLabel, 19);
		labelVersionInfo.setHorizontalAlignment(SwingConstants.CENTER);
		cp.add(labelVersionInfo);

		JTextPane versionText = new JTextPane();
		versionText.setText("MDR-Hilfen Ver. 1.2.1\nRelease: 05.03.2017\nBasierend auf MDR, Version 3.0, Stand 05.03.2017\nEs können Fehler bei Verwendung mit anderen MDR-Versionen auftreten.\n\u00A9 nola Linys Freund, 2014-2017.");
		versionText.setBounds(gridButtonGap, 100, widthLabel, 70);
		versionText.setFont(fontLabel);
		versionText.setEditable(false);
		versionText.setHighlighter(null);
		versionText.setOpaque(false);

		// for centering text
		StyledDocument doc = versionText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		cp.add(versionText);

		MDRButton buttonClose = new MDRButton("Schließen");
		buttonClose.setBounds(144, 175, 105, buttonHeight);
		buttonClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		cp.add(buttonClose);

		setSize(380, 240);
		super2();
	}

	public static void main(String[] args) {
		new PopUpAbout(new MainMenu());
	}
}
