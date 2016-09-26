package Wettbewerbsrechner;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Allgemein.Menue;


/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 10.08.2014
  * @author 
  */

@SuppressWarnings("serial")
public class Rechnerfenster extends JFrame {
  // Anfang Attribute
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabel9 = new JLabel();
  private JLabel jLabel10 = new JLabel();
  private JLabel jLabel11 = new JLabel();
  private JLabel jLabel12 = new JLabel();
  private JLabel jLabel13 = new JLabel();
  private JLabel jLabel14 = new JLabel();
  private JLabel jLabel15 = new JLabel();
  private JLabel jLabel16 = new JLabel();
  private JLabel jLabel17 = new JLabel();
  private JLabel jLabel18 = new JLabel();
  private JLabel jLabel19 = new JLabel();
  private JLabel jLabel20 = new JLabel();
  private JLabel jLabel21 = new JLabel();
  private JLabel jLabel22 = new JLabel();
  private JLabel jLabel23 = new JLabel();
  private JLabel jLabel24 = new JLabel();
  private JLabel jLabel25 = new JLabel();
  private JLabel jLabel26 = new JLabel();
  private JLabel jLabel27 = new JLabel();
  private JLabel jLabel28 = new JLabel();
  private JLabel jLabel30 = new JLabel();
  private JLabel jLabel31 = new JLabel();
  private JLabel jLabel32 = new JLabel();
  private JLabel jLabel33 = new JLabel();
  private JLabel jLabel34 = new JLabel();
  private JLabel jLabel35 = new JLabel();
  private JLabel jLabel36 = new JLabel();
  private JLabel jLabel37 = new JLabel();
  private JTextField wertDressur = new JTextField();
  private JTextField wertKondition = new JTextField();
  private JTextField wertTempo = new JTextField();
  private JTextField wertGelassenheit = new JTextField();
  private JTextField wertWendigkeit = new JTextField();
  private JTextField wertGrundgangarten = new JTextField();
  private JTextField wertFahren = new JTextField();
  private JTextField wertRennen = new JTextField();
  private JTextField wertWestern = new JTextField();
  private JTextField wertSpringen = new JTextField();
  private JComboBox wertTemperament = new JComboBox();
  private JComboBox wertGelehrigkeit = new JComboBox();
  private JComboBox wertSiegeswille = new JComboBox();
  private JComboBox wertIntelligenz = new JComboBox();
  private JComboBox wertNervenstaerke = new JComboBox();
  private JComboBox wertGutmuetigkeit = new JComboBox();
  private JComboBox wertAufmerksamkeit = new JComboBox();
  private JComboBox wertLeistungsbereitschaft = new JComboBox();
  private JComboBox wertSozialverhalten = new JComboBox();
  private JComboBox wertFurchtlosigkeit = new JComboBox();
  private JButton ButtonChancenBerechnen = new JButton();
  private JButton ButtonTextAnalysieren = new JButton();
  private JTextArea ereignislogText = new JTextArea();
  
  private JTextArea textInput = new JTextArea("");
  private JScrollPane textInputScrollPane = new JScrollPane(textInput);
  
  
  public String[] splitResult;
  private JButton ButtonZurueck = new JButton();
  private JButton ButtonBeenden = new JButton();
  public PopUpWettbewerbschancen chancen;
  private JLabel jlabelName = new JLabel();
  public JTextField nameDesPferds = new JTextField();
  private JButton ButtonTextLoeschen = new JButton();
  // Ende Attribute
  
  public Rechnerfenster(String title) { 
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 521; 
    int frameHeight = 629;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    jLabel1.setBounds(165, 16, 251, 33);
    jLabel1.setText("Wettbewerbsrechner");
    jLabel1.setFont(new Font("Dialog", Font.BOLD, 20));
    cp.add(jLabel1);
    jLabel4.setBounds(16, 96, 67, 22);
    jLabel4.setText("Disziplinen");
    jLabel4.setFont(new Font("Dialog", Font.BOLD, 12));
    cp.add(jLabel4);
    jLabel5.setBounds(32, 128, 51, 19);
    jLabel5.setText("Dressur");
    jLabel5.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel5);
    jLabel6.setBounds(32, 152, 54, 19);
    jLabel6.setText("Springen");
    jLabel6.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel6);
    jLabel7.setBounds(32, 176, 51, 19);
    jLabel7.setText("Western");
    jLabel7.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel7);
    jLabel8.setBounds(32, 200, 48, 19);
    jLabel8.setText("Rennen");
    jLabel8.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel8);
    jLabel9.setBounds(32, 224, 51, 19);
    jLabel9.setText("Fahren");
    jLabel9.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel9);
    jLabel10.setBounds(280, 96, 75, 17);
    jLabel10.setText("Grundlagen");
    cp.add(jLabel10);
    jLabel11.setBounds(304, 128, 91, 17);
    jLabel11.setText("Grundgangarten");
    jLabel11.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel11);
    jLabel12.setBounds(304, 152, 67, 17);
    jLabel12.setText("Wendigkeit");
    jLabel12.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel12);
    jLabel13.setBounds(304, 176, 83, 17);
    jLabel13.setText("Gelassenheit");
    jLabel13.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel13);
    jLabel14.setBounds(304, 200, 43, 17);
    jLabel14.setText("Tempo");
    jLabel14.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel14);
    jLabel15.setBounds(304, 224, 59, 17);
    jLabel15.setText("Kondition");
    jLabel15.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel15);
    jLabel16.setBounds(16, 256, 51, 17);
    jLabel16.setText("Interieur");
    cp.add(jLabel16);
    jLabel17.setBounds(32, 280, 84, 17);
    jLabel17.setText("Temperament");
    jLabel17.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel17);
    jLabel18.setBounds(32, 304, 67, 17);
    jLabel18.setText("Gelehrigkeit");
    jLabel18.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel18);
    jLabel19.setBounds(32, 328, 129, 17);
    jLabel19.setText("Leistungsbereitschaft");
    jLabel19.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel19);
    jLabel20.setBounds(32, 352, 91, 17);
    jLabel20.setText("Aufmerksamkeit");
    jLabel20.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel20);
    jLabel21.setBounds(32, 376, 75, 17);
    jLabel21.setText("Gutmütigkeit");
    jLabel21.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel21);
    jLabel22.setBounds(304, 280, 75, 17);
    jLabel22.setText("Nervenstärke");
    jLabel22.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel22);
    jLabel23.setBounds(304, 304, 59, 19);
    jLabel23.setText("Intelligenz");
    jLabel23.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel23);
    jLabel24.setBounds(304, 328, 67, 17);
    jLabel24.setText("Siegeswille");
    jLabel24.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel24);
    jLabel25.setBounds(304, 352, 83, 17);
    jLabel25.setText("Furchtlosigkeit");
    jLabel25.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel25);
    jLabel26.setBounds(304, 376, 91, 17);
    jLabel26.setText("Sozialverhalten");
    jLabel26.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel26);
    jLabel27.setBounds(248, 200, 11, 17);
    jLabel27.setText("%");
    cp.add(jLabel27);
    jLabel28.setBounds(248, 224, 11, 17);
    jLabel28.setText("%");
    cp.add(jLabel28);
    jLabel30.setBounds(480, 224, 11, 17);
    jLabel30.setText("%");
    cp.add(jLabel30);
    jLabel31.setBounds(480, 200, 11, 17);
    jLabel31.setText("%");
    cp.add(jLabel31);
    jLabel32.setBounds(480, 176, 11, 17);
    jLabel32.setText("%");
    cp.add(jLabel32);
    jLabel33.setBounds(480, 152, 11, 17);
    jLabel33.setText("%");
    cp.add(jLabel33);
    jLabel34.setBounds(480, 128, 11, 17);
    jLabel34.setText("%");
    cp.add(jLabel34);
    jLabel35.setBounds(248, 128, 11, 17);
    jLabel35.setText("%");
    cp.add(jLabel35);
    jLabel36.setBounds(248, 152, 11, 17);
    jLabel36.setText("%");
    cp.add(jLabel36);
    jLabel37.setBounds(248, 176, 11, 17);
    jLabel37.setText("%");
    cp.add(jLabel37);
    wertDressur.setBounds(216, 128, 25, 17);
    wertDressur.setText("0");
    wertDressur.addFocusListener(new FocusAdapter() { 
      public void focusGained(FocusEvent evt) { 
        wertDressur_FocusGained(evt);
      }
    });
    cp.add(wertDressur);
    wertKondition.setBounds(448, 224, 25, 17);
    wertKondition.setText("0");
    wertKondition.addFocusListener(new FocusAdapter() { 
      public void focusGained(FocusEvent evt) { 
        wertKondition_FocusGained(evt);
      }
    });
    cp.add(wertKondition);
    wertTempo.setBounds(448, 200, 25, 17);
    wertTempo.setText("0");
    wertTempo.addFocusListener(new FocusAdapter() { 
      public void focusGained(FocusEvent evt) { 
        wertTempo_FocusGained(evt);
      }
    });
    cp.add(wertTempo);
    wertGelassenheit.setBounds(448, 176, 25, 17);
    wertGelassenheit.setText("0");
    wertGelassenheit.addFocusListener(new FocusAdapter() { 
      public void focusGained(FocusEvent evt) { 
        wertGelassenheit_FocusGained(evt);
      }
    });
    cp.add(wertGelassenheit);
    wertWendigkeit.setBounds(448, 152, 25, 17);
    wertWendigkeit.setText("0");
    wertWendigkeit.addFocusListener(new FocusAdapter() { 
      public void focusGained(FocusEvent evt) { 
        wertWendigkeit_FocusGained(evt);
      }
    });
    cp.add(wertWendigkeit);
    wertGrundgangarten.setBounds(448, 128, 25, 17);
    wertGrundgangarten.setText("0");
    wertGrundgangarten.addFocusListener(new FocusAdapter() { 
      public void focusGained(FocusEvent evt) { 
        wertGrundgangarten_FocusGained(evt);
      }
    });
    cp.add(wertGrundgangarten);
    wertFahren.setBounds(216, 224, 25, 17);
    wertFahren.setText("0");
    wertFahren.addFocusListener(new FocusAdapter() { 
      public void focusGained(FocusEvent evt) { 
        wertFahren_FocusGained(evt);
      }
    });   
    cp.add(wertFahren);
    wertRennen.setBounds(216, 200, 25, 17);
    wertRennen.setText("0");
    wertRennen.addFocusListener(new FocusAdapter() { 
      public void focusGained(FocusEvent evt) { 
        wertRennen_FocusGained(evt);
      }
    });
    cp.add(wertRennen);
    wertWestern.setBounds(216, 176, 25, 17);
    wertWestern.setText("0");
    wertWestern.addFocusListener(new FocusAdapter() { 
      public void focusGained(FocusEvent evt) { 
        wertWestern_FocusGained(evt);
      }
    });
    cp.add(wertWestern);
    wertSpringen.setBounds(216, 152, 25, 17);
    wertSpringen.setText("0");
    wertSpringen.addFocusListener(new FocusAdapter() { 
      public void focusGained(FocusEvent evt) { 
        wertSpringen_FocusGained(evt);
      }
    });
    cp.add(wertSpringen);
    wertTemperament.setBounds(168, 280, 89, 25);
    wertTemperament.setModel(new DefaultComboBoxModel(new String[] {"Miserabel", "Schlecht", "In Ordnung", "Gut", "Exzellent"}));
    cp.add(wertTemperament);
    wertGelehrigkeit.setBounds(168, 304, 89, 25);
    wertGelehrigkeit.setModel(new DefaultComboBoxModel(new String[] {"Miserabel", "Schlecht", "In Ordnung", "Gut", "Exzellent"}));
    wertGelehrigkeit.setSelectedIndex(0);
    cp.add(wertGelehrigkeit);
    wertSiegeswille.setBounds(400, 328, 89, 25);
    wertSiegeswille.setModel(new DefaultComboBoxModel(new String[] {"Miserabel", "Schlecht", "In Ordnung", "Gut", "Exzellent"}));
    wertSiegeswille.setSelectedIndex(0);
    cp.add(wertSiegeswille);
    wertIntelligenz.setBounds(400, 304, 89, 25);
    wertIntelligenz.setModel(new DefaultComboBoxModel(new String[] {"Miserabel", "Schlecht", "In Ordnung", "Gut", "Exzellent"}));
    wertIntelligenz.setSelectedIndex(0);
    cp.add(wertIntelligenz);
    wertNervenstaerke.setBounds(400, 280, 89, 25);
    wertNervenstaerke.setModel(new DefaultComboBoxModel(new String[] {"Miserabel", "Schlecht", "In Ordnung", "Gut", "Exzellent"}));
    wertNervenstaerke.setSelectedIndex(0);
    cp.add(wertNervenstaerke);
    wertGutmuetigkeit.setBounds(168, 376, 89, 25);
    wertGutmuetigkeit.setModel(new DefaultComboBoxModel(new String[] {"Miserabel", "Schlecht", "In Ordnung", "Gut", "Exzellent"}));
    wertGutmuetigkeit.setSelectedIndex(0);
    cp.add(wertGutmuetigkeit);
    wertAufmerksamkeit.setBounds(168, 352, 89, 25);
    wertAufmerksamkeit.setModel(new DefaultComboBoxModel(new String[] {"Miserabel", "Schlecht", "In Ordnung", "Gut", "Exzellent"}));
    wertAufmerksamkeit.setSelectedIndex(0);
    cp.add(wertAufmerksamkeit);
    wertLeistungsbereitschaft.setBounds(168, 328, 89, 25);
    wertLeistungsbereitschaft.setModel(new DefaultComboBoxModel(new String[] {"Miserabel", "Schlecht", "In Ordnung", "Gut", "Exzellent"}));
    wertLeistungsbereitschaft.setSelectedIndex(0);
    cp.add(wertLeistungsbereitschaft);
    wertSozialverhalten.setBounds(400, 376, 89, 25);
    wertSozialverhalten.setModel(new DefaultComboBoxModel(new String[] {"Miserabel", "Schlecht", "In Ordnung", "Gut", "Exzellent"}));
    wertSozialverhalten.setSelectedIndex(0);
    cp.add(wertSozialverhalten);
    wertFurchtlosigkeit.setBounds(400, 352, 89, 25);
    wertFurchtlosigkeit.setModel(new DefaultComboBoxModel(new String[] {"Miserabel", "Schlecht", "In Ordnung", "Gut", "Exzellent"}));
    wertFurchtlosigkeit.setSelectedIndex(0);
    cp.add(wertFurchtlosigkeit);
    ButtonChancenBerechnen.setBounds(16, 416, 201, 25);
    ButtonChancenBerechnen.setText("Wettbewerbschancen berechnen");
    ButtonChancenBerechnen.setMargin(new Insets(2, 2, 2, 2));
    ButtonChancenBerechnen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonChancenBerechnen_ActionPerformed(evt);
      }
    });
    cp.add(ButtonChancenBerechnen);
    ButtonTextAnalysieren.setBounds(16, 560, 105, 25);
    ButtonTextAnalysieren.setText("Text analysieren");
    ButtonTextAnalysieren.setMargin(new Insets(2, 2, 2, 2));
    ButtonTextAnalysieren.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonTextAnalysieren_ActionPerformed(evt);
      }
    });
    ButtonTextAnalysieren.setEnabled(true);
    cp.add(ButtonTextAnalysieren);
    textInputScrollPane.setBounds(16, 456, 473, 97);
    cp.add(textInputScrollPane);
    ButtonZurueck.setBounds(304, 560, 89, 25);
    ButtonZurueck.setText("Hauptmenü");
    ButtonZurueck.setMargin(new Insets(2, 2, 2, 2));
    ButtonZurueck.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonZurueck_ActionPerformed(evt);
      }
    });
    cp.add(ButtonZurueck);
    ButtonBeenden.setBounds(400, 560, 89, 25);
    ButtonBeenden.setText("Schließen");
    ButtonBeenden.setMargin(new Insets(2, 2, 2, 2));
    ButtonBeenden.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonBeenden_ActionPerformed(evt);
      }
    });
    cp.add(ButtonBeenden);
    jlabelName.setBounds(16, 64, 38, 20);
    jlabelName.setText("Name");
    cp.add(jlabelName);
    nameDesPferds.setBounds(96, 64, 377, 20);
    cp.add(nameDesPferds);
    cp.setBackground(new Color(0xB8CFE5));
    ButtonTextLoeschen.setBounds(128, 560, 105, 25);
    ButtonTextLoeschen.setText("Text löschen");
    ButtonTextLoeschen.setMargin(new Insets(2, 2, 2, 2));
    ButtonTextLoeschen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonTextLoeschen_ActionPerformed(evt);
      }
    });
    cp.add(ButtonTextLoeschen);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Rechnerfenster
  
  // Anfang Methoden
  public void ButtonChancenBerechnen_ActionPerformed(ActionEvent evt) {
    
    double[] disziplinenwerte = new double[5];
    double[] grundlagenwerte = new double[5];
    String[] interieurwerte = new String[10];
    
    disziplinenwerte[0]=Double.parseDouble(wertDressur.getText());  
    disziplinenwerte[1]=Double.parseDouble(wertSpringen.getText());
    disziplinenwerte[2]=Double.parseDouble(wertWestern.getText());
    disziplinenwerte[3]=Double.parseDouble(wertRennen.getText());
    disziplinenwerte[4]=Double.parseDouble(wertFahren.getText());
    
    grundlagenwerte[0]=Double.parseDouble(wertGrundgangarten.getText());
    grundlagenwerte[1]=Double.parseDouble(wertWendigkeit.getText());
    grundlagenwerte[2]=Double.parseDouble(wertGelassenheit.getText());
    grundlagenwerte[3]=Double.parseDouble(wertTempo.getText());
    grundlagenwerte[4]=Double.parseDouble(wertKondition.getText());
    
    interieurwerte[0]=String.valueOf(wertTemperament.getSelectedItem());
    interieurwerte[1]=String.valueOf(wertGelehrigkeit.getSelectedItem());
    interieurwerte[2]=String.valueOf(wertLeistungsbereitschaft.getSelectedItem());
    interieurwerte[3]=String.valueOf(wertAufmerksamkeit.getSelectedItem());
    interieurwerte[4]=String.valueOf(wertGutmuetigkeit.getSelectedItem());
    interieurwerte[5]=String.valueOf(wertNervenstaerke.getSelectedItem());
    interieurwerte[6]=String.valueOf(wertIntelligenz.getSelectedItem());
    interieurwerte[7]=String.valueOf(wertSiegeswille.getSelectedItem());
    interieurwerte[8]=String.valueOf(wertFurchtlosigkeit.getSelectedItem());
    interieurwerte[9]=String.valueOf(wertSozialverhalten.getSelectedItem());
    
    Wettbewerbspferd p = new Wettbewerbspferd();
    p.weiseWertezu(disziplinenwerte, grundlagenwerte, interieurwerte);  
    Wettbewerbsrechner w = new Wettbewerbsrechner(p);
    
    chancen = new PopUpWettbewerbschancen("Wettbewerbschancen "+nameDesPferds.getText(), w);
    
    
    int[] indexMaximum = new int[2];
    double maximalwert=0;
    for (int i=0; i<w.wettbewerbschancen.length; i++) {
      for (int j=0; j<w.wettbewerbschancen[0].length; j++) {
        if (maximalwert<w.wettbewerbschancen[i][j]) {
          maximalwert=w.wettbewerbschancen[i][j];
          indexMaximum[0]=i;
          indexMaximum[1]=j;
        } 
      } 
    } 
    
  } 
  
  public void ButtonTextAnalysieren_ActionPerformed(ActionEvent evt) {
    String text = textInput.getText();
    splitResult = text.split("\n");
    
    int nameIndex = 0;
    for(int i=0; i<splitResult.length; i++){
      if(splitResult[i].contains("Gesamtpotential") && !splitResult[i].contains("Potential")){
        nameIndex = i;
        break;
      }
    }
    nameDesPferds.setText(splitResult[nameIndex-3]);
    
    wertDressur.setText(findeWert("Dressur"));
    wertSpringen.setText(findeWert("Springen"));
    wertWestern.setText(findeWert("Western"));
    wertRennen.setText(findeWert("Rennen"));
    wertFahren.setText(findeWert("Fahren"));
    wertGrundgangarten.setText(findeWert("Grundgangarten"));
    wertWendigkeit.setText(findeWert("Wendigkeit"));
    wertGelassenheit.setText(findeWert("Gelassenheit"));
    wertTempo.setText(findeWert("Tempo"));
    wertKondition.setText(findeWert("Kondition")); 
    
    wertTemperament.setSelectedIndex(findeAttribut("Temperament")); 
    wertGelehrigkeit.setSelectedIndex(findeAttribut("Gelehrigkeit"));
    wertLeistungsbereitschaft.setSelectedIndex(findeAttribut("Leistungsbereitschaft"));
    wertAufmerksamkeit.setSelectedIndex(findeAttribut("Aufmerksamkeit"));
    wertGutmuetigkeit.setSelectedIndex(findeAttribut("Gutmütigkeit"));
    wertNervenstaerke.setSelectedIndex(findeAttribut("Nervenstärke"));
    wertIntelligenz.setSelectedIndex(findeAttribut("Intelligenz"));
    wertSiegeswille.setSelectedIndex(findeAttribut("Siegeswille"));
    wertFurchtlosigkeit.setSelectedIndex(findeAttribut("Furchtlosigkeit"));
    wertSozialverhalten.setSelectedIndex(findeAttribut("Sozialverhalten"));
  } // end of ButtonTextAnalysieren_ActionPerformed
  
  public void wertKondition_FocusGained(FocusEvent evt) {
    wertKondition.setText("");
  } 
  
  public void wertDressur_FocusGained(FocusEvent evt) {
    wertDressur.setText("");
  } 
  
  public void wertWestern_FocusGained(FocusEvent evt) {
    wertWestern.setText("");
  } 
  
  public void wertRennen_FocusGained(FocusEvent evt) {
    wertRennen.setText("");
  } 
  
  public void wertFahren_FocusGained(FocusEvent evt) {
    wertFahren.setText("");
  }         
  
  public void wertGrundgangarten_FocusGained(FocusEvent evt) {
    wertGrundgangarten.setText("");
  } 
  
  public void wertWendigkeit_FocusGained(FocusEvent evt) {
    wertWendigkeit.setText("");
  } 
  
  public void wertGelassenheit_FocusGained(FocusEvent evt) {
    wertGelassenheit.setText("");
  } 
  
  public void wertTempo_FocusGained(FocusEvent evt) {
    wertTempo.setText("");
  }

  public void wertSpringen_FocusGained(FocusEvent evt) {
    wertSpringen.setText("");
  } 
  
  public void ButtonZurueck_ActionPerformed(ActionEvent evt) {
    new Menue();
    this.dispose();
  } 
  
  public void ButtonBeenden_ActionPerformed(ActionEvent evt) {
    this.dispose();
  }
  
  public void ButtonTextLoeschen_ActionPerformed(ActionEvent evt) {
    textInput.setText("");
  } 
  
  // Ende Methoden
  
  
  public String findeWert(String gesuchterWert){
    int start = 0;
    for(int i=0; i<splitResult.length; i++){
      if(splitResult[i].contains(gesuchterWert)){
        return splitResult[i+2].substring(0, splitResult[i+2].length()-1);
      }
    }
    return "";
  }
  
  public int findeAttribut(String eigenschaft){
    int positionEigenschaft = 0;
    for (int i = 0; i<splitResult.length; i++) {
      if (splitResult[i].contains(eigenschaft)) {
        positionEigenschaft=i;
        break;
      } 
    }
    
    String[] split2 = splitResult[positionEigenschaft].split("\t");
    for (int i=0; i<split2.length; i++) {
      if (split2[i].equals("Exzellent")) {
        return 4;
      } 
      else if (split2[i].equals("Gut")) {
        return 3;
      } 
      else if (split2[i].equals("In Ordnung")) {
        return 2;
      } 
      else if (split2[i].equals("Schlecht")) {
        return 1;
      } else if (split2[i].equals("Miserabel")) {
        return 0;
      }
    }
    return 0;
  }
  
  public static void main(String[] args) {
    new Rechnerfenster("MDR-Hilfen");
  } // end of main
} // end of class Rechnerfenster
