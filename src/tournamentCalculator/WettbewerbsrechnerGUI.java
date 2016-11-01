package tournamentCalculator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import general.MDRFrame;
import general.MDRNumberField;
import general.MainMenu;

@SuppressWarnings("unchecked")
public class WettbewerbsrechnerGUI extends MDRFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MDRNumberField[] numberFieldWert = new MDRNumberField[10];
	private JComboBox<String>[] comboBoxWert = new JComboBox[10];
	private JTextArea textInput = new JTextArea("");  
	private JTextField textfieldHorseName = new JTextField();
	
	private final String[] kategories = new String[]{"Miserabel", "Schlecht", "In Ordnung", "Gut", "Exzellent"};
  
	public WettbewerbsrechnerGUI() { 
		//Frame-Initialisierung
		super("Wettbewerbsrechner");

	    final Font fontLabel = new Font("Dialog", Font.PLAIN, 12);
	    final int widthComboBox = 90;
	    final int xGrundlagen = 6*gridButtonGap+widthLabel+widthComboBox;
	    final int widthTextArea = 8*gridButtonGap+2*widthLabel+2*widthComboBox;
	    
	    //Komponenten
	    JLabel labelName = new JLabel("Name");
	    int yName = 3*gridButtonGap+20;
	    labelName.setBounds(gridButtonGap, yName, widthLabel, heightLabel);
	    cp.add(labelName);
	    textfieldHorseName.setBounds(gridButtonGap+40, yName, (int)(widthTextArea-40), heightLabel);
	    cp.add(textfieldHorseName);
	    
	    int yDisziplinen = yName + gridButtonGap+buttonHeight;
	    JLabel labelDisziplinen = new JLabel("Disziplinen");
	    labelDisziplinen.setBounds(gridButtonGap, yDisziplinen, widthLabel, heightLabel);
	    cp.add(labelDisziplinen);
	    
	    JLabel[] labelEinzeldisziplinen = new JLabel[5];
	    int xLabelTemp = 3*gridButtonGap+widthLabel+gridButtonGap+widthComboBox-heightLabel/2;
	    for(int i = 0; i<5; i++){
	    	labelEinzeldisziplinen[i] = new JLabel();
	    	int y = yDisziplinen+gridButtonGap/2+heightLabel+i*(heightLabel+gridButtonGap/2);
	        labelEinzeldisziplinen[i].setBounds(3*gridButtonGap, y, widthLabel, heightLabel);
	        labelEinzeldisziplinen[i].setFont(fontLabel);
	        cp.add(labelEinzeldisziplinen[i]);
	        
	    	numberFieldWert[i] = new MDRNumberField(true);
	    	numberFieldWert[i].setBounds(xLabelTemp-heightLabel*3/2-gridButtonGap/2, y, heightLabel*3/2, heightLabel);
	    	cp.add(numberFieldWert[i]);
	        
	        JLabel labelTemp = new JLabel("%");
	        labelTemp.setBounds(xLabelTemp, y, heightLabel/2, heightLabel);
	        cp.add(labelTemp);
	    }
	    labelEinzeldisziplinen[0].setText("Dressur");
	    labelEinzeldisziplinen[1].setText("Springen");
	    labelEinzeldisziplinen[2].setText("Western");
	    labelEinzeldisziplinen[3].setText("Rennen");
	    labelEinzeldisziplinen[4].setText("Fahren");
	    
	    JLabel labelGrundlagen = new JLabel("Grundlagen");
	    labelGrundlagen.setBounds(xGrundlagen, yDisziplinen, widthLabel, heightLabel);
	    cp.add(labelGrundlagen);
	    
	    JLabel[] labelEinzelgrundlagen = new JLabel[5];
	    xLabelTemp = xGrundlagen+2*gridButtonGap+widthLabel+gridButtonGap+widthComboBox-heightLabel/2;
	    for(int i=0;i<5;i++){
	    	labelEinzelgrundlagen[i] = new JLabel();
	    	int y = yDisziplinen+gridButtonGap/2+heightLabel+i*(heightLabel+gridButtonGap/2);
	    	labelEinzelgrundlagen[i].setBounds(xGrundlagen+2*gridButtonGap, y, widthLabel, heightLabel);
	    	labelEinzelgrundlagen[i].setFont(fontLabel);
	        cp.add(labelEinzelgrundlagen[i]);
	    	
	    	numberFieldWert[i+5] = new MDRNumberField(true);
	    	numberFieldWert[i+5].setBounds(xLabelTemp-gridButtonGap/2-heightLabel*3/2, y, heightLabel*3/2, heightLabel);
	    	cp.add(numberFieldWert[i+5]);
	        
	        JLabel labelTemp = new JLabel("%");
	        labelTemp.setBounds(xLabelTemp, y, heightLabel/2, heightLabel);
	        cp.add(labelTemp);
	    }
	    labelEinzelgrundlagen[0].setText("Grundgangarten");
	    labelEinzelgrundlagen[1].setText("Wendigkeit");
	    labelEinzelgrundlagen[2].setText("Gelassenheit");
	    labelEinzelgrundlagen[3].setText("Tempo");
	    labelEinzelgrundlagen[4].setText("Kondition");
	    
	    JLabel labelInterieur = new JLabel("Interieur");
	    int yInterieur = (int)(yDisziplinen + 6*heightLabel+3.5*gridButtonGap);
	    labelInterieur.setBounds(gridButtonGap, yInterieur, 51, 17);
	    cp.add(labelInterieur);
	    
	    JLabel[] labelEinzelinterieurs = new JLabel[10];
	    for(int i=0;i<5;i++){
	    	int y = yInterieur + (gridButtonGap/2+heightLabel)*(i+1);
	    	labelEinzelinterieurs[i] = new JLabel();
	    	labelEinzelinterieurs[i+5] = new JLabel();
	    	labelEinzelinterieurs[i].setBounds(3*gridButtonGap, y, widthLabel, heightLabel);
	    	labelEinzelinterieurs[i+5].setBounds(xGrundlagen+2*gridButtonGap, y, widthLabel, heightLabel);
	    	labelEinzelinterieurs[i].setFont(fontLabel);
	    	labelEinzelinterieurs[i+5].setFont(fontLabel);
	        cp.add(labelEinzelinterieurs[i]);
	        cp.add(labelEinzelinterieurs[i+5]);
	        
	        comboBoxWert[i] = new JComboBox<String>();
	        comboBoxWert[i].setBounds(4*gridButtonGap+widthLabel, y-gridButtonGap/4, widthComboBox, heightLabel+gridButtonGap/2);
	        comboBoxWert[i].setModel(new DefaultComboBoxModel<String>(kategories));
	        cp.add(comboBoxWert[i]);
	        comboBoxWert[i+5] = new JComboBox<String>();
	        comboBoxWert[i+5].setBounds(xGrundlagen+3*gridButtonGap+widthLabel, y-gridButtonGap/4, widthComboBox, heightLabel+gridButtonGap/2);
	        comboBoxWert[i+5].setModel(new DefaultComboBoxModel<String>(kategories));
	        cp.add(comboBoxWert[i+5]);
	    }
	    labelEinzelinterieurs[0].setText("Temperament");
	    labelEinzelinterieurs[1].setText("Gelehrigkeit");
	    labelEinzelinterieurs[2].setText("Leistungsbereitschaft");
	    labelEinzelinterieurs[3].setText("Aufmerksamkeit");
	    labelEinzelinterieurs[4].setText("Gutmütigkeit");
	    labelEinzelinterieurs[5].setText("Nervenstärke");
	    labelEinzelinterieurs[6].setText("Intelligenz");
	    labelEinzelinterieurs[7].setText("Siegeswille");
	    labelEinzelinterieurs[8].setText("Furchtlosigkeit");
	    labelEinzelinterieurs[9].setText("Sozialverhalten");
	
	    JButton buttonCalcChances = new JButton("Wettbewerbschancen berechnen");
	    int yButtonCalc = (int)(yInterieur + 4*gridButtonGap + 6*heightLabel);
	    buttonCalcChances.setBounds(gridButtonGap, yButtonCalc, widthLabel+3*gridButtonGap+widthComboBox, buttonHeight);
	    buttonCalcChances.addActionListener(new ActionListener() { 
	    	@Override
	    	public void actionPerformed(ActionEvent evt) { 
	    		calcChances();
	    	}
	    });
	    cp.add(buttonCalcChances);
	    
	    JButton buttonGoToMenu = new JButton("Hauptmenü");
	    int widthButtonRight = (widthTextArea-gridButtonGap)/2;
	    int xTextArea = xLabelTemp+heightLabel+2*gridButtonGap;
	    buttonGoToMenu.setBounds(xTextArea, yName, widthButtonRight, buttonHeight);
	    buttonGoToMenu.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent evt) { 
	    		new MainMenu();
	    		dispose();
	    	}
	    });
	    cp.add(buttonGoToMenu);
	    
	    JButton buttonClose = new JButton("Schließen");
	    buttonClose.setBounds(xTextArea+widthButtonRight+gridButtonGap, yName, widthButtonRight, buttonHeight);
	    buttonClose.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent evt) { 
	    		dispose();
	    	}
	    });
	    cp.add(buttonClose);
	    
	    JScrollPane textInputScrollPane =  new JScrollPane(textInput);
	    textInputScrollPane.setBounds(xTextArea, yDisziplinen, widthTextArea, yButtonCalc-yDisziplinen-gridButtonGap);
	    cp.add(textInputScrollPane);
	    
	    JButton buttonAnalyzeText = new JButton("Text analysieren");
	    buttonAnalyzeText.setBounds(xTextArea, yButtonCalc, widthButtonRight, buttonHeight);
	    buttonAnalyzeText.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent evt) { 
	    		analyzeText();
	    	}
	    });
	    cp.add(buttonAnalyzeText);
	    
	    JButton buttonDeleteText = new JButton("Text löschen");
	    buttonDeleteText.setBounds(xTextArea+widthButtonRight+gridButtonGap, yButtonCalc, widthButtonRight, buttonHeight);
	    buttonDeleteText.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent evt) { 
	    		textInput.setText("");
	    	}
	    });
	    cp.add(buttonDeleteText);
	    
	    int frameWidth = (int)(2*widthTextArea+5.5*gridButtonGap); 
	    int frameHeight = (int)(yButtonCalc+buttonHeight+4*gridButtonGap);
	    setSize(frameWidth, frameHeight);
	    super2();
	}
  
  // Anfang Methoden
  public void calcChances() {
    
    double[] disziplinenwerte = new double[5];
    double[] grundlagenwerte = new double[5];
    String[] interieurwerte = new String[10];
    
    for(int i=0; i<5; i++){
    	disziplinenwerte[i]=Double.parseDouble(numberFieldWert[i].getText()); 
    }
    
    for(int i=0; i<5; i++){
    	grundlagenwerte[i]=Double.parseDouble(numberFieldWert[i+5].getText()); 
    }
    
    for(int i=0;i<10;i++){
    	interieurwerte[i]=String.valueOf(comboBoxWert[i].getSelectedItem());
    }
    
    Wettbewerbspferd p = new Wettbewerbspferd();
    p.weiseWertezu(disziplinenwerte, grundlagenwerte, interieurwerte);  
    Wettbewerbsrechner w = new Wettbewerbsrechner(p);
    
    new PopUpWettbewerbschancen(textfieldHorseName.getText(), w);
    
    
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
  
  public void analyzeText(){
    String text = textInput.getText();
    String[] splitResult = text.split("\n");
    
    int nameIndex = 0;
    for(int i=0; i<splitResult.length; i++){
      if(splitResult[i].contains("Gesamtpotential") && !splitResult[i].contains("Potential")){
        nameIndex = i;
        break;
      }
    }
    textfieldHorseName.setText(splitResult[nameIndex-3]);
    
    numberFieldWert[0].setText(findeWert("Dressur", splitResult));
    numberFieldWert[1].setText(findeWert("Springen", splitResult));
    numberFieldWert[2].setText(findeWert("Western", splitResult));
    numberFieldWert[3].setText(findeWert("Rennen", splitResult));
    numberFieldWert[4].setText(findeWert("Fahren", splitResult));
    numberFieldWert[5].setText(findeWert("Grundgangarten", splitResult));
    numberFieldWert[6].setText(findeWert("Wendigkeit", splitResult));
    numberFieldWert[7].setText(findeWert("Gelassenheit", splitResult));
    numberFieldWert[8].setText(findeWert("Tempo", splitResult));
    numberFieldWert[9].setText(findeWert("Kondition", splitResult)); 
    
    comboBoxWert[0].setSelectedIndex(findeAttribut("Temperament", splitResult)); 
    comboBoxWert[1].setSelectedIndex(findeAttribut("Gelehrigkeit", splitResult));
    comboBoxWert[2].setSelectedIndex(findeAttribut("Leistungsbereitschaft", splitResult));
    comboBoxWert[3].setSelectedIndex(findeAttribut("Aufmerksamkeit", splitResult));
    comboBoxWert[4].setSelectedIndex(findeAttribut("Gutmütigkeit", splitResult));
    comboBoxWert[5].setSelectedIndex(findeAttribut("Nervenstärke", splitResult));
    comboBoxWert[6].setSelectedIndex(findeAttribut("Intelligenz", splitResult));
    comboBoxWert[7].setSelectedIndex(findeAttribut("Siegeswille", splitResult));
    comboBoxWert[8].setSelectedIndex(findeAttribut("Furchtlosigkeit", splitResult));
    comboBoxWert[9].setSelectedIndex(findeAttribut("Sozialverhalten", splitResult));
  }
  
  
  public String findeWert(String gesuchterWert, String[] splitResult){
    for(int i=0; i<splitResult.length; i++){
      if(splitResult[i].contains(gesuchterWert)){
        return splitResult[i+2].substring(0, splitResult[i+2].length()-1);
      }
    }
    return "";
  }
  
  public int findeAttribut(String eigenschaft, String[] splitResult){
    int positionEigenschaft = 0;
    for (int i = 0; i<splitResult.length; i++) {
      if (splitResult[i].contains(eigenschaft)) {
        positionEigenschaft=i;
        break;
      } 
    }
    
    String[] split2 = splitResult[positionEigenschaft].split("\t");
    for (int i=0; i<split2.length; i++) {
    	for(int j=0;j<kategories.length;j++){
    		if (split2[i].equals(kategories[j])) {
    			return j;
    	    } 
    	}
    }
    return 0;
  }
  
  public static void main(String[] args) {
    new WettbewerbsrechnerGUI();
  }
}
