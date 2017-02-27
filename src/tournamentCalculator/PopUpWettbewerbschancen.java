package tournamentCalculator;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import general.MDRButton;
import general.MDRFrame;
import general.MDRNumberField;

public class PopUpWettbewerbschancen extends MDRFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PopUpWettbewerbschancen(String horseName, Wettbewerbsrechner w) {
		// Frame-Initialisierung
		super("Wettbewerbschancen " + horseName);

		final Font fontLabel = new Font("Arial", Font.PLAIN, 12);
		// Komponenten
		JLabel labelClass = new JLabel("Klasse");
		int y0 = 2 * gridButtonGap + 20;
		labelClass.setBounds(gridButtonGap, y0, widthLabel, heightLabel);
		cp.add(labelClass);

		JLabel labelDiscipline = new JLabel("Disziplin");
		int yDiscipline = (int) (2.5 * gridButtonGap + 20 + heightLabel);
		labelDiscipline.setBounds(gridButtonGap, yDiscipline, widthLabel, heightLabel);
		cp.add(labelDiscipline);

		Container containerTable = new Container();
		JLabel[] labelLevel = new JLabel[5];
		JLabel[] labelDisciplines = new JLabel[6];

		Container containerDisciplines = new Container();
		GridLayout glDisc = new GridLayout(labelDisciplines.length + 2, 1);
		glDisc.setHgap(gridButtonGap / 2);
		glDisc.setVgap(gridButtonGap / 2);
		containerDisciplines.setLayout(glDisc);
		containerDisciplines.add(Box.createGlue());
		containerDisciplines.add(Box.createGlue());

		labelDisciplines[0] = new JLabel("Dressur");
		labelDisciplines[1] = new JLabel("Vielseitigkeit");
		labelDisciplines[2] = new JLabel("Springen");
		labelDisciplines[3] = new JLabel("Western");
		labelDisciplines[4] = new JLabel("Rennen");
		labelDisciplines[5] = new JLabel("Fahren");
		for (int i = 0; i < labelDisciplines.length; i++) {
			labelDisciplines[i].setFont(fontLabel);
			containerDisciplines.add(labelDisciplines[i]);
		}
		final int widthLabel = 70;
		final int heightGrid = 190;
		containerDisciplines.setBounds(3 * gridButtonGap, y0, widthLabel, heightGrid);
		cp.add(containerDisciplines);

		GridLayout glTab = new GridLayout(labelDisciplines.length + 2, labelLevel.length);
		glTab.setHgap(gridButtonGap / 2);
		glTab.setVgap(gridButtonGap / 2);
		containerTable.setLayout(glTab);
		MDRNumberField[][] numberFieldValue = new MDRNumberField[labelDisciplines.length][labelLevel.length];

		labelLevel[0] = new JLabel("E");
		labelLevel[1] = new JLabel("A");
		labelLevel[2] = new JLabel("L");
		labelLevel[3] = new JLabel("M");
		labelLevel[4] = new JLabel("S");
		for (int i = 0; i < labelLevel.length; i++) {
			labelLevel[i].setHorizontalAlignment(SwingConstants.CENTER);
			containerTable.add(labelLevel[i]);
		}
		for (int i = 0; i < labelLevel.length; i++) {
			containerTable.add(Box.createGlue());
		}
		for (int i = 0; i < labelDisciplines.length; i++) {
			for (int j = 0; j < labelLevel.length; j++) {
				numberFieldValue[i][j] = new MDRNumberField(true);
				numberFieldValue[i][j].setEditable(false);
				for (FocusListener fl : numberFieldValue[i][j].getFocusListeners()) {
					numberFieldValue[i][j].removeFocusListener(fl);
				}
				containerTable.add(numberFieldValue[i][j]);
			}
		}
		final int widthNumberField = 40;
		containerTable.setBounds(4 * gridButtonGap + widthLabel, y0, 5 * widthNumberField + 2 * gridButtonGap, heightGrid);
		cp.add(containerTable);

		MDRButton buttonClose = new MDRButton("Schließen");
		buttonClose.setBounds((int) (5 * gridButtonGap + widthLabel + 2 * widthNumberField), y0 + heightGrid + gridButtonGap, 3 * widthNumberField + gridButtonGap, buttonHeight);
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		cp.add(buttonClose);

		double maximum = 0;
		for (int i = 0; i < numberFieldValue.length; i++) {
			for (int j = 0; j < numberFieldValue[0].length; j++) {
				if (w.wettbewerbschancen[i][j] == -100) {
					numberFieldValue[i][j].setText("X");
					numberFieldValue[i][j].setEnabled(false);
					numberFieldValue[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				} else {
					numberFieldValue[i][j].setText(w.wettbewerbschancen[i][j]);
					if (Double.parseDouble(numberFieldValue[i][j].getText()) > maximum) {
						maximum = Double.parseDouble(numberFieldValue[i][j].getText());
					}
				}
			}
		}

		for (int i = 0; i < numberFieldValue.length; i++) {
			for (int j = 0; j < numberFieldValue[0].length; j++) {
				if (numberFieldValue[i][j].isEnabled() == true && Double.parseDouble(numberFieldValue[i][j].getText()) == maximum) {
					numberFieldValue[i][j].setBackground(MDRFrame.GREEN2);
				}
			}
		}

		int frameWidth = (int) (7.5 * gridButtonGap + widthLabel + 5 * widthNumberField);
		int frameHeight = (int) (y0 + heightGrid + 4.5 * gridButtonGap + buttonHeight);
		setSize(frameWidth, frameHeight);
		super2();
	}

	// Methoden
	public static void main(String[] args) {
		new PopUpWettbewerbschancen("Test", new Wettbewerbsrechner(new Wettbewerbspferd()));
	}
}
