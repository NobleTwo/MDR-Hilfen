package allgemein;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JNumberField extends JTextField{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JNumberField(){
		super();
		this.setHorizontalAlignment(SwingConstants.RIGHT);
	}
	
	public void setText(Double value){
		DecimalFormat form = new DecimalFormat("#0.0000", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		this.setText(form.format(value));
	}
	
	public void setText(int value){
		this.setText(String.valueOf(value));
	}
}
