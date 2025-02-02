package com.juniper.kassa.page.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;

import com.juniper.kassa.model.User;
import com.juniper.kassa.model.UserRole;
import com.juniper.kassa.network.controller.authentication.AuthenticationController;
import com.juniper.kassa.network.controller.authentication.LoginResult;
import com.juniper.kassa.network.controller.authentication.LoginResult.Type;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.page.pages.headoffice.ProductManagementPage;
import com.juniper.kassa.page.pages.management.StoreManagementPage;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.JPasswordField;
import com.juniper.kassa.swing.JTextField;
import com.juniper.kassa.swing.custom.Gradient;
import com.juniper.kassa.swing.custom.Popup;
import com.juniper.kassa.swing.custom.numpad.Numpad;

public class LoginPage extends Page {

	private JPanel _jPanel;

	private JLabel titleLabel        = new JLabel("Authentication");
	private JLabel timeLabel         = new JLabel("01-01-2000 00:00:00");
	private JLabel accessDeniedLabel = new JLabel("Label");

	private JTextField     usernameField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();

	private JButton loginButton = new JButton("Login", 40);

	private JPanel keyboardPanel = new JPanel();

	private Numpad numpad = new Numpad(15);
	
	public LoginPage(User user) {
		super(user);
		
		_jPanel = new JPanel();
	}

	@Override
	public void open() {
		int width  = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		_jPanel.setLayout(null);
		_jPanel.setGradient(new Gradient(0, 0, width, height, Color.decode("#0860C4"), Color.decode("#d5418f")));

		/* Login title */
		titleLabel.setFont(_titleFont);
		titleLabel.setForeground(Color.white);
		titleLabel.setBounds(width / 2 - titleLabel.getPreferredSize().width / 2, height / 5, width, titleLabel.getPreferredSize().height);

		/* Username field */
		usernameField.setPreferredSize(new Dimension(400, 50));
		usernameField.setHintText("Username");
		usernameField.setFont(_defaultFont);
		usernameField.setForeground(Color.WHITE);
		usernameField.setMargin(new Insets(0, 10, 0, 0));
		usernameField.setHintTextColor(new Color(237, 237, 237, 200));
		usernameField.setCornerRadius(10);
		usernameField.setBorderColor(new Color(0xEF4550));
		
		int fieldsX = width / 2 - usernameField.getPreferredSize().width / 2;
		int usernameFieldY = height / 5 + titleLabel.getPreferredSize().height + 20;
		usernameField.setBounds(fieldsX, usernameFieldY, usernameField.getPreferredSize().width, usernameField.getPreferredSize().height);
		
		usernameField.addActionListener((ActionEvent e) -> usernameFilled());
		usernameField.addFocusListener(inputFieldsClicked());
		
		/* Password field */
		passwordField.setPreferredSize(new Dimension(400, 50));
		passwordField.setHintText("Password");
		passwordField.setFont(_defaultFont);
		passwordField.setForeground(Color.WHITE);
		passwordField.setMargin(new Insets(0, 10, 0, 0));
		passwordField.setHintTextColor(new Color(237, 237, 237, 200));
		passwordField.setCornerRadius(10);
		passwordField.setBorderColor(new Color(0xEF4550));

		int passwordFieldY = usernameFieldY + usernameField.getPreferredSize().height + 10;
		passwordField.setBounds(fieldsX, passwordFieldY, passwordField.getPreferredSize().width, passwordField.getPreferredSize().height);

		passwordField.addActionListener((ActionEvent e) -> attemptLogin());
		passwordField.addFocusListener(inputFieldsClicked());

		/* Access denied label */
		accessDeniedLabel.setFont(_errorFont);
		accessDeniedLabel.setForeground(new Color(0xEF4550));
		accessDeniedLabel.setBounds(width / 2 - accessDeniedLabel.getPreferredSize().width / 2, usernameField.getBounds().y - accessDeniedLabel.getPreferredSize().height, width, accessDeniedLabel.getPreferredSize().height);
		accessDeniedLabel.setVisible(false);
		
		/* Login button */
		loginButton.setPreferredSize(new Dimension(400, 50));
		loginButton.setFont(_defaultFont);
		loginButton.setFocusPainted(false);
		loginButton.setForeground(Color.WHITE);
		loginButton.setColor(new Color(237, 237, 237, 150));
		loginButton.setArmedColor(new Color(237, 237, 237, 200));

		int loginButtonY = passwordFieldY + passwordField.getPreferredSize().height + 10;
		loginButton.setBounds(fieldsX, loginButtonY, loginButton.getPreferredSize().width, loginButton.getPreferredSize().height);

		loginButton.addActionListener((ActionEvent e) -> attemptLogin());

		/* Keyboard */
		keyboardPanel.add(numpad.getPanel());
		keyboardPanel.setBounds(width / 2 - numpad.getWidth() / 2, height - 50 - numpad.getHeight(), numpad.getWidth(), numpad.getHeight());

		numpad.setTargetField(passwordField);
		numpad.setEnterKeyListener(() -> attemptLogin());

		/* Time label */
		timeLabel.setFont(_footerFont);
		timeLabel.setForeground(Color.white);
		timeLabel.setBounds(width - timeLabel.getPreferredSize().width - 10, height - timeLabel.getPreferredSize().height - 10, timeLabel.getPreferredSize().width, timeLabel.getPreferredSize().height);

		_jPanel.add(timeLabel);
		_jPanel.add(usernameField);
		_jPanel.add(passwordField);
		_jPanel.add(keyboardPanel);
		_jPanel.add(loginButton);
		_jPanel.add(titleLabel);
		_jPanel.add(accessDeniedLabel);
		
		usernameField.requestFocus();
	}

	private FocusListener inputFieldsClicked() {
		return new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				accessDeniedLabel.setText("");

				passwordField.setBorderVisible(false);
				passwordField.repaint();
				
				usernameField.setBorderVisible(false);
				usernameField.repaint();
			}

			@Override
			public void focusLost(FocusEvent e) {

			}
		};
	}
	
	private void usernameFilled() {
		passwordField.requestFocus();
	}

	private void attemptLogin() {
		AuthenticationController authController = new AuthenticationController();
		LoginResult              result         = authController.attemptLogin(usernameField.getText(), new String(passwordField.getPassword()));

		passwordField.setText("");
		usernameField.setText("");
		
		System.out.println("User role: " + result.getUserRole().toString());

		if(result.getType() == Type.Success) {
			String jwt = result.getToken();
			if(jwt != null) {
				User user = new User(result.getUserRole(), result.getToken());

				if(result.getUserRole() == UserRole.Manager) {
					PageHandler.closePage(this);
					PageHandler.openPage(new StoreManagementPage(user));
				}

				if(result.getUserRole() == UserRole.Cashier || result.getUserRole() == UserRole.SCO || result.getUserRole() == UserRole.Service || result.getUserRole() == UserRole.TeamLead) {
					PageHandler.closePage(this);
					PageHandler.openPage(new CashierPage(user));
				}

				if(result.getUserRole() == UserRole.Store) {
					accessDeniedLabel.setText("You don't have permission to perform this action.");
					accessDeniedLabel.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - accessDeniedLabel.getPreferredSize().width / 2), accessDeniedLabel.getBounds().y, accessDeniedLabel.getBounds().width, accessDeniedLabel.getBounds().height);
					accessDeniedLabel.setVisible(true);
					
					passwordField.setBorderVisible(true);
					passwordField.repaint();
					
					usernameField.setBorderVisible(true);
					usernameField.repaint();
					return;
				}
				
				if(result.getUserRole() == UserRole.ProductManager) {
					PageHandler.closePage(this);
					PageHandler.openPage(new ProductManagementPage(user));
				}

				return;
			}
		}

		if(result.getType() == Type.NoPermission) {
			accessDeniedLabel.setText("You don't have permission to perform this action.");
			accessDeniedLabel.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - accessDeniedLabel.getPreferredSize().width / 2), accessDeniedLabel.getBounds().y, accessDeniedLabel.getBounds().width, accessDeniedLabel.getBounds().height);
			accessDeniedLabel.setVisible(true);
			
			passwordField.setBorderVisible(true);
			passwordField.repaint();
			
			usernameField.setBorderVisible(true);
			usernameField.repaint();
			return;
		}
		if(result.getType() == Type.NoConnection) {
			accessDeniedLabel.setText("Could not connect, contact the system admin.");
			accessDeniedLabel.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - accessDeniedLabel.getPreferredSize().width / 2), accessDeniedLabel.getBounds().y, accessDeniedLabel.getBounds().width, accessDeniedLabel.getBounds().height);
			accessDeniedLabel.setVisible(true);
			
			result.getConnectionException().printStackTrace();

			passwordField.setBorderVisible(true);
			passwordField.repaint();
			
			usernameField.setBorderVisible(true);
			usernameField.repaint();
			return;
		}

		Popup popup = new Popup(this, "Unknown error", "An unknown error occurred, please contact your system administrator.");
		popup.setNextFocus(usernameField);
		popup.show();
	}

	@Override
	public JPanel getPanel() {
		return _jPanel;
	}

	@Override
	public void close() {
		
	}
	
	@Override
	public void start() {
		usernameField.requestFocus();
	}

}
