package allgemein;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MDRNumberField extends JTextField{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDRNumberField(boolean alignRight){
		super();
		this.setText("0");
		if(alignRight){
			this.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		this.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent ke) {
				if(!Character.isDigit(ke.getKeyChar())){
				 ke.consume();
				}
			}
		});
		this.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent fe){
				setText("");
			}
			@Override
			public void focusLost(FocusEvent fe){
				if(getText().equals("")){
					setText("0");
				}
			}
		});
	}
	
	public void setText(Double value){
		DecimalFormat form = new DecimalFormat("#0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		this.setText(form.format(value));
	}
	
	public void setText(int value){
		this.setText(String.valueOf(value));
	}
}
