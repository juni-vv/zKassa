package com.juniper.kassa.page.pages.management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.juniper.kassa.network.controller.product.ProductController;
import com.juniper.kassa.network.controller.product.ProductInfo;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.JTextField;
import com.juniper.kassa.swing.custom.Gradient;
import com.juniper.kassa.swing.custom.Numpad;
import com.juniper.kassa.swing.custom.Popup;

public class ManagementPage implements Page {

	private JPanel _jPanel;

	private JPanel keyboardPanel = new JPanel();
	private Numpad numpad        = new Numpad(15);

	private JTextField productCodeField = new JTextField("Product code");

	private JButton signoutButton    = new JButton("Sign out", 40);
	private JButton deliveriesButton = new JButton("Deliveries", 15);
	private JButton mRegistersButton = new JButton("Manage registers", 15);

	private JLabel timeLabel = new JLabel("01-01-2000 00:00:00");

	private String jwt;

	@Override
	public void populate() {
		int width  = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		_jPanel.setGradient(new Gradient(0, 0, width, height, Color.decode("#0860C4"), Color.decode("#d5418f")));
		_jPanel.setLayout(null);

		keyboardPanel.add(numpad.getJPanel());

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
		signoutButton.addActionListener(signOut());

		keyboardPanel.setBounds(width - numpad.getWidth(), signoutButton.getBounds().y - keyboardPanel.getPreferredSize().height, numpad.getWidth(), numpad.getHeight());

		int deliveriesWidth = numpad.getWidth() / 2 - 15, deliveriesHeight = 50;
		deliveriesButton.setPreferredSize(new Dimension(deliveriesWidth, deliveriesHeight));
		deliveriesButton.setFont(_defaultFont);
		deliveriesButton.setFocusPainted(false);
		deliveriesButton.setForeground(Color.white);
		deliveriesButton.setColor(new Color(237, 237, 237, 150));
		deliveriesButton.setArmedColor(new Color(237, 237, 237, 200));
		deliveriesButton.setBounds(width - deliveriesWidth - 10, 10, deliveriesWidth, deliveriesHeight);
		
		int registersWidth = deliveriesWidth, registersHeight = deliveriesHeight;
		mRegistersButton.setPreferredSize(new Dimension(registersWidth, registersHeight));
		mRegistersButton.setFont(_defaultFont);
		mRegistersButton.setFocusPainted(false);
		mRegistersButton.setForeground(Color.white);
		mRegistersButton.setColor(new Color(237, 237, 237, 150));
		mRegistersButton.setArmedColor(new Color(237, 237, 237, 200));
		mRegistersButton.setBounds(width - registersWidth - deliveriesWidth - 20, 10, registersWidth, registersHeight);
		
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

		numpad.addKeyboardListener((keyEvent) -> {
			numpadKeyPressHandle(keyEvent.getPressedKey().toString());
		});

		_jPanel.add(signoutButton);
		_jPanel.add(keyboardPanel);
		_jPanel.add(productCodeField);
		_jPanel.add(deliveriesButton);
		_jPanel.add(mRegistersButton);
	}

	private void numpadKeyPressHandle(String keyString) {
		String key = keyString.split("_")[1];

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
		ProductController productController = new ProductController();
		ProductInfo productInfo = productController.getProduct(productCodeField.getText(), jwt);
		
		productCodeField.requestFocus();
		productCodeField.selectAll();
		
		if(productInfo == null) {
			Popup popup = new Popup(this, "Error", "This product could not be found!");
			popup.setNextFocus(productCodeField);
			popup.show();
		}
		
		// TODO: Search product, add line
	}

	private ActionListener signOut() {
		return (ActionEvent e) -> {
			jwt = null;
			PageHandler.switchPage("loginPage");
		};
	}

	@Override
	public void setWebToken(String token) {
		jwt = token;
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
