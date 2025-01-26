package com.juniper.kassa.page;

import java.awt.Font;

import com.juniper.kassa.model.User;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.custom.FontManager;

public abstract class NewPage {
	
	public final Font _titleFont    = FontManager.createFont("/Raleway-Bold.ttf", 40);
	public final Font _subtitleFont = FontManager.createFont("/Raleway-Bold.ttf", 30);
	public final Font _defaultFont  = FontManager.createFont("/Raleway-SemiBold.ttf", 16);
	public final Font _errorFont    = FontManager.createFont("/Raleway-Black.ttf", 18);
	public final Font _footerFont   = FontManager.createFont("/Raleway-SemiBold.ttf", 20);
	
	protected User currentUser;
	
	public NewPage(User user) {
		currentUser = user;
	}
	
	public abstract JPanel getPanel();
	public abstract void open();
	public abstract void close();
	
	public abstract void start();
	
}
