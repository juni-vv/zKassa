package com.juniper.kassa.page.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import com.juniper.kassa.model.User;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.JTextField;
import com.juniper.kassa.swing.custom.Gradient;
import com.juniper.kassa.swing.custom.numpad.Numpad;

public class CashierPage extends Page {

	private JPanel _jPanel;

	private JPanel keyboardPanel = new JPanel();
	private Numpad numpad        = new Numpad(15);

	private JTextField productCodeField = new JTextField("Product code");
	private JButton    signoutButton    = new JButton("Sign out", 40);
	
	public CashierPage(User user) {
		super(user);
		
		this._jPanel = new JPanel();
	}

	@Override
	public void open() {
		int width  = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		_jPanel.setGradient(new Gradient(0, 0, width, height, Color.decode("#0860C4"), Color.decode("#d5418f")));
		_jPanel.setLayout(null);

		keyboardPanel.add(numpad.getPanel());

		timeLabel.setFont(_footerFont);
		timeLabel.setForeground(Color.white);
		timeLabel.setBounds(width - timeLabel.getPreferredSize().width - 10, height - timeLabel.getPreferredSize().height - 10, timeLabel.getPreferredSize().width, timeLabel.getPreferredSize().height);

		_jPanel.add(timeLabel);

		int signoutWidth = numpad.getWidth() - 20, signoutHeight = 50;
		signoutButton.setPreferredSize(new Dimension(signoutWidth, signoutHeight));
		signoutButton.setFont(_defaultFont);
		signoutButton.setFocusPainted(false);
		signoutButton.setForeground(Color.WHITE);
		signoutButton.setColor(new Color(237, 237, 237, 150));
		signoutButton.setArmedColor(new Color(237, 237, 237, 200));
		signoutButton.setBounds(width - signoutWidth - 10, height - signoutHeight - 10 - timeLabel.getPreferredSize().height, signoutWidth, signoutHeight);
		signoutButton.addActionListener((ActionEvent e) -> signOut());

		keyboardPanel.setBounds(width - numpad.getWidth(), signoutButton.getBounds().y - keyboardPanel.getPreferredSize().height, numpad.getWidth(), numpad.getHeight());

		int codeWidth = numpad.getWidth() - 20, codeHeight = 50;
		productCodeField = new JTextField("Product code:");
		productCodeField.setPreferredSize(new Dimension(codeWidth, codeHeight));
		productCodeField.setFont(_defaultFont);
		productCodeField.setForeground(Color.WHITE);
		productCodeField.setMargin(new Insets(0, 10, 0, 0));
		productCodeField.setHintTextColor(new Color(237, 237, 237, 200));
		productCodeField.setCornerRadius(10);
		productCodeField.setBorderColor(new Color(0xEF4550));
		productCodeField.setBounds(width - codeWidth - 10, keyboardPanel.getBounds().y - codeHeight, codeWidth, codeHeight);
		productCodeField.addActionListener((ActionEvent e) -> {
			searchProduct();
		});

		_jPanel.add(productCodeField);

		numpad.setTargetField(productCodeField);
		numpad.setEnterKeyListener(() -> searchProduct());

		_jPanel.add(signoutButton);
		_jPanel.add(keyboardPanel);
	}

	private void searchProduct() {
		// TODO: Search product, add line
	}

	private void signOut() {
		currentUser = null;
		PageHandler.openPage(new LoginPage(null));
	}

	@Override
	public void start() {
		productCodeField.requestFocus();
	}

	@Override
	public void close() {
		
	}

	@Override
	public JPanel getPanel() {
		return _jPanel;
	}

}
