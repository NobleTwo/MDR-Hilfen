package allgemein;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public abstract class MDRFrame extends JFrame{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	public static final int gridButtonGap = 10;
	public static final Color backgroundColor = new Color(0xB8CFE5);
	protected JLabel labelTitle;
	public static final int buttonHeight = 25;
	
	public MDRFrame(String title){
		super("MDR-Hilfen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		Container cp = getContentPane();
		cp.setBackground(backgroundColor);
		cp.setLayout(null);
		
		labelTitle = new JLabel(title, SwingConstants.CENTER);
		labelTitle.setFont(new Font("Dialog", Font.BOLD, 20));
	}
		
	public void super2(){
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		
		Dimension size = labelTitle.getPreferredSize();
		labelTitle.setBounds(new Rectangle(new Point((getSize().width - size.width ) / 2, gridButtonGap), size));
		this.add(labelTitle);
		
		setVisible(true);
	}
}
