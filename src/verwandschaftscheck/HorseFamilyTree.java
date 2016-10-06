package verwandschaftscheck;

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
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import allgemein.JNumberField;
import static allgemein.MDRFrame.*;

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
		gbc.insets = new Insets(1,9,1,9);
		mainHorse.setLayout(new GridBagLayout());
		//gbc.gridy = 0;
		//gbc.gridy = 1;
		//mainHorse.add(Box.createGlue());
		//gbc.gridy = 2;
		//mainHorse.add(Box.createGlue());
		//gbc.gridy = 3;
		//mainHorse.add(Box.createGlue());
		gbc.gridy = 4;
		//gbc.ipady = 10;
		//gbc.fill = GridBagConstraints.BOTH;
		mainHorse.add(horseNamesAndRaces[0] = new HorseAndRaceField(),gbc);
		
		Container containerHorseInfo = new Container();
		containerHorseInfo.setLayout(new BoxLayout(containerHorseInfo, BoxLayout.Y_AXIS));
		//containerHorseInfo.add(horseNamesAndRaces[0] = new HorseAndRaceField());
		gbc.gridheight = 4;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		//containerHorseInfo.add(Box.createRigidArea(new Dimension(gridButtonGap,gridButtonGap/2)));
		Container containerGP = new Container();
		containerGP.setLayout(new BoxLayout(containerGP, BoxLayout.X_AXIS));
		JLabel labelGP = new JLabel("GP:  ");
		containerGP.add(labelGP);
		containerGP.add(new JNumberField(false));
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
			currentCheckBox.setBackground(backgroundColor);
			containerHorseInfo.add(currentCheckBox);
		}
		mainHorse.add(containerHorseInfo,gbc);
		this.add(mainHorse);
		
		this.add(Box.createRigidArea(new Dimension(gridButtonGap,0)));
		Container containerParents = new Container();
		containerParents.setLayout(new GridLayout(9,0));
		containerParents.add(Box.createGlue());
		containerParents.add(Box.createGlue());
		containerParents.add(horseNamesAndRaces[1] = new HorseAndRaceField());
		containerParents.add(Box.createGlue());
		containerParents.add(Box.createGlue());
		containerParents.add(Box.createGlue());
		containerParents.add(horseNamesAndRaces[2] = new HorseAndRaceField());
		containerParents.add(Box.createGlue());
		this.add(containerParents);
		
		this.add(Box.createRigidArea(new Dimension(gridButtonGap,0)));
		Container containerGrandparents = new Container();
		containerGrandparents.setLayout(new GridLayout(9,0,0,0));
		for(int i=3;i<7;i++){
			containerGrandparents.add(Box.createGlue());
			containerGrandparents.add(horseNamesAndRaces[i] = new HorseAndRaceField());
		}
		containerGrandparents.add(Box.createGlue());
		this.add(containerGrandparents);
		
		this.add(Box.createRigidArea(new Dimension(gridButtonGap,0)));
		Container containerEldest = new Container();
		containerEldest.setLayout(new GridLayout(8,0,0,gridButtonGap/2));
		for(int i=7;i<15;i++){
			containerEldest.add(horseNamesAndRaces[i] = new HorseAndRaceField());
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
