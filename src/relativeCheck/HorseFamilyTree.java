package relativeCheck;

import static general.MDRFrame.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import general.MDRNumberField;

public class HorseFamilyTree extends Container{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HorseAndRaceField[] horseNamesAndRaces = new HorseAndRaceField[15];
	private boolean mainHorseIsMale = true;
	private Vector<String> vectorFavourites = new Vector<String>();
	
	public HorseFamilyTree(){
		vectorFavourites.add("Favoriten");
		vectorFavourites.add("Favoriten2");
	
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		this.add(Box.createRigidArea(new Dimension(gridButtonGap,0)));
		
		Container mainHorse = new Container();
		GridBagConstraints gbc = new GridBagConstraints();
		mainHorse.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridheight = 5; 
		Container containerEmptySpaceAndName = new Container();
		containerEmptySpaceAndName.setLayout(new GridLayout(5,0));
		containerEmptySpaceAndName.add(Box.createGlue());
		containerEmptySpaceAndName.add(Box.createGlue());
		containerEmptySpaceAndName.add(Box.createGlue());
		containerEmptySpaceAndName.add(Box.createGlue());
		containerEmptySpaceAndName.add(horseNamesAndRaces[0] = new HorseAndRaceField(),gbc);
		mainHorse.add(containerEmptySpaceAndName, gbc);
		
		Container containerHorseInfo = new Container();
		containerHorseInfo.setLayout(new BoxLayout(containerHorseInfo, BoxLayout.Y_AXIS));
		gbc.gridheight = 4;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		containerHorseInfo.add(Box.createRigidArea(new Dimension(gridButtonGap,gridButtonGap/2)));
		Container containerGP = new Container();
		containerGP.setLayout(new BoxLayout(containerGP, BoxLayout.X_AXIS));
		JLabel labelGP = new JLabel("GP:  ");
		containerGP.add(labelGP);
		containerGP.add(new MDRNumberField(false));
		containerHorseInfo.add(containerGP);
		
		JRadioButton buttonMale = new JRadioButton("Hengst");
		buttonMale.setBackground(backgroundColor);
		buttonMale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMainHorseIsMale(buttonMale.isSelected());
			}
		});
		JRadioButton buttonFemale = new JRadioButton("Stute");
		buttonFemale.setBackground(backgroundColor);
		buttonFemale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMainHorseIsMale(buttonFemale.isSelected());
			}
		});
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(buttonMale);
		radioButtonGroup.add(buttonFemale);
		//Container containerRadioButtonGroup = new Container();
		//containerRadioButtonGroup.setLayout(new BoxLayout(containerRadioButtonGroup, BoxLayout.Y_AXIS));
		//containerRadioButtonGroup.add(buttonMale);
		//containerRadioButtonGroup.add(buttonFemale);
		containerHorseInfo.add(buttonMale);
		containerHorseInfo.add(buttonFemale);
		containerHorseInfo.add(new JLabel("eingetragen in:"));
		Iterator<String> it = vectorFavourites.iterator();
		while(it.hasNext()){
			JCheckBox currentCheckBox = new JCheckBox(it.next());
			//currentCheckBox.setBackground(backgroundColor);
			containerHorseInfo.add(currentCheckBox);
		}
		mainHorse.add(containerHorseInfo,gbc);
		this.add(mainHorse);
		
		this.add(Box.createRigidArea(new Dimension(gridButtonGap,0)));
		Container containerParents = new Container();
		containerParents.setLayout(new BoxLayout(containerParents, BoxLayout.Y_AXIS));
		containerParents.add(Box.createRigidArea(new Dimension(0,60+gridButtonGap/2)));
		containerParents.add(horseNamesAndRaces[1] = new HorseAndRaceField());
		containerParents.add(Box.createRigidArea(new Dimension(0,60+gridButtonGap/2)));
		containerParents.add(Box.createGlue());
		containerParents.add(horseNamesAndRaces[2] = new HorseAndRaceField());
		this.add(containerParents);
		
		this.add(Box.createRigidArea(new Dimension(gridButtonGap,0)));
		Container containerGrandparents = new Container();
		containerGrandparents.setLayout(new BoxLayout(containerGrandparents, BoxLayout.Y_AXIS));//new GridLayout(9,0,0,0));
		//gbc = new GridBagConstraints();
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		//gbc.insets = new Insets(10,1,10,1);
		containerGrandparents.add(Box.createRigidArea(new Dimension(0,40)));
		//gbc.gridheight = 4;
		Container containerEmptySpaceAndGrandparents = new Container();
		containerEmptySpaceAndGrandparents.setLayout(new GridLayout(8,0,0,gridButtonGap/2));
		for(int i=3;i<7;i++){
			containerEmptySpaceAndGrandparents.add(horseNamesAndRaces[i] = new HorseAndRaceField());
			containerEmptySpaceAndGrandparents.add(Box.createGlue());
		}
		containerGrandparents.add(containerEmptySpaceAndGrandparents);
		//gbc.gridheight = 1;
		containerGrandparents.add(Box.createGlue(),gbc);
		this.add(containerGrandparents);
		
		this.add(Box.createRigidArea(new Dimension(gridButtonGap,0)));
		Container containerEldest = new Container();
		containerEldest.setLayout(new GridBagLayout());//(8,0,0,gridButtonGap/2));
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0,0,gridButtonGap/2,0);
		for(int i=7;i<15;i++){
			gbc.gridy = i-7;
			containerEldest.add(horseNamesAndRaces[i] = new HorseAndRaceField(),gbc);
		}
		this.add(containerEldest);
	}

	public boolean isMainHorseIsMale() {
		return mainHorseIsMale;
	}

	public void setMainHorseIsMale(boolean mainHorseIsMale) {
		this.mainHorseIsMale = mainHorseIsMale;
	}
	
	public HorseAndRaceField[] getHorseNamesAndRaces(){
		return horseNamesAndRaces;
	}
}
