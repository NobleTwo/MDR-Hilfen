package wettbewerbsrechner;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import allgemein.JNumberField;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 03.10.2014
  * @author 
  */

public class PopUpWettbewerbschancen extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
// Anfang Attribute
  private JLabel jlabel1 = new JLabel();
  private JLabel jlabel2 = new JLabel();
  private JLabel jlabel3 = new JLabel();
  private JLabel jlabel4 = new JLabel();
  private JLabel jlabel5 = new JLabel();
  private JLabel jlabel6 = new JLabel();
  private JLabel jlabel7 = new JLabel();
  private JLabel jlabel8 = new JLabel();
  public JLabel[] klassen= new JLabel[5];
  public JNumberField[][] chancen;
  
  private JButton jButton1 = new JButton();
  // Ende Attribute
  
  public PopUpWettbewerbschancen(String title, Wettbewerbsrechner w) { 
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 433; 
    int frameHeight = 273;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    jlabel1.setBounds(8, 8, 51, 17);
    jlabel1.setText("Klasse");
    jlabel1.setFont(new Font("Arial", Font.BOLD, 12));
    cp.add(jlabel1);
    jlabel2.setBounds(8, 32, 51, 17);
    jlabel2.setText("Disziplin");
    jlabel2.setFont(new Font("Arial", Font.BOLD, 12));
    cp.add(jlabel2);
    jlabel3.setBounds(24, 56, 51, 17);
    jlabel3.setText("Dressur");
    jlabel3.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jlabel3);
    jlabel4.setBounds(24, 80, 75, 17);
    jlabel4.setText("Vielseitigkeit");
    jlabel4.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jlabel4);
    jlabel5.setBounds(24, 104, 51, 17);
    jlabel5.setText("Springen");
    jlabel5.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jlabel5);
    jlabel6.setBounds(24, 128, 51, 17);
    jlabel6.setText("Western");
    jlabel6.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jlabel6);
    jlabel7.setBounds(24, 152, 51, 17);
    jlabel7.setText("Rennen");
    jlabel7.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jlabel7);
    jlabel8.setBounds(24, 176, 51, 17);
    jlabel8.setText("Fahren");
    jlabel8.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jlabel8);
    jButton1.setBounds(312, 208, 99, 25);
    jButton1.setText("Schlieﬂen");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
      }
    });
    cp.add(jButton1);
    cp.setBackground(new Color(0xB8CFE5));
    // Ende Komponenten
    
    chancen = new JNumberField[w.wettbewerbschancen.length][w.wettbewerbschancen[0].length];
    
    for(int i=0; i<klassen.length; i++){
      klassen[i]=new JLabel();
      klassen[i].setBounds(120+i*60, 8, 51, 17);
      klassen[i].setFont(new Font("Arial", Font.BOLD, 12));
      klassen[i].setHorizontalAlignment(SwingConstants.CENTER);
      cp.add(klassen[i]);                      
    }  
    klassen[0].setText("E");   
    klassen[1].setText("A");
    klassen[2].setText("L");   
    klassen[3].setText("M");
    klassen[4].setText("S");    
    
    double maximum = 0;
    
    for(int i=0; i<chancen.length; i++){
      for (int j=0; j<chancen[0].length; j++) {
        chancen[i][j]=new JNumberField();
        chancen[i][j].setBounds(120+j*60, 56+i*24, 51, 17);
        
        if(w.wettbewerbschancen[i][j]==-100){
          chancen[i][j].setText("X");
          chancen[i][j].setEnabled(false);
          chancen[i][j].setHorizontalAlignment(SwingConstants.CENTER);
        }
        else{
          chancen[i][j].setText(w.wettbewerbschancen[i][j]);
          if(Double.parseDouble(chancen[i][j].getText())>maximum){
            maximum=Double.parseDouble(chancen[i][j].getText());
          }
        }
        
        chancen[i][j].setEditable(false);
        cp.add(chancen[i][j]);
      } 
    }
    
    for(int i=0; i<chancen.length; i++){
      for(int j=0; j<chancen[0].length; j++){
        if(chancen[i][j].isEnabled()==true && Double.parseDouble(chancen[i][j].getText())==maximum){
          chancen[i][j].setBackground(Color.ORANGE);
        }
      }
    }
    
    setVisible(true);
  } // end of public PopUpWettbewerbschancen
  
  // Anfang Methoden
  public double verkuerze(double zahl, int stellen){
    zahl=zahl*Math.pow(10,stellen);
    zahl=Math.round(zahl);
    zahl=zahl/Math.pow(10, stellen);
    return zahl;
  }
  public void jButton1_ActionPerformed(ActionEvent evt) {
    this.dispose();
  } // end of jButton1_ActionPerformed
  
  // Ende Methoden
  
  public static void main(String[] args) {
    new PopUpWettbewerbschancen("Wettbewerbschancen", new Wettbewerbsrechner(new Wettbewerbspferd()));
  } // end of main
  
} // end of class Wettbewerbschancen
