package com.juniper.kassa.swing.custom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.swing.JButton;

public class Popup {

	private JFrame frame = new JFrame("Message");
	
	private String title, message;
	private Page parent;
	
	public Popup(Page parent, String title, String message) {
		this.title = title;
		this.message = message;
		
		this.parent = parent;
	}

	public void show() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(0);

		int width = 500, height = 200;
		frame.setBounds(new Rectangle(toolkit.getScreenSize().width / 2 - width / 2, toolkit.getScreenSize().height / 2 - height / 2, width, height));

		JLabel title   = new JLabel(this.title);
		title.setForeground(Color.black);
		title.setFont(parent._defaultFont);
		
		JLabel message = new JLabel(this.message);
		message.setForeground(Color.black);
		message.setFont(parent._defaultFont);
		
		JButton button = new JButton("Okay", 15);
		button.setPreferredSize(new Dimension(200, 100));
		button.setFont(parent._defaultFont);
		button.setFocusPainted(false);
		button.setForeground(Color.WHITE);
		button.setColor(new Color(237, 237, 237, 150));
		button.setArmedColor(new Color(237, 237, 237, 200));
		button.setBounds(new Rectangle(width / 2 - 50, height / 2 - 25, 100, 50));
		button.addActionListener(closeButton());

		frame.add(title);
		frame.add(message);
		frame.add(button);

		frame.setVisible(true);
	}
	
	private ActionListener closeButton() {
		return (ActionEvent e) -> {
			frame.dispose();
			frame.setVisible(false);
		};
	}

}
