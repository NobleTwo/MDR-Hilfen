package allgemein;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class PopUpAbout extends JFrame {
  // Anfang Attribute
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JButton jButton1 = new JButton();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  // Ende Attribute
  
  public PopUpAbout(String title) { 
    // Frame-Initialisierung
    super("Über");
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 383; 
    int frameHeight = 221;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    jLabel1.setBounds(8, 8, 358, 19);
    jLabel1.setText("MDR-Hilfen Ver. 1.0.2");
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel1);
    jLabel2.setBounds(8, 120, 353, 17);
    jLabel2.setText("Copyright: nolaLinys Freund, 2014-2015.");
    jLabel2.setFont(new Font("Arial", Font.PLAIN, 10));
    jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel2);
    jButton1.setBounds(144, 152, 105, 25);
    jButton1.setText("Schließen");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
      }
    });
    cp.add(jButton1);
    jLabel3.setBounds(8, 32, 358, 19);
    jLabel3.setText("Release: 21.08.15");
    jLabel3.setFont(new Font("Arial", Font.PLAIN, 10));
    jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel3);
    jLabel4.setBounds(8, 64, 356, 25);
    jLabel4.setText("Basierend auf MDR, Version 3.0, Stand 08.05.15");
    jLabel4.setFont(new Font("Arial", Font.PLAIN, 10));
    jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel4);
    jLabel5.setBounds(8, 88, 355, 17);
    jLabel5.setText("Es können Fehler bei Verwendung mit anderen MDR-Versionen auftreten.");
    jLabel5.setFont(new Font("Arial", Font.PLAIN, 10));
    jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel5);
    cp.setBackground(new Color(0xB8CFE5));
    // Ende Komponenten
    
    setVisible(true);
  } // end of public PopUpAbout
  
  // Anfang Methoden
  public void jButton1_ActionPerformed(ActionEvent evt) {
    this.dispose();
  } // end of jButton1_ActionPerformed
  
  // Ende Methoden
  
  public static void main(String[] args) {
    new PopUpAbout("PopUpAbout");
  } // end of main
  
} // end of class PopUpAbout
