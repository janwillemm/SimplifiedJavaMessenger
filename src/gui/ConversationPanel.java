package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import java.text.*;
import java.util.Date;

import lombok.*;

import shared.Message;

@SuppressWarnings("serial")

public class ConversationPanel extends JPanel  {
	@Getter private int partnerId = -1;
	@Getter private String partner = null;
	
	private JTextPane conversation = new JTextPane();
	private AbstractDocument doc;
	
	private DateFormat formatter = new SimpleDateFormat("H:mm:ss");
	
	private JPanel talkPanel = new JPanel(new BorderLayout(10,10));
	private JTextField inputBox = new JTextField();
	private JButton sendButton = new JButton("Verstuur");
	
	public ConversationPanel(int pid, String p) {
		this.partnerId = pid;
		this.partner = p;
		
		setLayout(new BorderLayout(10,10));
		
		StyledDocument styledDoc = conversation.getStyledDocument();
		doc = (AbstractDocument) styledDoc;
		
		conversation.setEditable(false);
		
		SimpleAttributeSet italic = new SimpleAttributeSet();
		StyleConstants.setItalic(italic, true);
		
		try {
			doc.insertString(doc.getLength(), "Je chat nu met " + p + ".", italic);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane(conversation);
		
		add(scrollPane, BorderLayout.CENTER);
		
		this.talkPanel.add(this.inputBox, BorderLayout.CENTER);
		this.talkPanel.add(this.sendButton, BorderLayout.EAST);
		
		add(this.talkPanel, BorderLayout.SOUTH);
		
		this.sendButton.addActionListener(null);
	}
	
	public void addMessage(String time, String from, String text, Color textColor) {
		SimpleAttributeSet color = new SimpleAttributeSet();
		StyleConstants.setForeground(color, textColor);
		
		String addition = new String(); 
		addition += "\n[" + time + "] ";
		addition += from + ": ";
		addition += text;
		
		try {
			doc.insertString(doc.getLength(), addition, color);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addExternalMessage(Message msg) {
		Color useColor;
		if(msg.getFromId() == -1)
			useColor = Color.RED;
		else 
			useColor = Color.GREEN;
		
		addMessage(this.formatter.format(msg.getDate()), msg.getFrom(), msg.getContent(), useColor);
	}
	
	public void addOwnMessage(String str) {
		addMessage(this.formatter.format(new Date()), "Jij", str, Color.BLUE);
	}
	
	public String getInput() {
		return this.inputBox.getText();
	}
	
	public void setInput(String str) {
		this.inputBox.setText(str);
	}

}
