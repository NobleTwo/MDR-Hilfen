package verwandschaftscheck;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import allgemein.MDRFrame;
import allgemein.MainMenu;

public abstract class ManageHorseGUI2 extends MDRFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textInput = new JTextArea();
	protected JButton buttonAddOrSaveHorse;
	protected JButton buttonResetOrDeleteHorse;
	private HorseAndRaceField[] horseNamesAndRaces;
	
	public ManageHorseGUI2(){
		super();

		setLayout(new BorderLayout());
		
		///
		///WEST
		///
		Container west = new Container();
		this.add(west, BorderLayout.WEST);
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		HorseFamilyTree containerHorses = new HorseFamilyTree();
		west.add(containerHorses);
		horseNamesAndRaces = containerHorses.getHorseNamesAndRaces();
		
		Container buttonsManageHorse = new Container();
		west.add(Box.createRigidArea(new Dimension(0,gridButtonGap)));
		west.add(buttonsManageHorse);
		
		/*GridLayout glBMH = new GridLayout(1,4);
		glBMH.setHgap(gridButtonGap);
		glBMH.setVgap(gridButtonGap);
		buttonsManageHorse.setLayout(glBMH);
		buttonsManageHorse.add(Box.createGlue());*/
		
		buttonsManageHorse.setLayout(new BoxLayout(buttonsManageHorse, BoxLayout.X_AXIS));
		buttonsManageHorse.add(Box.createRigidArea(new Dimension(gridButtonGap,gridButtonGap)));
		
		buttonAddOrSaveHorse = new JButton();
		buttonAddOrSaveHorse.setPreferredSize(new Dimension(170, 25));
		buttonAddOrSaveHorse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addOrSave();
				dispose();
			}
		});
		buttonsManageHorse.add(buttonAddOrSaveHorse);
		
		buttonsManageHorse.add(Box.createRigidArea(new Dimension(gridButtonGap,gridButtonGap)));
		buttonResetOrDeleteHorse = new JButton();
		buttonResetOrDeleteHorse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetOrDelete();
				dispose();
			}
		});
		buttonsManageHorse.add(buttonResetOrDeleteHorse);
		
		buttonsManageHorse.add(Box.createGlue());
		west.add(Box.createRigidArea(new Dimension(gridButtonGap,gridButtonGap)));
		
		///
		///CENTER
		///
		this.add(Box.createRigidArea(new Dimension(gridButtonGap,0)), BorderLayout.CENTER);
		
		///
		///EAST
		///
		Container containerOuterEast = new Container();
		containerOuterEast.setLayout(new BorderLayout());
		this.add(containerOuterEast, BorderLayout.EAST);
		Container east = new Container();
		east.setLayout(new BoxLayout(east,BoxLayout.Y_AXIS));
		
		Container buttonsGoTo = new Container();
		east.add(buttonsGoTo);
		GridLayout glBGT = new GridLayout(2,2);
		glBGT.setHgap(gridButtonGap);
		glBGT.setVgap(gridButtonGap);
		buttonsGoTo.setLayout(glBGT);
		
		JButton buttonChooseHorse = new JButton("Bestehendes Pferd auswählen");
		buttonChooseHorse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HorseChoice(new DocumentManager());
				dispose();
			}
		});
		buttonsGoTo.add(buttonChooseHorse);
		
		JButton buttonGoToRelativeCheck = new JButton("Verwandtschaftscheck");
		buttonGoToRelativeCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RelativeCheckGUI();
				dispose();
			}
		});
		buttonsGoTo.add(buttonGoToRelativeCheck);
		
		JButton buttonGoToMainMenu = new JButton("Hauptmenü");
		buttonGoToMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainMenu();
				dispose();
			}
		});
		buttonsGoTo.add(buttonGoToMainMenu);
		
		JButton buttonClose = new JButton("Schließen");
		buttonClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonsGoTo.add(buttonClose);
		
		JScrollPane textInputScrollPane = new JScrollPane(textInput);
		textInputScrollPane.setPreferredSize(new Dimension(100, 350));
		east.add(Box.createRigidArea(new Dimension(0, gridButtonGap)));
		east.add(textInputScrollPane);
		east.add(Box.createRigidArea(new Dimension(0, gridButtonGap)));
		
		Container buttonsManageTextInput = new Container();
		GridLayout glBMTI = new GridLayout(0,2);
        glBMTI.setHgap(gridButtonGap);
		buttonsManageTextInput.setLayout(glBMTI);
		east.add(buttonsManageTextInput);
		
		JButton buttonDeleteText = new JButton("Text löschen");
		buttonDeleteText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textInput.setText("");
				textInput.requestFocus();
			}
		});
		buttonsManageTextInput.add(buttonDeleteText);
		
		JButton buttonAnalyzeText = new JButton("Text analysieren");
		buttonAnalyzeText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				analyzeText();
			}
		});
		buttonsManageTextInput.add(buttonAnalyzeText);
		
		containerOuterEast.add(east,BorderLayout.CENTER);
		containerOuterEast.add(Box.createRigidArea(new Dimension(gridButtonGap,gridButtonGap)), BorderLayout.EAST);
		containerOuterEast.add(Box.createRigidArea(new Dimension(gridButtonGap,gridButtonGap)), BorderLayout.SOUTH);

		super2("Neues Pferd erstellen");
		textInput.requestFocus();
	}
	
	private void analyzeText(){
		
	}
	
	protected abstract void addOrSave();
	
	protected abstract void resetOrDelete();
}
