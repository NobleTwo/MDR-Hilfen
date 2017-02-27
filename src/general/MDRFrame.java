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

public abstract class MDRFrame extends JFrame {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	public static final int gridButtonGap = 10;

	protected static final Color YELLOW = new Color(255, 210, 0);
	public static final Color GREEN = new Color(23, 224, 23);
	public static final Color RED = new Color(255, 0, 0);
	protected static final Color SELECTION_GREY = new Color(239, 239, 239);
	public static final Color LIGHT_BROWN = new Color(211, 195, 180);
	public static final Color BROWN = new Color(184, 161, 140);
	public static final Color DARK_BROWN = new Color(135, 100, 84);

	public static final Color GREEN2 = new Color(116, 235, 95);
	public static final Color ORANGE = new Color(245, 172, 73);
	public static final Color RED2 = new Color(241, 88, 50);

	public static final Color backgroundColor = LIGHT_BROWN; // new Color(0x9FC5C8); 0x488793 0x559AA7 0xB8CFE5

	protected static final int heightLabel = 20;
	protected static final int widthLabel = 120;
	protected static final int yTop = 2 * gridButtonGap + 20;
	public static final int buttonHeight = 25;

	protected Container cp = getContentPane();
	protected JLabel labelTitle;

	public MDRFrame(String title) {
		super("MDR-Hilfen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		cp.setBackground(backgroundColor);
		cp.setLayout(null);
		
		labelTitle = new JLabel(title, SwingConstants.CENTER);
		labelTitle.setFont(new Font("Dialog", Font.BOLD, 20));
	}

	public void super2() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);

		Dimension size = labelTitle.getPreferredSize();
		labelTitle.setBounds(new Rectangle(new Point((getSize().width - size.width) / 2, gridButtonGap), size));
		this.add(labelTitle);

		this.setVisible(true);
	}
}
