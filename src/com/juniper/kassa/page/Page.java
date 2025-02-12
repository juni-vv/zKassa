package com.juniper.kassa.page;

import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;

import com.juniper.kassa.model.User;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.custom.FontManager;

public abstract class Page {
	
	public final Font _titleFont    = FontManager.createFont("/Raleway-Bold.ttf", 40);
	public final Font _subtitleFont = FontManager.createFont("/Raleway-Bold.ttf", 30);
	public final Font _defaultFont  = FontManager.createFont("/Raleway-SemiBold.ttf", 16);
	public final Font _errorFont    = FontManager.createFont("/Raleway-Black.ttf", 18);
	public final Font _footerFont   = FontManager.createFont("/Raleway-SemiBold.ttf", 20);
	
	protected JLabel timeLabel         = new JLabel("01-01-2000 00:00:00");
	
	protected User currentUser;
	
	public Page(User user) {
		currentUser = user;
	}
	
	public abstract void open();
	public abstract void close();
	public abstract void start();
	
	public abstract JPanel getPanel();
	
	public void update() {
        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		timeLabel.setText(formattedDateTime);
	}
	
}
