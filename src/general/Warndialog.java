package general;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


@SuppressWarnings("serial")
public class Warndialog extends JDialog{
	public Warndialog(JFrame owner, String message){
		super(owner, "Hinweis", true);
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (d.width - getSize().width) / 2;
	    int y = (d.height - getSize().height) / 2;
	    setLocation(x, y);
		setLayout(new BorderLayout());
		getContentPane().setBackground(new Color(0xB8CFE5));
		setResizable(false);
		
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
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		pnlSouth.add(btnOk);
		add(pnlSouth, BorderLayout.SOUTH);
		
		this.addWindowListener(new WindowAdapter(){
			@Override	
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
		pack();
		setVisible(true);
	}
}
