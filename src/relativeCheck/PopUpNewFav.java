package relativeCheck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import general.MDRDialog;

public class PopUpNewFav extends MDRDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PopUpNewFav(ManageFavouritesGUI manageFavsFrame) {
		super(manageFavsFrame, "Neue Favoriten-Liste erstellen");
		
		final String errorMessage = "   --- Name bereits vorhanden!";
		JTextField favName = new JTextField();
		favName.setBounds(gridButtonGap, yTop, widthLabel*2, heightLabel);
		cp.add(favName);
		JButton buttonAccept = new JButton("OK");
		buttonAccept.setBounds(gridButtonGap, yTop+heightLabel+gridButtonGap, (2*widthLabel-gridButtonGap)/2, buttonHeight);
		buttonAccept.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(favName.getText().contains(errorMessage)){
					String s = favName.getText(); 
					favName.setText(s.substring(s.indexOf(0),s.indexOf(errorMessage)));
				}
				
				if(DatabaseManager.addFavourite(favName.getText())){
					dispose();
				} else{
					favName.setText(favName.getText()+errorMessage);
				}
			}
		});
		cp.add(buttonAccept);
		
		JButton buttonCancel = new JButton("Abbrechen");
		buttonCancel.setBounds(gridButtonGap*2+(2*widthLabel-gridButtonGap)/2, yTop+heightLabel+gridButtonGap, (2*widthLabel-gridButtonGap)/2, buttonHeight);
		buttonCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cp.add(buttonCancel);
		
		setSize(gridButtonGap*5/2+2*widthLabel,yTop+heightLabel+gridButtonGap*9/2+buttonHeight);
		super2();
	}
	
}
