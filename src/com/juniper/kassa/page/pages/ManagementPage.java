package com.juniper.kassa.page.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import com.juniper.kassa.database.Database;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.JTextField;
import com.juniper.kassa.swing.custom.FontManager;
import com.juniper.kassa.swing.custom.Gradient;
import com.juniper.kassa.swing.custom.Numpad;

public class ManagementPage implements Page {
	
	double tempNumber = 0;

	private JPanel _jPanel;

	private final Font _titleFont   = FontManager.createFont("/Raleway-Bold.ttf", 40);
	private final Font _defaultFont = FontManager.createFont("/Raleway-SemiBold.ttf", 16);
	private final Font _errorFont   = FontManager.createFont("/Raleway-Black.ttf", 18);

	private JPanel keyboardPanel = new JPanel();
	private Numpad numpad        = new Numpad(15);

	private JTextField productCodeField = new JTextField("Product code");
	private JButton    signoutButton    = new JButton("Sign out", 40);

	@Override
	public void populate() {
		int width  = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		_jPanel.setGradient(new Gradient(0, 0, width, height, Color.decode("#0860C4"), Color.decode("#d5418f")));
		_jPanel.setLayout(null);
		
		keyboardPanel.setOpaque(false);
		keyboardPanel.add(numpad.getJPane());

		int codeWidth = numpad.getWidth() - 20, codeHeight = 50;
		productCodeField = new JTextField("Product code");
		productCodeField.setPreferredSize(new Dimension(codeWidth, codeHeight));
		productCodeField.setFont(_defaultFont);
		productCodeField.setForeground(Color.WHITE);
		productCodeField.setMargin(new Insets(0, 10, 0, 0));
		productCodeField.setHintTextColor(new Color(237, 237, 237, 200));
		productCodeField.setCornerRadius(10);
		productCodeField.setBorderColor(new Color(0xEF4550));
		productCodeField.setBounds(width - codeWidth - 10, 10, codeWidth, codeHeight);
		productCodeField.addActionListener((ActionEvent e) -> {
			searchProduct();
		});

		_jPanel.add(productCodeField);
		
		keyboardPanel.setBounds(width - numpad.getWidth(), codeHeight + 10, numpad.getWidth(), numpad.getHeight());

		int signoutWidth = numpad.getWidth() - 20, signoutHeight = 50;
		signoutButton.setPreferredSize(new Dimension(signoutWidth, signoutHeight));
		signoutButton.setFont(_defaultFont);
		signoutButton.setFocusPainted(false);
		signoutButton.setForeground(Color.WHITE);
		signoutButton.setColor(new Color(237, 237, 237, 150));
		signoutButton.setArmedColor(new Color(237, 237, 237, 200));
		signoutButton.setBounds(width - signoutWidth - 10, height - signoutHeight - 10, signoutWidth, signoutHeight);
		signoutButton.addActionListener(signOut());
		
		numpad.addKeyboardListener((keyEvent) -> {
			numpadKeyPressHandle(keyEvent.getPressedKey().toString());
		});

		_jPanel.add(signoutButton);
		_jPanel.add(keyboardPanel);
	}
	
	private void numpadKeyPressHandle(String keyString) {
		String key = keyString.split("_")[1];
		System.out.println(key);
		
		if(key.equalsIgnoreCase("backspace")) {
			if(productCodeField.getText().length() > 0)
				productCodeField.setText(productCodeField.getText().substring(0, productCodeField.getText().length() - 1));
			
			return;
		}
		
		if(key.equalsIgnoreCase("enter")) {
			searchProduct();
			return;
		}
		
		productCodeField.setText(productCodeField.getText() + key);
	}
	
	private void searchProduct() {
		if(productCodeField.getText().equalsIgnoreCase("54020276"))
			tempNumber += 0.15;
		else if(productCodeField.getText().equalsIgnoreCase("613008765767"))
			tempNumber += 0.15;
		else if(productCodeField.getText().equalsIgnoreCase("5060947543669"))
			tempNumber += 0.15;
		else if(productCodeField.getText().equalsIgnoreCase("8718907384902"))
			tempNumber += 0.25;
		else if(productCodeField.getText().equalsIgnoreCase("613008739058"))
			tempNumber += 0.15;
		else if(productCodeField.getText().equalsIgnoreCase("3124480167057"))
			tempNumber += 0.15;
		else if(productCodeField.getText().equalsIgnoreCase("8715600246377"))
			tempNumber += 0.25;
		else
			System.out.println("Unknown product!");
		
		NumberFormat euroFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		String formattedAmount = euroFormat.format(tempNumber);
		
		System.out.println("Statiegeld: " + formattedAmount);
		productCodeField.setText("");
	}

	private ActionListener signOut() {
		return (ActionEvent e) -> {
			Database.getConnection().close();
			PageHandler.switchPage("loginPage");
		};
	}

	@Override
	public void resume() {
		productCodeField.requestFocus();
	}

	@Override
	public void init() {
		_jPanel = new JPanel();
	}

	@Override
	public JPanel getPanel() {
		return _jPanel;
	}

}
