package allgemein;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class PopUpAbout extends MDRFrame {
  
  public PopUpAbout() { 
    //Frame-Initialisierung
    super("Über");
    setSize(380, 220);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    Container cp = getContentPane();
    
    final Font fontLabel = new Font("Arial", Font.PLAIN, 10);
    //Komponenten
    final int widthLabel = 355;
    JLabel labelTitle = new JLabel("MDR-Hilfen Ver. 1.1");
    labelTitle.setBounds(gridButtonGap, 8, widthLabel, 19);
    labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(labelTitle);
    
    JLabel labelReleaseDate = new JLabel("Release: 12.10.2016");
    labelReleaseDate.setBounds(gridButtonGap, 32, widthLabel, 19);
    labelReleaseDate.setFont(fontLabel);
    labelReleaseDate.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(labelReleaseDate);
    
    JLabel labelMDRVersion = new JLabel("Basierend auf MDR, Version 3.0, Stand 11.10.2016");
    labelMDRVersion.setBounds(gridButtonGap, 64, widthLabel, 25);
    labelMDRVersion.setFont(fontLabel);
    labelMDRVersion.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(labelMDRVersion);
    
    JLabel labelWarning = new JLabel("Es können Fehler bei Verwendung mit anderen MDR-Versionen auftreten.");
    labelWarning.setBounds(gridButtonGap, 88, widthLabel, 17);
    labelWarning.setFont(fontLabel);
    labelWarning.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(labelWarning);
    
    JLabel labelCopyright = new JLabel("\u00A9 nola Linys Freund, 2014-2016.");
    labelCopyright.setBounds(gridButtonGap, 120, widthLabel, 17);
    labelCopyright.setFont(fontLabel);
    labelCopyright.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(labelCopyright);
    
    JButton buttonClose = new JButton("Schließen");
    buttonClose.setBounds(144, 152, 105, buttonHeight);
    buttonClose.addActionListener(new ActionListener() {
    	@Override
    	public void actionPerformed(ActionEvent evt) { 
    		dispose();
    	}
    });
    cp.add(buttonClose);
    setVisible(true);
  }
  
  public static void main(String[] args) {
    new PopUpAbout();
  }
}
