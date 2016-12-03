package general;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class WarningDialog extends MDRDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WarningDialog(JFrame owner, String message){
		super(owner, "Hinweis");	

		setLayout(new BorderLayout());
		JTextPane text = new JTextPane();
		text.setText(message);
		text.setEditable(false);
		text.setOpaque(false);
		StyledDocument doc = text.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		add(text, BorderLayout.NORTH);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setOpaque(false);
		JButton buttonAccept = new JButton("OK");
		buttonAccept.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		pnlSouth.add(buttonAccept);
		add(pnlSouth, BorderLayout.SOUTH);
		
		pack();
		super2();
	}
}
