package general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

import javax.swing.UIManager;

import relativeCheck.DatabaseManager;
import relativeCheck.DocumentManager;
import relativeCheck.IllegalFileException;
import relativeCheck.ManageFavouritesGUI;
import relativeCheck.NewHorseGUI;
import relativeCheck.RelativeCheckGUI;
import relativeCheck.RelativeHorse;
import tournamentCalculator.WettbewerbsrechnerGUI;

public class MainMenu extends MDRFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MDRButton buttonRC = new MDRButton("Verwandtschaftscheck");
	private MDRButton buttonClose = new MDRButton("Beenden");
	private MDRButton buttonAbout = new MDRButton("?");

	final private int buttonWidth = 260;
	final private int buttonCloseWidth = buttonWidth - buttonHeight - 20 - gridButtonGap;
	private final int frameWidth = (int) (4.5 * gridButtonGap + buttonWidth);

	public MainMenu() {
		// Frame-Initialisierung
		super("Hauptmenü");
		int frameHeight = 8 * gridButtonGap + 3 * buttonHeight + 20;
		setSize(frameWidth, frameHeight);

		// Komponenten
		MDRButton buttonGoToWettbewerbsrechner = new MDRButton("Wettbewerbsrechner");
		int yTop = 2 * gridButtonGap + 20;
		buttonGoToWettbewerbsrechner.setBounds(2 * gridButtonGap, yTop, buttonWidth, buttonHeight);
		buttonGoToWettbewerbsrechner.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new WettbewerbsrechnerGUI();
				dispose();
			}
		});
		cp.add(buttonGoToWettbewerbsrechner);

		buttonRC.setBounds(2 * gridButtonGap, yTop + buttonHeight + gridButtonGap, buttonWidth, buttonHeight);
		buttonRC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				loadDatabase();
				showRC();
			}
		});
		cp.add(buttonRC);

		int y3 = yTop + 2 * gridButtonGap + 2 * buttonHeight;
		buttonClose.setBounds(2 * gridButtonGap, y3, buttonCloseWidth, buttonHeight);
		buttonClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		cp.add(buttonClose);

		buttonAbout.setBounds((int) (gridButtonGap * 3 + buttonCloseWidth), y3, buttonHeight + 20, buttonHeight);
		MainMenu temp = this;
		buttonAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new PopUpAbout(temp);
			}
		});
		buttonAbout.setToolTipText("Hilfe und Versionsinformationen");
		UIManager.put("ToolTip.background", BROWN);
		cp.add(buttonAbout);

		super2();
	}

	// Methoden
	public void showRC() {
		buttonRC.setEnabled(false);

		MDRButton buttonDatabase = new MDRButton("Datenbankverwaltung");
		int y3 = (int) (gridButtonGap * 3.5 + 20 + 2 * buttonHeight);
		buttonDatabase.setBounds(2 * gridButtonGap + buttonWidth / 6, y3, buttonWidth * 2 / 3, buttonHeight);
		buttonDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new NewHorseGUI();
				dispose();
			}
		});
		cp.add(buttonDatabase);

		MDRButton buttonGoToRC = new MDRButton("Verwandtschaftscheck");
		int y4 = y3 + buttonHeight + gridButtonGap / 2;
		buttonGoToRC.setBounds(2 * gridButtonGap + buttonWidth / 6, y4, buttonWidth * 2 / 3, buttonHeight);
		buttonGoToRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new RelativeCheckGUI();
				dispose();
			}
		});
		cp.add(buttonGoToRC);

		MDRButton buttonGoToManageFavs = new MDRButton("Favoriten-Listen");
		int y5 = y4 + buttonHeight + gridButtonGap / 2;
		buttonGoToManageFavs.setBounds(2 * gridButtonGap + buttonWidth / 6, y5, buttonWidth * 2 / 3, buttonHeight);
		buttonGoToManageFavs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new ManageFavouritesGUI();
				dispose();
			}
		});
		cp.add(buttonGoToManageFavs);

		int y6 = y5 + gridButtonGap + buttonHeight;
		buttonClose.setBounds(2 * gridButtonGap, y6, buttonCloseWidth, buttonHeight);
		buttonAbout.setBounds((int) (gridButtonGap * 3 + buttonCloseWidth), y6, buttonHeight + 20, buttonHeight);

		int frameHeight = 10 * gridButtonGap + 6 * buttonHeight + 20;
		this.setSize(frameWidth, frameHeight);
	}

	private void loadDatabase() {
		File fileNew = new File(DatabaseManager.fileName);
		File fileOld = new File("database.txt");
		if (fileOld.isFile()) {
			DocumentManager dm = new DocumentManager();
			try {
				dm.analyzeFile();
				if (fileNew.isFile()) {
					DatabaseManager.load();
					DatabaseManager.addPopulation(dm.population);
					new WarningDialog(this, "Pferde werden in der (versteckten) Datei '" + DatabaseManager.fileName + "' gespeichert.\nEs werden nun mehrere Favoritenlisten, GP (Standardwert -1) und Sonderzeichen in Namen unterstützt.");
				} else{
					DatabaseManager.setPopulation(dm.population);
					DatabaseManager.addFavourite("Standard");
					new WarningDialog(this, "Veraltete Datei 'database.txt' ersetzt durch (versteckte) neue Datei '" + DatabaseManager.fileName + "'.\nEs werden nun mehrere Favoritenlisten, GP (Standardwert -1) und Sonderzeichen in Namen unterstützt.");
				}
				fileOld.delete();
			} catch (IllegalFileException e) {
				System.out.println(e.getMessage());
			}
		} else if (fileNew.isFile()){
			DatabaseManager.load();
		} else{
			DatabaseManager.setPopulation(new Vector<RelativeHorse>());
		}
		try {
			Path path = fileNew.toPath();
			Files.setAttribute(path, "dos:hidden", true);
		} catch (IOException e) {
			System.out.println("fail");
		}
	}

	public static void main(String[] args) throws Exception {
		new MainMenu();
	}
}
