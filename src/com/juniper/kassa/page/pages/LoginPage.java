package com.juniper.kassa.page.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;

import com.juniper.kassa.database.DatabaseConnection;
import com.juniper.kassa.exception.ConnectionException;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.JPasswordField;
import com.juniper.kassa.swing.JTextField;
import com.juniper.kassa.swing.custom.FontManager;
import com.juniper.kassa.swing.custom.Gradient;

public class LoginPage implements Page {

	private JPanel _jPanel;

	private final Font _titleFont   = FontManager.createFont("/Raleway-Bold.ttf", 40);
	private final Font _defaultFont = FontManager.createFont("/Raleway-SemiBold.ttf", 16);
	private final Font _errorFont   = FontManager.createFont("/Raleway-Black.ttf", 18);

	private JLabel titleLabel        = new JLabel("Authentication");
	private JLabel accessDeniedLabel = new JLabel();

	private JTextField     usernameField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();

	private JButton loginButton = new JButton("Login", 40);

	@Override
	public void populate() {
		int width  = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);

		_jPanel.setGradient(new Gradient(0, 0, width, height, Color.decode("#0860C4"), Color.decode("#d5418f")));
		constraints.gridy = 0;

		/* Login title */
		titleLabel.setFont(_titleFont);
		titleLabel.setForeground(Color.white);

		constraints.gridy++;
		constraints.insets = new Insets(5, 5, 0, 5);
		_jPanel.add(titleLabel, constraints);

		/* Access denied label */
		accessDeniedLabel.setFont(_errorFont);
		accessDeniedLabel.setForeground(new Color(0xEF4550));

		constraints.gridy++;
		constraints.insets = new Insets(5, 5, 10, 5);
		_jPanel.add(accessDeniedLabel, constraints);

		/* Username field */
		usernameField.setPreferredSize(new Dimension(400, 50));
		usernameField.setHintText("Username");
		usernameField.setFont(_defaultFont);
		usernameField.setForeground(Color.WHITE);
		usernameField.setMargin(new Insets(0, 10, 0, 0));
		usernameField.setHintTextColor(new Color(237, 237, 237, 200));
		usernameField.setCornerRadius(10);
		usernameField.setBorderColor(new Color(0xEF4550));

		usernameField.addFocusListener(inputFieldsClicked());
		usernameField.addActionListener((ActionEvent e) -> {
			passwordField.requestFocus();
		});

		constraints.gridy++;
		constraints.insets = new Insets(5, 5, 15, 5);
		_jPanel.add(usernameField, constraints);

		/* Password field */
		passwordField.setPreferredSize(new Dimension(400, 50));
		passwordField.setHintText("Password");
		passwordField.setFont(_defaultFont);
		passwordField.setForeground(Color.WHITE);
		passwordField.setMargin(new Insets(0, 10, 0, 0));
		passwordField.setHintTextColor(new Color(237, 237, 237, 200));
		passwordField.setCornerRadius(10);
		passwordField.setBorderColor(new Color(0xEF4550));

		passwordField.addFocusListener(inputFieldsClicked());
		passwordField.addActionListener(loginButtonPressed());

		constraints.gridy++;
		_jPanel.add(passwordField, constraints);

		/* Login button */
		loginButton.setPreferredSize(new Dimension(400, 50));
		loginButton.setFont(_defaultFont);
		loginButton.setFocusPainted(false);
		loginButton.setForeground(Color.WHITE);
		loginButton.setColor(new Color(237, 237, 237, 150));
		loginButton.setArmedColor(new Color(237, 237, 237, 200));

		loginButton.addActionListener(loginButtonPressed());

		constraints.gridy++;
		_jPanel.add(loginButton, constraints);
	}

	private FocusListener inputFieldsClicked() {
		return new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				accessDeniedLabel.setText("   ");

				usernameField.setBorderVisible(false);
				usernameField.repaint();

				passwordField.setBorderVisible(false);
				passwordField.repaint();
			}

			@Override
			public void focusLost(FocusEvent e) {

			}
		};
	}

	private ActionListener loginButtonPressed() {
		return (ActionEvent actionEvent) -> {
			DatabaseConnection databaseConnection = null;

			try {
				String usernameParam = usernameField.getText();
				String passwordParam = new String(passwordField.getPassword());

				databaseConnection = new DatabaseConnection(usernameParam, passwordParam);
			} catch(ConnectionException e) {
				accessDeniedLabel.setText(e.getReason());

				usernameField.setBorderVisible(true);
				usernameField.repaint();

				passwordField.setBorderVisible(true);
				passwordField.repaint();
			}

			if(databaseConnection != null) {
				usernameField.setText("");
				passwordField.setText("");
				
				PageHandler.switchPage("managementPage");
			}
		};
	}
	
	@Override
	public void resume() {
		usernameField.requestFocus();
	}

	@Override
	public void init() {
		_jPanel = new JPanel(new GridBagLayout());
	}

	@Override
	public JPanel getPanel() {
		return _jPanel;
	}

}
