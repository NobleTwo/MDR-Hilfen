package verwandschaftscheck;

public class NewHorseGUI extends ManageHorseGUI2{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewHorseGUI(){
		super();
		buttonAddOrSaveHorse.setText("Pferd hinzuf�gen");
		buttonResetOrDeleteHorse.setText("Zur�cksetzen");
		buttonChooseHorse.setText("Bestehendes Pferd bearbeiten");
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
