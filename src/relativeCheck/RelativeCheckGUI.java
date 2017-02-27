package relativeCheck;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import general.MDRButton;
import general.MDRFrame;
import general.MDRNumberField;
import general.MainMenu;

public class RelativeCheckGUI extends MDRFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RelativeHorse[] subject = new RelativeHorse[2];
	private HorseAndRaceField[][] horseNamesAndRaces = new HorseAndRaceField[2][15];
	private MDRNumberField[] numberfieldGP = new MDRNumberField[2];
	private JRadioButton[] radioButtonIsMale = new JRadioButton[2];
	private JRadioButton[] radioButtonIsFemale = new JRadioButton[2];

	private static final Color DARK_GREEN = new Color(0, 159, 0);
	private static final Color BLACK = new Color(69, 49, 49);

	public RelativeCheckGUI() {
		super("Verwandtschaftscheck");

		MDRButton[] buttonChooseHorse = new MDRButton[2];
		int x2ndColumn = 0, x2row = 0, x3row = 0, x4row = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < horseNamesAndRaces[0].length; j++) {
				horseNamesAndRaces[i][j] = new HorseAndRaceField();
				horseNamesAndRaces[i][j].setEditable(false);
				cp.add(horseNamesAndRaces[i][j]);
			}

			numberfieldGP[i] = new MDRNumberField(false);
			numberfieldGP[i].setBackground(MDRFrame.BROWN);
			radioButtonIsMale[i] = new JRadioButton("Hengst");
			radioButtonIsFemale[i] = new JRadioButton("Stute");

			x2ndColumn = 5 * gridButtonGap + 4 * horseNamesAndRaces[0][0].getWidth();
			int y0 = (int) (yTop + 3.5 * HorseAndRaceField.HEIGHT + 3 * gridButtonGap);
			horseNamesAndRaces[i][0].setLocation(gridButtonGap + i * x2ndColumn, y0);

			x2row = 2 * gridButtonGap + horseNamesAndRaces[0][0].getWidth() + i * x2ndColumn;
			int y1 = (int) (yTop + HorseAndRaceField.HEIGHT * 1.5 + gridButtonGap);
			horseNamesAndRaces[i][1].setLocation(x2row, y1);
			int y2 = (int) (yTop + HorseAndRaceField.HEIGHT * 5.5 + 5 * gridButtonGap);
			horseNamesAndRaces[i][2].setLocation(x2row, y2);

			x3row = 3 * gridButtonGap + 2 * horseNamesAndRaces[0][0].getWidth() + i * x2ndColumn;
			for (int j = 3; j <= 6; j++) {
				int y = (int) (yTop + HorseAndRaceField.HEIGHT / 2 + (2 * gridButtonGap + 2 * HorseAndRaceField.HEIGHT) * (j - 3));
				horseNamesAndRaces[i][j].setLocation(x3row, y);
			}

			x4row = 4 * gridButtonGap + 3 * horseNamesAndRaces[0][0].getWidth() + i * x2ndColumn;
			for (int j = 7; j <= 14; j++) {
				horseNamesAndRaces[i][j].setLocation(x4row, yTop + (gridButtonGap + HorseAndRaceField.HEIGHT) * (j - 7));
			}

			Container containerGP = new Container();
			containerGP.setLayout(new BorderLayout());
			int y0GP = y0 + HorseAndRaceField.HEIGHT + gridButtonGap / 2;
			containerGP.setBounds(gridButtonGap + i * x2ndColumn, y0GP, HorseAndRaceField.WIDTH, 2 * gridButtonGap);
			JLabel labelGP = new JLabel("GP: ");
			containerGP.add(labelGP, BorderLayout.WEST);
			numberfieldGP[i].setEditable(false);
			numberfieldGP[i].setText("");
			// needed so that GP is not removed on click
			for (FocusListener f : numberfieldGP[i].getFocusListeners()) {
				numberfieldGP[i].removeFocusListener(f);
			}
			containerGP.add(numberfieldGP[i], BorderLayout.CENTER);
			cp.add(containerGP);

			radioButtonIsMale[i].setBounds(gridButtonGap + i * x2ndColumn, (int) (y0GP + 2.5 * gridButtonGap), HorseAndRaceField.WIDTH, 2 * gridButtonGap);
			radioButtonIsMale[i].setOpaque(false);
			radioButtonIsMale[i].setSelected(i == 0);
			for (MouseListener ml : radioButtonIsMale[i].getMouseListeners()) {
				radioButtonIsMale[i].removeMouseListener(ml);
			}
			cp.add(radioButtonIsMale[i]);
			radioButtonIsFemale[i].setBounds(gridButtonGap + i * x2ndColumn, (int) (y0GP + 4 * gridButtonGap), HorseAndRaceField.WIDTH, 2 * gridButtonGap);
			radioButtonIsFemale[i].setOpaque(false);
			radioButtonIsFemale[i].setSelected(i == 1);
			for (MouseListener ml : radioButtonIsFemale[i].getMouseListeners()) {
				radioButtonIsFemale[i].removeMouseListener(ml);
			}
			cp.add(radioButtonIsFemale[i]);
			ButtonGroup buttonGroupRadioButtons = new ButtonGroup();
			buttonGroupRadioButtons.add(radioButtonIsMale[i]);
			buttonGroupRadioButtons.add(radioButtonIsFemale[i]);

			int y0ChooseHorse = (int) (y0GP + 7 * gridButtonGap);
			buttonChooseHorse[i] = new MDRButton("Pferd ändern");
			RelativeCheckGUI temp = this;
			int pos = i;
			buttonChooseHorse[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					new HorseChoiceGUI(temp, pos);
				}
			});
			buttonChooseHorse[i].setBounds(gridButtonGap + i * x2ndColumn, y0ChooseHorse, HorseAndRaceField.WIDTH, buttonHeight);
			cp.add(buttonChooseHorse[i]);
		}

		MDRButton buttonClose = new MDRButton("Schließen");
		buttonClose.setBounds(x4row, yTop + (gridButtonGap + HorseAndRaceField.HEIGHT) * 8, HorseAndRaceField.WIDTH, buttonHeight);
		buttonClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		cp.add(buttonClose);

		MDRButton buttonMainMenu = new MDRButton("Hauptmenü");
		buttonMainMenu.setBounds(x3row, yTop + (gridButtonGap + HorseAndRaceField.HEIGHT) * 8, HorseAndRaceField.WIDTH, buttonHeight);
		buttonMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new MainMenu();
				dispose();
			}
		});
		cp.add(buttonMainMenu);

		MDRButton buttonNewHorseGUI = new MDRButton("Datenbank");
		buttonNewHorseGUI.setBounds(x2row, yTop + (gridButtonGap + HorseAndRaceField.HEIGHT) * 8, HorseAndRaceField.WIDTH, buttonHeight);
		buttonNewHorseGUI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new NewHorseGUI();
				dispose();
			}
		});
		cp.add(buttonNewHorseGUI);

		int frameWidth = x2ndColumn * 2 + gridButtonGap / 2;
		int frameHeight = yTop + (gridButtonGap + HorseAndRaceField.HEIGHT) * 8 + buttonHeight + gridButtonGap * 4;
		setSize(frameWidth, frameHeight);
		super2();
	}

	private void update(int position) {
		if (subject[position] != null) {
			HorseLoader.assignNamesAndRaces(subject[position].getName(), horseNamesAndRaces[position]);
			numberfieldGP[position].setText(subject[position].getCompletePotential());
			radioButtonIsMale[position].setSelected(subject[position].isMale());
			radioButtonIsFemale[position].setSelected(!subject[position].isMale());
		}
	}

	public void setSubject(int position, String name) {
		RelativeHorse tempSubject = DatabaseManager.findHorse(name);
		if (tempSubject != null) {
			this.subject[position] = tempSubject;
			update(position);
			// if other horse is already set, automatically check for relativity
			if (subject[Math.abs(1 - position)] != null) {
				check();
			}
		}
	}

	private void check() {
		resetColors();
		RelativeCheckResult result = getResult();

		if (result.containsError()) {
			Vector<Point[]> identicalHorses = result.getIdenticalHorses();
			for (Point[] pair : identicalHorses) {
				horseNamesAndRaces[pair[0].x][pair[0].y].setColor(true, ResultType.ERROR);
				horseNamesAndRaces[pair[1].x][pair[1].y].setColor(true, ResultType.ERROR);
			}

			Vector<Point[]> mismatchedRaces = result.getMismatchedRaces();
			for (Point[] pair : mismatchedRaces) {
				horseNamesAndRaces[pair[0].x][pair[0].y].setColor(false, ResultType.ERROR);
				horseNamesAndRaces[pair[1].x][pair[1].y].setColor(false, ResultType.ERROR);
			}

			if (result.isMismatchedSexes()) {
				for (int i = 0; i < subject.length; i++) {
					if (radioButtonIsMale[i].isSelected()) {
						radioButtonIsMale[i].setForeground(MDRFrame.RED2);
					}
					if (radioButtonIsFemale[i].isSelected()) {
						radioButtonIsFemale[i].setForeground(MDRFrame.RED2);
					}
				}
			}
		} else if (result.containsWarning() || !result.getTooOldToMatter().isEmpty()) {
			Vector<Point> unknownHorses = result.getUnknownHorses();
			for (Point point : unknownHorses) {
				horseNamesAndRaces[point.x][point.y].setColor(true, ResultType.WARNING);
			}

			Vector<Point> unknownRaces = result.getUnknownRaces();
			for (Point point : unknownRaces) {
				horseNamesAndRaces[point.x][point.y].setColor(false, ResultType.WARNING);
			}

			Vector<Point[]> tooOldToMatter = result.getTooOldToMatter();
			for (Point[] pair : tooOldToMatter) {
				horseNamesAndRaces[pair[0].x][pair[0].y].setColor(true, ResultType.WARNING);
				horseNamesAndRaces[pair[1].x][pair[1].y].setColor(true, ResultType.WARNING);
			}
		} else {
			for (int i = 0; i < subject.length; i++) {
				for (HorseAndRaceField field : horseNamesAndRaces[i]) {
					field.setColor(true, ResultType.MATCH);
					field.setColor(false, ResultType.MATCH);
				}
			}

			for (int i = 0; i < subject.length; i++) {
				if (radioButtonIsMale[i].isSelected()) {
					radioButtonIsMale[i].setForeground(DARK_GREEN);
				}
				if (radioButtonIsFemale[i].isSelected()) {
					radioButtonIsFemale[i].setForeground(DARK_GREEN);
				}
			}
		}

	}

	private void resetColors() {
		for (int i = 0; i < horseNamesAndRaces.length; i++) {
			for (HorseAndRaceField field : horseNamesAndRaces[i]) {
				field.setColor(true, ResultType.NEUTRAL);
				field.setColor(false, ResultType.NEUTRAL);
			}
			radioButtonIsMale[i].setForeground(BLACK);
			radioButtonIsFemale[i].setForeground(BLACK);
		}

	}

	private RelativeCheckResult getResult() {
		String[][] horseNames = new String[2][15];
		for (int i = 0; i < horseNames.length; i++) {
			for (int j = 0; j < horseNames[i].length; j++) {
				horseNames[i][j] = horseNamesAndRaces[i][j].getName();
			}
		}

		String[][] races = new String[2][15];
		for (int i = 0; i < races.length; i++) {
			for (int j = 0; j < races[i].length; j++) {
				races[i][j] = horseNamesAndRaces[i][j].getRace();
			}
		}

		boolean[] isMale = new boolean[2];
		isMale[0] = subject[0].isMale();
		isMale[1] = subject[1].isMale();

		return new RelativeCheckResult(horseNames, races, isMale);
	}

	public RelativeHorse getSubject(int position) {
		return subject[position];
	}

	public static void main(String[] args) {
		new RelativeCheckGUI();
	}

}
