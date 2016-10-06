package verwandschaftscheck;

public class NewHorseGUI extends ManageHorseGUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NewHorseGUI(){
		super();
		buttonAddOrSaveHorse.setText("Pferd hinzufügen");
		buttonResetOrDeleteHorse.setText("Zurücksetzen");
	}
	@Override
	protected void addOrSave() {
		//add
	}
	
	@Override
	protected void resetOrDelete() {
		//reset
		new NewHorseGUI();
		this.dispose();
	}
	
	public static void main(String[] args){
		new NewHorseGUI();
	}
}
