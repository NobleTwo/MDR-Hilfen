package relativeCheck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import general.MDRDialog;

public class PopUpEditFav extends MDRDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PopUpEditFav(ManageFavouritesGUI manageFavsFrame, String name) {
		super(manageFavsFrame, "Name der Favoriten-Liste ändern");
		
		final String errorMessageAlreadyKnown = "   --- Name bereits vorhanden!";
		final String errorMessageNotKnown = "   --- Alter Name nicht vorhanden! Bitte abbrechen.";
		JTextField favName = new JTextField(name);
		favName.setBounds(gridButtonGap, yTop, widthLabel*2, heightLabel);
		cp.add(favName);
		JButton buttonAccept = new JButton("OK");
		buttonAccept.setBounds(gridButtonGap, yTop+heightLabel+gridButtonGap, (2*widthLabel-gridButtonGap)/2, buttonHeight);
		buttonAccept.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(favName.getText().contains(errorMessageAlreadyKnown)){
					String s = favName.getText(); 
					favName.setText(s.substring(s.indexOf(0),s.indexOf(errorMessageAlreadyKnown)));
				}
				
				int result = DatabaseManager.editFavourite(name, favName.getText());
				switch (result){
					case -2:	favName.setText(favName.getText()+errorMessageAlreadyKnown);
								break;
					case -1:	favName.setText(favName.getText()+errorMessageNotKnown);
								break;
					case 0:		dispose();
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