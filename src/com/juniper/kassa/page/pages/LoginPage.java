package com.juniper.kassa.page.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import com.juniper.kassa.network.controller.AuthenticationController;
import com.juniper.kassa.network.controller.LoginResult;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.JPasswordField;
import com.juniper.kassa.swing.custom.Gradient;
import com.juniper.kassa.swing.custom.Numpad;

public class LoginPage implements Page {

	private JPanel _jPanel;

	private JLabel titleLabel = new JLabel("Authentication");
	private JLabel timeLabel  = new JLabel("01-01-2000 00:00:00");

	private JPasswordField passwordField = new JPasswordField();

	private JButton loginButton = new JButton("Login", 40);

	private JPanel keyboardPanel = new JPanel();

	private Numpad numpad = new Numpad(15);

	@Override
	public void populate() {
		int width  = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		_jPanel.setLayout(null);

		_jPanel.setGradient(new Gradient(0, 0, width, height, Color.decode("#0860C4"), Color.decode("#d5418f")));

		/* Login title */
		titleLabel.setFont(_titleFont);
		titleLabel.setForeground(Color.white);
		titleLabel.setBounds(width / 2 - titleLabel.getPreferredSize().width / 2, height / 5, width, titleLabel.getPreferredSize().height);

		_jPanel.add(titleLabel);

		/* Password field */
		passwordField.setPreferredSize(new Dimension(400, 50));
		passwordField.setHintText("Passcode");
		passwordField.setFont(_defaultFont);
		passwordField.setForeground(Color.WHITE);
		passwordField.setMargin(new Insets(0, 10, 0, 0));
		passwordField.setHintTextColor(new Color(237, 237, 237, 200));
		passwordField.setCornerRadius(10);
		passwordField.setBorderColor(new Color(0xEF4550));

		int passwordFieldX = width / 2 - passwordField.getPreferredSize().width / 2;
		int passwordFieldY = height / 5 + titleLabel.getPreferredSize().height + 20;
		passwordField.setBounds(passwordFieldX, passwordFieldY, passwordField.getPreferredSize().width, passwordField.getPreferredSize().height);

		passwordField.addActionListener(loginButtonPressed());

		_jPanel.add(passwordField);

		/* Login button */
		loginButton.setPreferredSize(new Dimension(400, 50));
		loginButton.setFont(_defaultFont);
		loginButton.setFocusPainted(false);
		loginButton.setForeground(Color.WHITE);
		loginButton.setColor(new Color(237, 237, 237, 150));
		loginButton.setArmedColor(new Color(237, 237, 237, 200));

		int loginButtonX = width / 2 - loginButton.getPreferredSize().width / 2;
		int loginButtonY = height / 5 + titleLabel.getPreferredSize().height + passwordField.getPreferredSize().height + 30;
		loginButton.setBounds(loginButtonX, loginButtonY, loginButton.getPreferredSize().width, loginButton.getPreferredSize().height);

		loginButton.addActionListener(loginButtonPressed());

		_jPanel.add(loginButton);

		/* Keyboard */
		keyboardPanel.add(numpad.getJPanel());
		
		keyboardPanel.setBounds(width / 2 - numpad.getWidth() / 2, height - 50 - numpad.getHeight(), numpad.getWidth(), numpad.getHeight());
		
		_jPanel.add(keyboardPanel);

		timeLabel.setFont(_footerFont);
		timeLabel.setForeground(Color.white);
		timeLabel.setBounds(width - timeLabel.getPreferredSize().width - 10, height - timeLabel.getPreferredSize().height - 10, timeLabel.getPreferredSize().width, timeLabel.getPreferredSize().height);

		_jPanel.add(timeLabel);
		
		numpad.addKeyboardListener((keyEvent) -> {
			keypadKeyPressHandle(keyEvent.getPressedKey().toString());
		});
	}
	
	private void keypadKeyPressHandle(String keyString) {
		String key = keyString.split("_")[1];

		String passcode = new String(passwordField.getPassword());
		if(key.equalsIgnoreCase("backspace")) {
			if(passcode.length() > 0)
				passwordField.setText(passcode.substring(0, passcode.length() - 1));

			return;
		}

		if(key.equalsIgnoreCase("enter")) {
			attemptLogin();
			return;
		}

		passwordField.setText(passcode + key);
	}

	private ActionListener loginButtonPressed() {
		return (ActionEvent actionEvent) -> {
			attemptLogin();
		};
	}
	
	private void attemptLogin() {
		AuthenticationController authController = new AuthenticationController();
		LoginResult result = authController.attemptLogin(new String(passwordField.getPassword()));
		
		passwordField.setText("");

		String jwt = result.getJWT();
		if(jwt != null) {
			PageHandler.switchPage("cashierPage");
		}
	}

	@Override
	public void resume() {
		passwordField.requestFocus();
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
