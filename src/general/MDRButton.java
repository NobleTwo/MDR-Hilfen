package general;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class MDRButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDRButton() {
		super();
		initiate();
	}

	public MDRButton(String text) {
		super(text);
		initiate();
	}

	private void initiate() {
		this.setBackground(MDRFrame.DARK_BROWN);
		this.setContentAreaFilled(false);
		this.setBorder(new LineBorder(MDRFrame.BROWN));
		this.setFocusPainted(false);
		this.setForeground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isPressed()) {
			g.setColor(MDRFrame.BROWN);
		} else if (getModel().isRollover()) {
			g.setColor(MDRFrame.BROWN);
		} else {
			g.setColor(getBackground());
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
}
