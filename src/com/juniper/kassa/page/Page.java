package com.juniper.kassa.page;

import java.awt.Font;

import javax.swing.JPanel;

import com.juniper.kassa.swing.custom.FontManager;

public interface Page {

	final Font _titleFont    = FontManager.createFont("/Raleway-Bold.ttf", 40);
	final Font _subtitleFont = FontManager.createFont("/Raleway-Bold.ttf", 30);
	final Font _defaultFont  = FontManager.createFont("/Raleway-SemiBold.ttf", 16);
	final Font _errorFont    = FontManager.createFont("/Raleway-Black.ttf", 18);
	final Font _footerFont   = FontManager.createFont("/Raleway-SemiBold.ttf", 20);

	public void populate();

	public void resume();

	public void init();

	public JPanel getPanel();

	public void setWebToken(String token);

}
