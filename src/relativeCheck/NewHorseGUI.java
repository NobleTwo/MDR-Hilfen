package relativeCheck;

public class NewHorseGUI extends ManageHorseGUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewHorseGUI() {
		super("Verwandtschaftscheck: Neues Pferd erstellen");
		buttonAddOrSaveHorse.setText("Pferd hinzufügen");
		buttonResetOrDeleteHorse.setText("Zurücksetzen");
		buttonChooseHorse.setText("Bestehendes Pferd bearbeiten");
		super2();
	}

	@Override
	protected void resetOrDelete() {
		// reset
		new NewHorseGUI();
		this.dispose();
	}

	public static void main(String[] args) {
		new NewHorseGUI();
	}
}
