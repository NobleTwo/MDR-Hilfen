package allgemein;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import verwandschaftscheck.RCGUIdatabase;
import verwandschaftscheck.RelativeCheckGUI;
import wettbewerbsrechner.Rechnerfenster;
/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 15.08.2014
  * @author 
  */

public class Menue extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
// Anfang Attribute
  private JButton ButtonWettbewerbsrechner = new JButton();
  private JButton ButtonVerwandtschaftscheck = new JButton();
  private JButton ButtonBeenden = new JButton();
  private JButton ButtonAbout = new JButton();
  private JButton ButtonDatenbank = new JButton();
  private JButton ButtonVerwandtschaftscheckReal = new JButton();
  // Ende Attribute
  
  public Menue() { 
    // Frame-Initialisierung
    super("MDR-Hilfen");
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 238; 
    int frameHeight = 160;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    ButtonWettbewerbsrechner.setBounds(8, 8, 217, 25);
    ButtonWettbewerbsrechner.setText("Wettbewerbsrechner");
    ButtonWettbewerbsrechner.setMargin(new Insets(2, 2, 2, 2));
    ButtonWettbewerbsrechner.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonWettbewerbsrechner_ActionPerformed(evt);
      }
    });
    cp.add(ButtonWettbewerbsrechner);
    ButtonVerwandtschaftscheck.setBounds(8, 40, 217, 25);
    ButtonVerwandtschaftscheck.setText("Verwandtschaftscheck");
    ButtonVerwandtschaftscheck.setMargin(new Insets(2, 2, 2, 2));
    ButtonVerwandtschaftscheck.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonVerwandtschaftscheck_ActionPerformed(evt);
      }
    });
    ButtonVerwandtschaftscheck.setEnabled(true);
    cp.add(ButtonVerwandtschaftscheck);
    ButtonBeenden.setBounds(8, 88, 185, 25);
    ButtonBeenden.setText("Beenden");
    ButtonBeenden.setMargin(new Insets(2, 2, 2, 2));
    ButtonBeenden.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonBeenden_ActionPerformed(evt);
      }
    });
    cp.add(ButtonBeenden);
    ButtonAbout.setBounds(200, 88, 25, 25);
    ButtonAbout.setText("?");
    ButtonAbout.setMargin(new Insets(2, 2, 2, 2));
    ButtonAbout.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonAbout_ActionPerformed(evt);
      }
    });
    cp.add(ButtonAbout);
    cp.setBackground(new Color(0xB8CFE5));
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Menue
  
  // Anfang Methoden
  public void ButtonWettbewerbsrechner_ActionPerformed(ActionEvent evt) {
    new Rechnerfenster("MDR-Hilfen");
    this.dispose();
  } // end of ButtonWettbewerbsrechner_ActionPerformed
  
  public void ButtonVerwandtschaftscheck_ActionPerformed(ActionEvent evt) {
    ButtonVerwandtschaftscheck.setEnabled(false);
    ButtonBeenden.setBounds(8, 148, 185, 25);
    ButtonAbout.setBounds(200, 148, 25, 25);
    setSize(238, 213);

    Container cp = getContentPane();
    ButtonDatenbank.setBounds(40, 70, 153, 25);
    ButtonDatenbank.setText("Datenbankverwaltung");
    ButtonDatenbank.setMargin(new Insets(2, 2, 2, 2));
    ButtonDatenbank.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonDatenbank_ActionPerformed(evt);
      }
    });
    cp.add(ButtonDatenbank);
    ButtonVerwandtschaftscheckReal.setBounds(40, 100, 153, 25);
    ButtonVerwandtschaftscheckReal.setText("Verwandtschaftscheck");
    ButtonVerwandtschaftscheckReal.setMargin(new Insets(2, 2, 2, 2));
    ButtonVerwandtschaftscheckReal.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonVerwandtschaftscheckReal_ActionPerformed(evt);
      }
    });
    cp.add(ButtonVerwandtschaftscheckReal);
  }
  
  public void ButtonBeenden_ActionPerformed(ActionEvent evt) {
    this.dispose();
  }
  
  public void ButtonAbout_ActionPerformed(ActionEvent evt) {
    new PopUpAbout("About");
  }
  
  public void ButtonVerwandtschaftscheckReal_ActionPerformed(ActionEvent evt){
    new RelativeCheckGUI();
    this.dispose();
  }

  public void ButtonDatenbank_ActionPerformed(ActionEvent evt){
    new RCGUIdatabase();
    this.dispose();
  }
  
  public static void main(String[] args) throws Exception {
    new Menue();
  }
}
