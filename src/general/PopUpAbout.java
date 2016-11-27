package general;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class PopUpAbout extends MDRDialog {
  
  public PopUpAbout(MainMenu mainMenuFrame) { 
    //Frame-Initialisierung
    super(mainMenuFrame, "MDR-Hilfen Ver. 1.1");
    setSize(380, 215);
    Container cp = getContentPane();
    
    //Komponenten
    final int widthLabel = 355;
    
    JLabel labelReleaseDate = new JLabel("Release: 12.10.2016");
    labelReleaseDate.setBounds(gridButtonGap, yTop, widthLabel, 19);
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
    
    super2();
  }
  
  public static void main(String[] args) {
    new PopUpAbout(new MainMenu());
  }
}
