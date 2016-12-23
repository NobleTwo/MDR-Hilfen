package general;

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
	public static final Color backgroundColor = new Color(0x9FC5C8);//     0x488793 0x559AA7 0xB8CFE5
    protected static final int heightLabel = 20;
    protected static final int widthLabel = 120;
    protected static final int yTop = 2*gridButtonGap + 20;
    public static final int buttonHeight = 25;
    
    protected Container cp = getContentPane();
    protected JLabel labelTitle;
    
	public MDRFrame(String title){
		super("MDR-Hilfen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
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
