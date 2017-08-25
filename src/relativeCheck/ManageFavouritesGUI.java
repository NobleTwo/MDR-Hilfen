package relativeCheck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import general.MDRButton;
import general.MDRFrame;
import general.MainMenu;

public class ManageFavouritesGUI extends MDRFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> listOfFavs = new JList<String>();
	private DefaultListModel<String> listModelFavs = new DefaultListModel<String>();

	private MDRButton buttonDelete = new MDRButton("Löschen");
	private MDRButton buttonEdit = new MDRButton("Bearbeiten");

	public ManageFavouritesGUI() {
		super("Favoriten-Listen verwalten");
		JScrollPane scrollPaneListFavs = new JScrollPane(listOfFavs);
		scrollPaneListFavs.setBounds(gridButtonGap, yTop, 2 * widthLabel, heightLabel * 10);
		listOfFavs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOfFavs.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				indexSelected(evt);
			}
		});
		listOfFavs.setSelectionBackground(MDRFrame.LIGHT_BROWN);
		updateFavs();
		cp.add(scrollPaneListFavs);

		MDRButton buttonNew = new MDRButton("Neu");
		buttonNew.setBounds(gridButtonGap + 2 * widthLabel + gridButtonGap, yTop, widthLabel, buttonHeight);
		buttonNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newFav();
			}
		});
		cp.add(buttonNew);

		buttonEdit.setBounds(gridButtonGap + 2 * widthLabel + gridButtonGap, yTop + buttonHeight + gridButtonGap, widthLabel, buttonHeight);
		buttonEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				editFav();
			}
		});
		buttonEdit.setEnabled(false);
		cp.add(buttonEdit);

		buttonDelete.setBounds(gridButtonGap + 2 * widthLabel + gridButtonGap, yTop + buttonHeight * 2 + 2 * gridButtonGap, widthLabel, buttonHeight);
		buttonDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				deleteFav();
			}
		});
		cp.add(buttonDelete);

		MDRButton buttonMainMenu = new MDRButton("Hauptmenü");
		buttonMainMenu.setBounds(gridButtonGap, yTop + heightLabel * 10 + gridButtonGap, widthLabel * 3 / 2, buttonHeight);
		buttonMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new MainMenu();
				dispose();
			}
		});
		cp.add(buttonMainMenu);

		MDRButton buttonManageHorse = new MDRButton("Datenbankverwaltung");
		buttonManageHorse.setBounds(gridButtonGap * 2 + widthLabel * 3 / 2, yTop + heightLabel * 10 + gridButtonGap, widthLabel * 3 / 2, buttonHeight);
		buttonManageHorse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new NewHorseGUI();
				dispose();
			}
		});
		cp.add(buttonManageHorse);

		setSize(gridButtonGap * 7 / 2 + widthLabel * 3, yTop + heightLabel * 10 + buttonHeight + gridButtonGap * 5);
		super2();
	}

	private void updateFavs() {
		listModelFavs = new DefaultListModel<String>();
		Vector<String> vecFavs = DatabaseManager.getFavourites();
		Iterator<String> it = vecFavs.iterator();
		while (it.hasNext()) {
			listModelFavs.addElement(getNameWithSize(it.next()));
		}
		listOfFavs.setModel(listModelFavs);

		if (listModelFavs.isEmpty()) {
			buttonEdit.setEnabled(false);
			buttonDelete.setEnabled(false);
		}
	}

	private void indexSelected(MouseEvent evt) {
		listOfFavs.setSelectedIndex(listOfFavs.locationToIndex(evt.getPoint()));
		if (listOfFavs.getSelectedValue() != null) {
			buttonEdit.setEnabled(true);
			buttonDelete.setEnabled(true);
		}
		if (evt.getClickCount() == 2) {
			editFav();
		}
	}

	private void newFav() {
		new PopUpNewFav(this);
		updateFavs();
	}

	private void editFav() {
		if (listOfFavs.getSelectedValue() != null) {
			new PopUpEditFav(this, getNameWithoutSize(listOfFavs.getSelectedValue()));
			updateFavs();
			buttonEdit.setEnabled(false);
			buttonDelete.setEnabled(false);
		}
	}

	private void deleteFav() {
		if (listOfFavs.getSelectedValue() != null) {
			new PopUpDeleteFav(this, getNameWithoutSize(listOfFavs.getSelectedValue()));
			updateFavs();
			buttonEdit.setEnabled(false);
			buttonDelete.setEnabled(false);
		}
	}

	public static String getNameWithSize(String name){
		int count = 0;
		for (RelativeHorse current: DatabaseManager.getPopulation()) {
			if(current.getFavourites()!=null && current.getFavourites().contains(name)){
				count++;
			}
		}
		return name+" ("+count+")";
	}
	
	public static String getNameWithoutSize(String name){
		while(name.charAt(name.length()-1)!='('){
			name = name.substring(0, name.length()-1);
		}
		name = name.substring(0, name.length()-2);
		return name;
	}
	
	public static void main(String[] args) {
		new ManageFavouritesGUI();
	}
}