package com.juniper.kassa.swing.custom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.juniper.kassa.page.NewPage;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;

public class Popup {

	private JFrame frame = new JFrame("Message");

	private String title, message;
	private NewPage   parent;
	
	private JComponent nextFocus = null;
	
	private boolean open = false;

	public Popup(NewPage parent, String title, String message) {
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

		JButton button = new JButton("Okay", 15);
		button.setPreferredSize(new Dimension(200, 100));
		button.setFont(parent._defaultFont);
		button.setFocusPainted(false);
		button.setForeground(Color.WHITE);
		button.setColor(new Color(237, 237, 237, 150));
		button.setArmedColor(new Color(237, 237, 237, 200));
		button.setBounds(new Rectangle(10, height - 60, width - 20, 50));
		button.addActionListener(closeButton());

		panel.add(title);
		panel.add(message);
		panel.add(button);

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

	private ActionListener closeButton() {
		return (ActionEvent e) -> {
			frame.dispose();
			frame.setVisible(false);
			
			open = false;
			
			if(nextFocus != null) {
				nextFocus.requestFocus();
			}
		};
	}
	
	public boolean isOpen() {
		return open;
	}

}
