package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import java.text.*;
import java.util.Date;

import shared.Message;

@SuppressWarnings("serial")
public class ConversationPanel extends JPanel {
	private int partnerId = -1;
	private String partner = null;
	
	private JTextPane conversation = new JTextPane();
	private AbstractDocument doc;
	private JScrollPane scrollPane = new JScrollPane(conversation, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	private DateFormat formatter = new SimpleDateFormat("H:mm:ss");
	
	private JPanel talkPanel = new JPanel(new BorderLayout(2,2));
	private JTextField inputBox = new JTextField();
	private JButton sendButton = new JButton("Verstuur");
	
	public ConversationPanel(int pid, String p) {
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
		
		try {
			doc.insertString(doc.getLength(), "*** Je chat nu met " + p + " ***", new SimpleAttributeSet());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.add(this.scrollPane, BorderLayout.CENTER);
		
		this.talkPanel.add(this.inputBox, BorderLayout.CENTER);
		this.talkPanel.add(this.sendButton, BorderLayout.EAST);
		
		this.add(this.talkPanel, BorderLayout.SOUTH);
		
		//TODO point to actionHandler!
		this.sendButton.addActionListener(null);
	}
	
	public void addMessage(String time, String from, String text, Color textColor) {
		this.addMessage(time, from, text, textColor, false);
	}
	
	public void addMessage(String time, String from, String text, Color textColor, boolean important) {
		SimpleAttributeSet style = new SimpleAttributeSet();
		StyleConstants.setForeground(style, textColor);
		
		String addition = new String(); 
		addition += "\n[" + time + "] ";
		addition += from + ": ";
		addition += text;
		
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
	
	public void addOwnMessage(String str) {
		addMessage(this.formatter.format(new Date()), "Jij", str, new Color(150, 150, 225));
	}
	
	public String getInput() {
		return this.inputBox.getText();
	}
	
	public void setInput(String str) {
		this.inputBox.setText(str);
	}
}
