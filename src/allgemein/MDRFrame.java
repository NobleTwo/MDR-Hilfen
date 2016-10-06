package allgemein;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Box;
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
	
	public MDRFrame(){
		super("MDR-Hilfen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(backgroundColor);
	}
	
	protected void super2(String title){
		JLabel labelTitle = new JLabel(title, SwingConstants.CENTER);
		labelTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		Container containerNorth = new Container();
		containerNorth.setLayout(new BorderLayout());
		containerNorth.add(Box.createRigidArea(new Dimension(gridButtonGap,gridButtonGap)), BorderLayout.NORTH);
		containerNorth.add(Box.createRigidArea(new Dimension(gridButtonGap,gridButtonGap)), BorderLayout.SOUTH);
		containerNorth.add(labelTitle, BorderLayout.CENTER);
		this.add(containerNorth, BorderLayout.NORTH);
		
		pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setVisible(true);
	}
}
