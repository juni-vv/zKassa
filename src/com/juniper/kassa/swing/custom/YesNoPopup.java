package com.juniper.kassa.swing.custom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.concurrent.CompletableFuture;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.juniper.kassa.page.Page;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;

public class YesNoPopup {

	private JFrame frame = new JFrame("Message");

	private String title, message;
	private Page   parent;
	
	private JComponent nextFocus = null;
	
	private CompletableFuture<Boolean> result = new CompletableFuture<>();
	
	private boolean open = false;

	public YesNoPopup(Page parent, String title, String message) {
		this.title = title;
		this.message = message;

		this.parent = parent;
	}
	
	public void setNextFocus(JComponent component) {
		nextFocus = component;
	}

	public void show() {
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(0);
		
		JTextPane message = new JTextPane();
		message.setText(this.message);

		int width = message.getPreferredSize().width * 2 + 20, height = 175;
		frame.setSize(new Dimension(width, height));
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setGradient(new Gradient(0, 0, width, height, Color.decode("#252b36"), Color.decode("#101b30")));
		panel.setLayout(null);

		JLabel title = new JLabel(this.title);
		title.setForeground(Color.white);
		title.setFont(FontManager.createFont("/Raleway-Black.ttf", 22));
		title.setBounds(width / 2 - title.getPreferredSize().width / 2, 10, title.getPreferredSize().width, title.getPreferredSize().height);
		
		message.setForeground(Color.white);
		message.setFont(parent._defaultFont);
		message.setEditable(false);
		message.setOpaque(false);
		message.setBounds(10, title.getBounds().y + title.getBounds().height + 10, width - 20, height - 100);
		message.setPreferredSize(new Dimension(width - 20, height - 100));
		message.setSize(new Dimension(width - 20, height - 100));

		StyledDocument     document = message.getStyledDocument();
		SimpleAttributeSet center   = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		document.setParagraphAttributes(0, document.getLength(), center, false);

		JButton yesButton = new JButton("Yes", 15);
		yesButton.setPreferredSize(new Dimension(200, 100));
		yesButton.setFont(parent._defaultFont);
		yesButton.setFocusPainted(false);
		yesButton.setForeground(Color.WHITE);
		yesButton.setColor(new Color(237, 237, 237, 150));
		yesButton.setArmedColor(new Color(237, 237, 237, 200));
		yesButton.setBounds(new Rectangle(10, height - 60, width / 2 - 15, 50));
		yesButton.addActionListener(yesButton());
		
		JButton noButton = new JButton("No", 15);
		noButton.setPreferredSize(new Dimension(200, 100));
		noButton.setFont(parent._defaultFont);
		noButton.setFocusPainted(false);
		noButton.setForeground(Color.WHITE);
		noButton.setColor(new Color(237, 237, 237, 150));
		noButton.setArmedColor(new Color(237, 237, 237, 200));
		noButton.setBounds(new Rectangle(yesButton.getBounds().width + 20, height - 60, width / 2 - 15, 50));
		noButton.addActionListener(noButton());

		panel.add(title);
		panel.add(message);
		panel.add(yesButton);
		panel.add(noButton);

		frame.add(panel);
		
		frame.addWindowFocusListener(new WindowFocusListener() {
	        @Override
	        public void windowGainedFocus(WindowEvent e) {
	        }

	        @Override
	        public void windowLostFocus(WindowEvent e) {
	            frame.toFront();
	            frame.requestFocus();
	        }
	    });
		
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();
		
		open = true;
	}
	
	private ActionListener yesButton() {
		return (ActionEvent e) -> {
			frame.dispose();
			frame.setVisible(false);
			
			result.complete(true);
			
			open = false;
			
			if(nextFocus != null) {
				nextFocus.requestFocus();
			}
		};
	}
	

	private ActionListener noButton() {
		return (ActionEvent e) -> {
			frame.dispose();
			frame.setVisible(false);
			
			result.complete(false);
			
			open = false;
			
			if(nextFocus != null) {
				nextFocus.requestFocus();
			}
		};
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public CompletableFuture<Boolean> getResult() {
		return result;
	}
	
}
