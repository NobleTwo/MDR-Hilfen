package verwandschaftscheck;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import allgemein.Menue;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 04.03.2015
  * @author 
  */

public class RelativeCheckGUI extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Color green = new Color(116, 235, 95);
  Color orange = new Color(245, 172, 73);
  Color red = new Color(241, 88, 50);

  private DocumentManager dm;
  public JTextField[] horseNamesR;
  public JTextField[] horseNamesL;
  public JComboBox<String>[] horseRacesR;
  public JComboBox<String>[] horseRacesL;
  public JTextField[][] allNames;
  public JComboBox<String>[][] allRaces;
  private JLabel[][] genders;
  // Anfang Attribute
  private JTextField jTextField7 = new JTextField();
  private JTextField jTextField9 = new JTextField();
  private JTextField jTextField10 = new JTextField();
  private JTextField jTextField11 = new JTextField();
  private JTextField jTextField12 = new JTextField();
  private JTextField jTextField13 = new JTextField();
  private JTextField jTextField14 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private JTextField jTextField5 = new JTextField();
  private JTextField jTextField6 = new JTextField();
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField4 = new JTextField();
  private JTextField jTextField8 = new JTextField();
  private JTextField jTextField0 = new JTextField();
  private JComboBox<String> jComboBox0 = new JComboBox<String>();
  private JComboBox<String> jComboBox1 = new JComboBox<String>();
  private JComboBox<String> jComboBox2 = new JComboBox<String>();
  private JComboBox<String> jComboBox3 = new JComboBox<String>();
  private JComboBox<String> jComboBox4 = new JComboBox<String>();
  private JComboBox<String> jComboBox5 = new JComboBox<String>();
  private JComboBox<String> jComboBox6 = new JComboBox<String>();
  private JComboBox<String> jComboBox7 = new JComboBox<String>();
  private JComboBox<String> jComboBox8 = new JComboBox<String>();
  private JComboBox<String> jComboBox9 = new JComboBox<String>();
  private JComboBox<String> jComboBox10 = new JComboBox<String>();
  private JComboBox<String> jComboBox11 = new JComboBox<String>();
  private JComboBox<String> jComboBox12 = new JComboBox<String>();
  private JComboBox<String> jComboBox13 = new JComboBox<String>();
  private JComboBox<String> jComboBox14 = new JComboBox<String>();
  private JTextField jTextField22 = new JTextField();
  private JTextField jTextField24 = new JTextField();
  private JTextField jTextField25 = new JTextField();
  private JTextField jTextField26 = new JTextField();
  private JTextField jTextField27 = new JTextField();
  private JTextField jTextField28 = new JTextField();
  private JTextField jTextField29 = new JTextField();
  private JTextField jTextField18 = new JTextField();
  private JTextField jTextField20 = new JTextField();
  private JTextField jTextField21 = new JTextField();
  private JTextField jTextField16 = new JTextField();
  private JTextField jTextField17 = new JTextField();
  private JTextField jTextField19 = new JTextField();
  private JTextField jTextField23 = new JTextField();
  private JTextField jTextField15 = new JTextField();
  private JComboBox<String> jComboBox15 = new JComboBox<String>();
  private JComboBox<String> jComboBox16 = new JComboBox<String>();
  private JComboBox<String> jComboBox17 = new JComboBox<String>();
  private JComboBox<String> jComboBox18 = new JComboBox<String>();
  private JComboBox<String> jComboBox19 = new JComboBox<String>();
  private JComboBox<String> jComboBox20 = new JComboBox<String>();
  private JComboBox<String> jComboBox21 = new JComboBox<String>();
  private JComboBox<String> jComboBox22 = new JComboBox<String>();
  private JComboBox<String> jComboBox23 = new JComboBox<String>();
  private JComboBox<String> jComboBox24 = new JComboBox<String>();
  private JComboBox<String> jComboBox25 = new JComboBox<String>();
  private JComboBox<String> jComboBox26 = new JComboBox<String>();
  private JComboBox<String> jComboBox27 = new JComboBox<String>();
  private JComboBox<String> jComboBox28 = new JComboBox<String>();
  private JComboBox<String> jComboBox29 = new JComboBox<String>();
  private JButton editDatabase = new JButton();
  private JButton ButtonAnalyze = new JButton();
  private JButton changeLeft = new JButton();
  private JButton changeRight = new JButton();
  private JButton ButtonExit = new JButton();
  private JButton ButtonGoToMenu = new JButton();
  private JLabel jLabel1 = new JLabel();
  private JRadioButton radioButtonIsMaleLeft = new JRadioButton();
  private JRadioButton radioButtonIsFemaleLeft = new JRadioButton();
  private JLabel labelIsMaleLeft = new JLabel();
  private JLabel labelIsFemaleLeft = new JLabel();
  private JRadioButton radioButtonIsMaleRight = new JRadioButton();
  private JRadioButton radioButtonIsFemaleRight = new JRadioButton();
  private JLabel labelIsMaleRight = new JLabel();
  private JLabel labelIsFemaleRight = new JLabel();
  // Ende Attribute
  
  @SuppressWarnings("unchecked")
public RelativeCheckGUI() { 
    // Frame-Initialisierung
    super("MDR-Hilfen");
    dm = new DocumentManager();

    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 1084; 
    int frameHeight = 581;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    jTextField7.setBounds(400, 48, 121, 33);
    jTextField7.setEnabled(true);
    jTextField7.setEditable(false);
    cp.add(jTextField7);
    jTextField9.setBounds(400, 160, 121, 33);
    jTextField9.setEnabled(true);
    jTextField9.setEditable(false);
    cp.add(jTextField9);
    jTextField10.setBounds(400, 216, 121, 33);
    jTextField10.setEnabled(true);
    jTextField10.setEditable(false);
    cp.add(jTextField10);
    jTextField11.setBounds(400, 272, 121, 33);
    jTextField11.setEnabled(true);
    jTextField11.setEditable(false);
    cp.add(jTextField11);
    jTextField12.setBounds(400, 328, 121, 33);
    jTextField12.setEnabled(true);
    jTextField12.setEditable(false);
    cp.add(jTextField12);
    jTextField13.setBounds(400, 384, 121, 33);
    jTextField13.setEnabled(true);
    jTextField13.setEditable(false);
    cp.add(jTextField13);
    jTextField14.setBounds(400, 440, 121, 33);
    jTextField14.setEnabled(true);
    jTextField14.setEditable(false);
    cp.add(jTextField14);
    jTextField3.setBounds(272, 80, 121, 33);
    jTextField3.setEnabled(true);
    jTextField3.setEditable(false);
    cp.add(jTextField3);
    jTextField5.setBounds(272, 296, 121, 33);
    jTextField5.setEnabled(true);
    jTextField5.setEditable(false);
    cp.add(jTextField5);
    jTextField6.setBounds(272, 416, 121, 33);
    jTextField6.setEnabled(true);
    jTextField6.setEditable(false);
    cp.add(jTextField6);
    jTextField1.setBounds(144, 136, 121, 33);
    jTextField1.setEnabled(true);
    jTextField1.setEditable(false);
    cp.add(jTextField1);
    jTextField2.setBounds(144, 352, 121, 33);
    jTextField2.setEnabled(true);
    jTextField2.setEditable(false);
    cp.add(jTextField2);
    jTextField4.setBounds(272, 192, 121, 33);
    jTextField4.setEnabled(true);
    jTextField4.setEditable(false);
    cp.add(jTextField4);
    jTextField8.setBounds(400, 104, 121, 33);
    jTextField8.setEnabled(true);
    jTextField8.setEditable(false);
    cp.add(jTextField8);
    jTextField0.setBounds(16, 248, 121, 33);
    jTextField0.setEnabled(true);
    jTextField0.setEditable(false);
    cp.add(jTextField0);
    jComboBox0.setBounds(16, 280, 121, 17);
    jComboBox0.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox0.setEnabled(true);
    jComboBox0.setEditable(false);
    cp.add(jComboBox0);
    jComboBox1.setBounds(144, 168, 121, 17);
    jComboBox1.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox1.setEnabled(true);
    jComboBox1.setEditable(false);
    cp.add(jComboBox1);
    jComboBox2.setBounds(144, 384, 121, 17);
    jComboBox2.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox2.setEnabled(true);
    jComboBox2.setEditable(false);
    cp.add(jComboBox2);
    jComboBox3.setBounds(272, 112, 121, 17);
    jComboBox3.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox3.setEnabled(true);
    jComboBox3.setEditable(false);
    cp.add(jComboBox3);
    jComboBox4.setBounds(272, 224, 121, 17);
    jComboBox4.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox4.setEnabled(true);
    jComboBox4.setEditable(false);
    cp.add(jComboBox4);
    jComboBox5.setBounds(272, 328, 121, 17);
    jComboBox5.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox5.setEnabled(true);
    jComboBox5.setEditable(false);
    cp.add(jComboBox5);
    jComboBox6.setBounds(272, 448, 121, 17);
    jComboBox6.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox6.setEnabled(true);
    jComboBox6.setEditable(false);
    cp.add(jComboBox6);
    jComboBox7.setBounds(400, 80, 121, 17);
    jComboBox7.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox7.setEnabled(true);
    jComboBox7.setEditable(false);
    cp.add(jComboBox7);
    jComboBox8.setBounds(400, 136, 121, 17);
    jComboBox8.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox8.setEnabled(true);
    jComboBox8.setEditable(false);
    cp.add(jComboBox8);
    jComboBox9.setBounds(400, 192, 121, 17);
    jComboBox9.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox9.setEnabled(true);
    jComboBox9.setEditable(false);
    cp.add(jComboBox9);
    jComboBox10.setBounds(400, 248, 121, 17);
    jComboBox10.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox10.setEnabled(true);
    jComboBox10.setEditable(false);
    cp.add(jComboBox10);
    jComboBox11.setBounds(400, 304, 121, 17);
    jComboBox11.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox11.setEnabled(true);
    jComboBox11.setEditable(false);
    cp.add(jComboBox11);
    jComboBox12.setBounds(400, 360, 121, 17);
    jComboBox12.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox12.setEnabled(true);
    jComboBox12.setEditable(false);
    cp.add(jComboBox12);
    jComboBox13.setBounds(400, 416, 121, 17);
    jComboBox13.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox13.setEnabled(true);
    jComboBox13.setEditable(false);
    cp.add(jComboBox13);
    jComboBox14.setBounds(400, 472, 121, 17);
    jComboBox14.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox14.setEnabled(true);
    jComboBox14.setEditable(false);
    cp.add(jComboBox14);
    jTextField22.setBounds(944, 48, 121, 33);
    jTextField22.setEnabled(true);
    jTextField22.setEditable(false);
    cp.add(jTextField22);
    jTextField24.setBounds(944, 160, 121, 33);
    jTextField24.setEnabled(true);
    jTextField24.setEditable(false);
    cp.add(jTextField24);
    jTextField25.setBounds(944, 216, 121, 33);
    jTextField25.setEnabled(true);
    jTextField25.setEditable(false);
    cp.add(jTextField25);
    jTextField26.setBounds(944, 272, 121, 33);
    jTextField26.setEnabled(true);
    jTextField26.setEditable(false);
    cp.add(jTextField26);
    jTextField27.setBounds(944, 328, 121, 33);
    jTextField27.setEnabled(true);
    jTextField27.setEditable(false);
    cp.add(jTextField27);
    jTextField28.setBounds(944, 384, 121, 33);
    jTextField28.setEnabled(true);
    jTextField28.setEditable(false);
    cp.add(jTextField28);
    jTextField29.setBounds(944, 440, 121, 33);
    jTextField29.setEnabled(true);
    jTextField29.setEditable(false);
    cp.add(jTextField29);
    jTextField18.setBounds(816, 80, 121, 33);
    jTextField18.setEnabled(true);
    jTextField18.setEditable(false);
    cp.add(jTextField18);
    jTextField20.setBounds(816, 296, 121, 33);
    jTextField20.setEnabled(true);
    jTextField20.setEditable(false);
    cp.add(jTextField20);
    jTextField21.setBounds(816, 416, 121, 33);
    jTextField21.setEnabled(true);
    jTextField21.setEditable(false);
    cp.add(jTextField21);
    jTextField16.setBounds(688, 136, 121, 33);
    jTextField16.setEnabled(true);
    jTextField16.setEditable(false);
    cp.add(jTextField16);
    jTextField17.setBounds(688, 352, 121, 33);
    jTextField17.setEnabled(true);
    jTextField17.setEditable(false);
    cp.add(jTextField17);
    jTextField19.setBounds(816, 192, 121, 33);
    jTextField19.setEnabled(true);
    jTextField19.setEditable(false);
    cp.add(jTextField19);
    jTextField23.setBounds(944, 104, 121, 33);
    jTextField23.setEnabled(true);
    jTextField23.setEditable(false);
    cp.add(jTextField23);
    jTextField15.setBounds(560, 248, 121, 33);
    jTextField15.setEnabled(true);
    jTextField15.setEditable(false);
    cp.add(jTextField15);
    jComboBox15.setBounds(560, 280, 121, 17);
    jComboBox15.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox15.setEnabled(true);
    jComboBox15.setEditable(false);
    cp.add(jComboBox15);
    jComboBox16.setBounds(688, 168, 121, 17);
    jComboBox16.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox16.setEnabled(true);
    jComboBox16.setEditable(false);
    cp.add(jComboBox16);
    jComboBox17.setBounds(688, 384, 121, 17);
    jComboBox17.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox17.setEnabled(true);
    jComboBox17.setEditable(false);
    cp.add(jComboBox17);
    jComboBox18.setBounds(816, 112, 121, 17);
    jComboBox18.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox18.setEnabled(true);
    jComboBox18.setEditable(false);
    cp.add(jComboBox18);
    jComboBox19.setBounds(816, 224, 121, 17);
    jComboBox19.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox19.setEnabled(true);
    jComboBox19.setEditable(false);
    cp.add(jComboBox19);
    jComboBox20.setBounds(816, 328, 121, 17);
    jComboBox20.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox20.setEnabled(true);
    jComboBox20.setEditable(false);
    cp.add(jComboBox20);
    jComboBox21.setBounds(816, 448, 121, 17);
    jComboBox21.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox21.setEnabled(true);
    jComboBox21.setEditable(false);
    cp.add(jComboBox21);
    jComboBox22.setBounds(944, 80, 121, 17);
    jComboBox22.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox22.setEnabled(true);
    jComboBox22.setEditable(false);
    cp.add(jComboBox22);
    jComboBox23.setBounds(944, 136, 121, 17);
    jComboBox23.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox23.setEnabled(true);
    jComboBox23.setEditable(false);
    cp.add(jComboBox23);
    jComboBox24.setBounds(944, 192, 121, 17);
    jComboBox24.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox24.setEnabled(true);
    jComboBox24.setEditable(false);
    cp.add(jComboBox24);
    jComboBox25.setBounds(944, 248, 121, 17);
    jComboBox25.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox25.setEnabled(true);
    jComboBox25.setEditable(false);
    cp.add(jComboBox25);
    jComboBox26.setBounds(944, 304, 121, 17);
    jComboBox26.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox26.setEnabled(true);
    jComboBox26.setEditable(false);
    cp.add(jComboBox26);
    jComboBox27.setBounds(944, 360, 121, 17);
    jComboBox27.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox27.setEnabled(true);
    jComboBox27.setEditable(false);
    cp.add(jComboBox27);
    jComboBox28.setBounds(944, 416, 121, 17);
    jComboBox28.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox28.setEnabled(true);
    jComboBox28.setEditable(false);
    cp.add(jComboBox28);
    jComboBox29.setBounds(944, 472, 121, 17);
    jComboBox29.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
    jComboBox29.setEnabled(true);
    jComboBox29.setEditable(false);
    cp.add(jComboBox29);
    editDatabase.setBounds(640, 504, 137, 25);
    editDatabase.setText("Datenbank bearbeiten");
    editDatabase.setMargin(new Insets(2, 2, 2, 2));
    editDatabase.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        editDatabase_ActionPerformed(evt);
      }
    });
    cp.add(editDatabase);
    ButtonAnalyze.setBounds(344, 504, 177, 25);
    ButtonAnalyze.setText("Verwandtschaft untersuchen");
    ButtonAnalyze.setMargin(new Insets(2, 2, 2, 2));
    ButtonAnalyze.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonAnalyze_ActionPerformed(evt);
      }
    });
    cp.add(ButtonAnalyze);
    changeLeft.setBounds(16, 344, 121, 25);
    changeLeft.setText("Pferd ändern");
    changeLeft.setMargin(new Insets(2, 2, 2, 2));
    changeLeft.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        changeLeft_ActionPerformed(evt);
      }
    });
    changeLeft.setEnabled(true);
    cp.add(changeLeft);
    changeRight.setBounds(560, 344, 121, 25);
    changeRight.setText("Pferd ändern");
    changeRight.setMargin(new Insets(2, 2, 2, 2));
    changeRight.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        changeRight_ActionPerformed(evt);
      }
    });
    changeRight.setEnabled(true);
    cp.add(changeRight);
    ButtonExit.setBounds(928, 504, 137, 25);
    ButtonExit.setText("Schließen");
    ButtonExit.setMargin(new Insets(2, 2, 2, 2));
    ButtonExit.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonExit_ActionPerformed(evt);
      }
    });
    cp.add(ButtonExit);
    ButtonGoToMenu.setBounds(784, 504, 137, 25);
    ButtonGoToMenu.setText("Hauptmenü");
    ButtonGoToMenu.setMargin(new Insets(2, 2, 2, 2));
    ButtonGoToMenu.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        ButtonGoToMenu_ActionPerformed(evt);
      }
    });
    cp.add(ButtonGoToMenu);
    cp.setBackground(new Color(0xB8CFE5));
    jLabel1.setBounds(448, 8, 227, 28);
    jLabel1.setText("Verwandtschaftscheck");
    jLabel1.setFont(new Font("Dialog", Font.BOLD, 20));
    cp.add(jLabel1);
    radioButtonIsMaleLeft.setBounds(16, 304, 17, 17);
    radioButtonIsMaleLeft.setText("");
    radioButtonIsMaleLeft.setOpaque(false);
    radioButtonIsMaleLeft.setSelected(true);
    radioButtonIsMaleLeft.setEnabled(false);
    cp.add(radioButtonIsMaleLeft);
    radioButtonIsFemaleLeft.setBounds(16, 320, 17, 17);
    radioButtonIsFemaleLeft.setText("");
    radioButtonIsFemaleLeft.setOpaque(false);
    radioButtonIsFemaleLeft.setEnabled(false);
    cp.add(radioButtonIsFemaleLeft);
    labelIsMaleLeft.setBounds(35, 302, 59, 19);
    labelIsMaleLeft.setText("Hengst");
    labelIsMaleLeft.setForeground(new Color(0x404040));
    cp.add(labelIsMaleLeft);
    labelIsFemaleLeft.setBounds(35, 318, 59, 19);
    labelIsFemaleLeft.setText("Stute");
    cp.add(labelIsFemaleLeft);
    radioButtonIsMaleRight.setBounds(560, 304, 17, 17);
    radioButtonIsMaleRight.setText("");
    radioButtonIsMaleRight.setOpaque(false);
    radioButtonIsMaleRight.setEnabled(false);
    radioButtonIsMaleRight.setSelected(true);
    radioButtonIsMaleRight.setForeground(new Color(0x333333));
    cp.add(radioButtonIsMaleRight);
    radioButtonIsFemaleRight.setBounds(560, 320, 17, 17);
    radioButtonIsFemaleRight.setText("");
    radioButtonIsFemaleRight.setOpaque(false);
    radioButtonIsFemaleRight.setEnabled(false);
    cp.add(radioButtonIsFemaleRight);
    labelIsMaleRight.setBounds(579, 302, 59, 19);
    labelIsMaleRight.setText("Hengst");
    cp.add(labelIsMaleRight);
    labelIsFemaleRight.setBounds(579, 318, 59, 19);
    labelIsFemaleRight.setText("Stute");
    cp.add(labelIsFemaleRight);
    // Ende Komponenten

    horseNamesL = new JTextField[15];
    horseNamesL[0] = jTextField0;
    horseNamesL[1] = jTextField1;
    horseNamesL[2] = jTextField2;
    horseNamesL[3] = jTextField3;
    horseNamesL[4] = jTextField4;
    horseNamesL[5] = jTextField5;
    horseNamesL[6] = jTextField6;
    horseNamesL[7] = jTextField7;
    horseNamesL[8] = jTextField8;
    horseNamesL[9] = jTextField9;
    horseNamesL[10] = jTextField10;
    horseNamesL[11] = jTextField11;
    horseNamesL[12] = jTextField12;
    horseNamesL[13] = jTextField13;
    horseNamesL[14] = jTextField14;
    
    horseRacesL = new JComboBox[horseNamesL.length];
    horseRacesL[0] = jComboBox0;
    horseRacesL[1] = jComboBox1;
    horseRacesL[2] = jComboBox2;
    horseRacesL[3] = jComboBox3;
    horseRacesL[4] = jComboBox4;
    horseRacesL[5] = jComboBox5;
    horseRacesL[6] = jComboBox6;
    horseRacesL[7] = jComboBox7;
    horseRacesL[8] = jComboBox8;
    horseRacesL[9] = jComboBox9;
    horseRacesL[10] = jComboBox10;
    horseRacesL[11] = jComboBox11;
    horseRacesL[12] = jComboBox12;
    horseRacesL[13] = jComboBox13;
    horseRacesL[14] = jComboBox14;    

    horseNamesR = new JTextField[15];
    horseNamesR[0] = jTextField15;
    horseNamesR[1] = jTextField16;
    horseNamesR[2] = jTextField17;
    horseNamesR[3] = jTextField18;
    horseNamesR[4] = jTextField19;
    horseNamesR[5] = jTextField20;
    horseNamesR[6] = jTextField21;
    horseNamesR[7] = jTextField22;
    horseNamesR[8] = jTextField23;
    horseNamesR[9] = jTextField24;
    horseNamesR[10] = jTextField25;
    horseNamesR[11] = jTextField26;
    horseNamesR[12] = jTextField27;
    horseNamesR[13] = jTextField28;
    horseNamesR[14] = jTextField29;
    
    horseRacesR = new JComboBox[horseNamesR.length];
    horseRacesR[0] = jComboBox15;
    horseRacesR[1] = jComboBox16;
    horseRacesR[2] = jComboBox17;
    horseRacesR[3] = jComboBox18;
    horseRacesR[4] = jComboBox19;
    horseRacesR[5] = jComboBox20;
    horseRacesR[6] = jComboBox21;
    horseRacesR[7] = jComboBox22;
    horseRacesR[8] = jComboBox23;
    horseRacesR[9] = jComboBox24;
    horseRacesR[10] = jComboBox25;
    horseRacesR[11] = jComboBox26;
    horseRacesR[12] = jComboBox27;
    horseRacesR[13] = jComboBox28;
    horseRacesR[14] = jComboBox29; 

    allNames = new JTextField[2][horseNamesL.length];
    allNames[0] = horseNamesL;
    allNames[1] = horseNamesR;

    allRaces = new JComboBox[2][horseRacesL.length];
    allRaces[0] = horseRacesL;
    allRaces[1] = horseRacesR;

    for(int i=0; i<horseRacesL.length; i++){
      for(int j=0; j<dm.races.length; j++){
        horseRacesL[i].addItem(dm.races[j]);
        horseRacesR[i].addItem(dm.races[j]);
      }
    }

    genders = new JLabel[2][2];
    genders[0][0] = labelIsMaleLeft;
    genders[0][1] = labelIsFemaleLeft;
    genders[1][0] = labelIsMaleRight;
    genders[1][1] = labelIsFemaleRight;

    setVisible(true);
  }
  
  // Anfang Methoden
  public void editDatabase_ActionPerformed(ActionEvent evt) {
    new RCGUIdatabase();
    this.dispose();
  }

  public void ButtonAnalyze_ActionPerformed(ActionEvent evt) {
    neutralizeBackgrounds(0);
    neutralizeBackgrounds(1);
    boolean namesException = checkNames();
    boolean racesException = checkRaces();
    boolean genderException = checkGender();
    if(!namesException && racesException && genderException){ 
      for(int i=0; i<2; i++){
        for(int j=0; j<allNames[0].length; j++){
          String name = allNames[i][j].getText();
          if(isValid(name)){
            allNames[i][j].setBackground(green);
            allRaces[i][j].setBackground(green);
          }
        }
      }
      green = new Color(46, 180, 22);
      if(radioButtonIsMaleLeft.isSelected() && radioButtonIsFemaleRight.isSelected()){
        genders[0][0].setForeground(green);
        genders[1][1].setForeground(green);
      }
      if(radioButtonIsFemaleLeft.isSelected() && radioButtonIsMaleRight.isSelected()){
        genders[0][1].setForeground(green);
        genders[1][0].setForeground(green);
      }
    }
  }

  private boolean checkRaces(){
    boolean foundException = false;
    String chosenRace = (String)horseRacesL[0].getSelectedItem();
    if(chosenRace.equals(" Unbekannt")){
      for(int i=0; i<2; i++){
        for(int j=0; j<horseRacesL.length; j++){
          String name = allNames[i][j].getText();
          if(!(name.equals("nicht in DB") || name.equals(""))){
            allRaces[i][j].setBackground(Color.ORANGE);
            foundException = true;
          }
        }
      }
    }
    else {
      for(int i=0; i<2; i++){
        for(int j=0; j<allRaces[0].length; j++){
          String currentRace = (String)allRaces[i][j].getSelectedItem();
          String name = allNames[i][j].getText();
          if(!(chosenRace.equals(currentRace)) && isValid(name)){
            allRaces[i][j].setBackground(red);
            foundException = true;
          }
        }
      }
    }

   for(int i=0; i<2; i++){
     for(int j=0; j<horseRacesL.length; j++){
      String name = allNames[i][j].getText();
      String race = (String)allRaces[i][j].getSelectedItem();
      if(!(name.equals("") || name.equals("Unbekannt")) && race.equals(" Unbekannt")){
        allRaces[i][j].setBackground(orange);
        foundException = true;
      }
     }
    }
    return foundException;
  }

  public boolean checkNames(){
    boolean foundException = false;
    for(int i=0; i<2; i++){
      for(int j=0; j<horseNamesL.length; j++){
        String name = allNames[i][j].getText();
        if(!isValid(name))
          continue;
        for(int k=i; k<2; k++){
          for(int l=0; l<horseNamesL.length; l++){
            String current = allNames[k][l].getText();
            if(!isValid(name) || (i==k && j==l))
              continue;
            if(name.equals(current)){
              foundException = true;
              allNames[i][j].setBackground(red);
              allNames[k][l].setBackground(red);
            }
          }
        }
      }
    }

    for(int i=0; i<2; i++){
      for(int j=0; j<horseNamesL.length; j++){
        String name = allNames[i][j].getText();
        if(name.equals("nicht in DB")){
          foundException = true;
          allNames[i][j].setBackground(orange);
        }
      }
    }
    return foundException;
  }

  private boolean checkGender(){
    boolean foundException = false;
    if(radioButtonIsMaleLeft.isSelected() && radioButtonIsMaleRight.isSelected()){
      genders[0][0].setForeground(red);
      genders[1][0].setForeground(red);
      foundException = true;
    }
    if(radioButtonIsFemaleLeft.isSelected() && radioButtonIsFemaleRight.isSelected()){
      genders[0][1].setForeground(red);
      genders[1][1].setForeground(red);
      foundException = true;
    }
    return foundException;
  }

  public boolean isValid(String name){
    return !(name.equals("") || name.equals("Unbekannt") || name.equals("nicht in DB"));
  }

  public void changeLeft_ActionPerformed(ActionEvent evt) {
    new HorseChoice(dm, this, true);
    neutralizeBackgrounds(0);
  }

  public void changeRight_ActionPerformed(ActionEvent evt) {
    new HorseChoice(dm, this, false);
    neutralizeBackgrounds(1);
  }

  public void ButtonExit_ActionPerformed(ActionEvent evt) {
    this.dispose();
  }

  public void ButtonGoToMenu_ActionPerformed(ActionEvent evt) {
    new Menue();
    this.dispose();
  }

  public void neutralizeBackgrounds(int side){
    if(side<0 || side >1)
      return;
    for(int i=0; i<horseNamesL.length; i++){
        allNames[side][i].setBackground(Color.WHITE);
        allRaces[side][i].setBackground(Color.WHITE);
      }
    genders[side][0].setForeground(new Color(64, 64, 64));
    genders[side][1].setForeground(new Color(64, 64, 64));
  }

  public JRadioButton getRadioButtonIsMaleLeft(){
      return radioButtonIsMaleLeft;
   }
  public JRadioButton getRadioButtonIsFemaleLeft(){
      return radioButtonIsFemaleLeft;
   }
  public JRadioButton getRadioButtonIsMaleRight(){
      return radioButtonIsMaleRight;
   }
  public JRadioButton getRadioButtonIsFemaleRight(){
      return radioButtonIsFemaleRight;
   }
  // Ende Methoden
  
  public static void main(String[] args) {
    new RelativeCheckGUI();
  }
}
