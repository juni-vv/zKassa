package com.juniper.kassa.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//Temporary class
public class Window extends JFrame {

	//private LoginActivity _mainActivity;
	private JPanel _mainPanel = new JPanel(new GridBagLayout());
	
	private final Font _defaultFont = new Font("arial", 1, 16);

	public Window() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(3);
		setTitle("STORE_NAME");
		setSize(getWindowSize());
		setUndecorated(true);
		setLocationRelativeTo(null);

		saturateLoginScreen();
		//_mainPanel.setGradient(new Gradient(0, 0, getWidth(), getHeight(), Color.decode("#0860C4"), Color.decode("#d5418f")));

		setVisible(true);
	}

	public void saturateProductInformationScreen() {
		_mainPanel.removeAll();
		_mainPanel.revalidate();
		_mainPanel.repaint();
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		
		JTextField productCodeField = new JTextField(50);
		productCodeField.setHintText("Product code");
		productCodeField.setFont(_defaultFont);
		
		_mainPanel.add(productCodeField, gridBagConstraints);
	}

	private void saturateLoginScreen() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		_mainPanel.setBackground(Color.decode("#6d3fa1"));

		JTextField usernameField = new JTextField(50);
		usernameField.setHintText("Username");
		usernameField.setFont(_defaultFont);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		_mainPanel.add(usernameField, gridBagConstraints);

		JPasswordField passwordField = new JPasswordField(50);
		passwordField.setHintText("Password");
		passwordField.setFont(_defaultFont);

		gridBagConstraints.gridy = 1;
		_mainPanel.add(passwordField, gridBagConstraints);

		JButton loginButton = new JButton("Authorize");
		gridBagConstraints.gridy = 2;

		loginButton.setPreferredSize(new Dimension(200, 50));
		loginButton.setFont(_defaultFont);

		JLabel accessDeniedLabel = new JLabel("Username or password incorrect.");
		gridBagConstraints.gridy = 3;

		accessDeniedLabel.setFont(_defaultFont);
		accessDeniedLabel.setForeground(Color.red);
		accessDeniedLabel.setVisible(false);

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String password = "";
				for(char c : passwordField.getPassword())
					password += c;

				//try {
				//	startMainActivity(usernameField.getText(), password);
				//} catch(AccessDeniedException ex) {
				//	accessDeniedLabel.setVisible(true);
				//}
			}

		});

		_mainPanel.add(loginButton, gridBagConstraints);

		gridBagConstraints.gridy = 4;
		_mainPanel.add(accessDeniedLabel, gridBagConstraints);

		add(_mainPanel);
	}

	//private void startMainActivity(String username, String password) throws AccessDeniedException {
		//_mainActivity = new LoginActivity(this, username, password);

	private Dimension getWindowSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

}
