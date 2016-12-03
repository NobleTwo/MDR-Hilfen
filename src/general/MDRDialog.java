package general;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public abstract class MDRDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int gridButtonGap = MDRFrame.gridButtonGap;
	public static final Color backgroundColor = MDRFrame.backgroundColor;
	public static final int buttonHeight = MDRFrame.buttonHeight;
	protected static final Font fontLabel = new Font("Arial", Font.PLAIN, 10);
	protected static final int yTop = 32;
    protected static final int heightLabel = 20;
    protected static final int widthLabel = 120;
	
    protected Container cp = getContentPane();
    protected JLabel labelTitle;
    
	public MDRDialog(JFrame parentFrame, String title){
		super(parentFrame, "MDR-Hilfen", true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		cp.setBackground(backgroundColor);
		cp.setLayout(null);
		
	    final int widthLabel = 355;
	    labelTitle = new JLabel(title);
	    labelTitle.setBounds(gridButtonGap, 8, widthLabel, 19);
	    labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
	    cp.add(labelTitle);
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
