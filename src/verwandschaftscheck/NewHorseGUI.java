package verwandschaftscheck;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import allgemein.MDRFrame;
import allgemein.MainMenu;

public class NewHorseGUI extends MDRFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textInput = new JTextArea();
	private JButton buttonAddHorse;
	private JButton buttonDeleteHorse;
	
	public NewHorseGUI(){
		super();
		
		setLayout(new BorderLayout());
		
		Container west = new Container();
		this.add(west, BorderLayout.WEST);
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		
		Container containerHorses = new Container();
		west.add(containerHorses);
		
		Container buttonsManageHorse = new Container();
		west.add(buttonsManageHorse);
		
		GridLayout glBMH = new GridLayout(1,4);
		glBMH.setHgap(gridButtonGap);
		glBMH.setVgap(gridButtonGap);
		buttonsManageHorse.setLayout(glBMH);
		buttonsManageHorse.add(Box.createGlue());
		
		buttonAddHorse = new JButton("Pferd hinzufügen");
		buttonAddHorse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
				dispose();
			}
		});
		buttonsManageHorse.add(buttonAddHorse);
		
		buttonDeleteHorse = new JButton("Zurücksetzen");
		buttonDeleteHorse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
				dispose();
			}
		});
		buttonsManageHorse.add(buttonDeleteHorse);
		
		buttonsManageHorse.add(Box.createGlue());
		
		Container east = new Container();
		this.add(east, BorderLayout.EAST);
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
		textInputScrollPane.setPreferredSize(new Dimension(350, 330));
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

		super2("Neues Pferd erstellen");
	}
	
	private void analyzeText(){
		
	}
	
	private void save(){
		
	}
	
	private void delete(){
		
	}
	
	public static void main(String[] args){
		new NewHorseGUI();
	}
}
