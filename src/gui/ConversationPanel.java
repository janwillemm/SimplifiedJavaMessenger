package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import lombok.Getter;
import shared.Message;

@SuppressWarnings("serial")
public class ConversationPanel extends JPanel {
	@Getter private int partnerId = -1;
	@Getter private String partner = null;
	
	private JTextPane conversation = new JTextPane();
	private AbstractDocument doc;
	private JScrollPane scrollPane = new JScrollPane(conversation, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	private DateFormat formatter = new SimpleDateFormat("H:mm:ss");
	
	private JTextField inputBox = new JTextField();
	private JButton sendButton = new JButton("Verstuur");
	
	public ConversationPanel(int pid, String p, KeyListener kl) {
		this.partnerId = pid;
		this.partner = p;
		
		this.setLayout(new BorderLayout(2,2));
		
		StyledDocument styledDoc = conversation.getStyledDocument();
		this.doc = (AbstractDocument) styledDoc;
		
		this.conversation.setBackground(Color.BLACK);
		this.conversation.setForeground(Color.LIGHT_GRAY);
		this.conversation.setFont(new Font("Consolas", 0, 12));
		this.conversation.setEditable(false);
		
		this.inputBox.setBackground(Color.BLACK);
		this.inputBox.setForeground(new Color(150, 150, 225));
		this.inputBox.setFont(new Font("Consolas", 0, 12));
		this.inputBox.setMargin(new Insets(5,5,5,5));
		this.inputBox.addKeyListener(kl);
		
		try {
			if(pid > -1)
				doc.insertString(doc.getLength(), "*** Je chat nu met " + p + " ***\n", new SimpleAttributeSet());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.add(this.scrollPane, BorderLayout.CENTER);
		
		this.add(this.inputBox, BorderLayout.SOUTH);
		
	}
	
	public void addMessage(String text, Color textColor) {
		this.addMessage(null, null, text, textColor, false);
	}
	
	public void addMessage(String time, String from, String text, Color textColor) {
		this.addMessage(time, from, text, textColor, false);
	}
	
	public void addMessage(String time, String from, String text, Color textColor, boolean important) {
		SimpleAttributeSet style = new SimpleAttributeSet();
		StyleConstants.setForeground(style, textColor);
		
		String addition = new String(); 
		if(time != null)
			addition += "[" + time + "] ";
		if(from != null)
			addition += from + ": ";
		addition += text + "\n";
		
		try {
			doc.insertString(doc.getLength(), addition, style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addExternalMessage(Message msg) {
		if(msg.getFromId() == -1)
			addMessage(this.formatter.format(msg.getDate()), msg.getFrom(), msg.getContent(), new Color(230, 0, 0), true);
		else
			addMessage(this.formatter.format(msg.getDate()), msg.getFrom(), msg.getContent(), new Color(0, 180, 0));
	}
	
	public void addOwnMessage(Message msg) {
		addMessage(this.formatter.format(msg.getDate()), "Jij", msg.getContent(), new Color(150, 150, 225));
	}
	
	public String getInput() {
		return this.inputBox.getText();
	}
	
	public void setInput(String str) {
		this.inputBox.setText(str);
	}
}
