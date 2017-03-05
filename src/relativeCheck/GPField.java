package relativeCheck;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;

import general.MDRFrame;
import general.MDRNumberField;

public class GPField extends Container {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MDRNumberField numberField = new MDRNumberField(false);
	private JLabel label = new JLabel("GP: ");

	public GPField() {
		this.setLayout(new BorderLayout());
		this.add(numberField, BorderLayout.CENTER);
		this.add(label, BorderLayout.WEST);
		this.setPreferredSize(new Dimension(HorseAndRaceField.WIDTH, 2 * MDRFrame.gridButtonGap));
		numberField.removeFocusListener(numberField.getFocusListeners()[2]);
	}

	public MDRNumberField getNumberField() {
		return numberField;
	}

	@Override
	public void setLocation(int x, int y) {
		setBounds(new Rectangle(new Point(x, y), getPreferredSize()));
	}

	public void setGP(int gp) {
		this.numberField.setText(gp);
	}

	public int getGP() {
		return Integer.valueOf(numberField.getText());
	}
}
