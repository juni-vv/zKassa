package com.juniper.kassa.page.pages.management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import javax.swing.JLabel;

import com.juniper.kassa.model.User;
import com.juniper.kassa.model.product.ProductInfo;
import com.juniper.kassa.model.product.ProductStatus;
import com.juniper.kassa.network.controller.product.ProductController;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.page.pages.LoginPage;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.JTextField;
import com.juniper.kassa.swing.custom.Gradient;
import com.juniper.kassa.swing.custom.OkPopup;
import com.juniper.kassa.swing.custom.numpad.Numpad;

public class StoreManagementPage extends Page {

	private JPanel _jPanel;

	private JPanel keyboardPanel = new JPanel();
	private JPanel productPanel  = new JPanel();

	private Numpad     numpad           = new Numpad(15);
	private JTextField productCodeField = new JTextField("Product code");

	private JButton signoutButton    = new JButton("Sign out", 40);
	private JButton deliveriesButton = new JButton("Deliveries", 15);
	private JButton registersButton  = new JButton("Registers", 15);
	private JButton staffButton      = new JButton("Staff", 15);
	private JButton scheduleButton   = new JButton("Schedules", 15);

	private JLabel productTitle   = new JLabel("{productTitle}");
	private JLabel productDeposit = new JLabel("{productDeposit}");
	private JLabel productStatus  = new JLabel("{productStatus}");
	
	public StoreManagementPage(User user) {
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

		productPanel.setBounds(10, 10, width - signoutWidth - 30, height - 20);
		productPanel.setBackground(Color.black);
		productPanel.setLayout(null);
		productPanel.setVisible(false);

		productTitle.setFont(_subtitleFont);
		productTitle.setForeground(Color.white);
		productTitle.setBounds(10, 10, productPanel.getPreferredSize().width - 20, productTitle.getPreferredSize().height);

		productDeposit.setFont(_defaultFont);
		productDeposit.setForeground(Color.white);
		productDeposit.setBounds(10, 10 + productTitle.getPreferredSize().height, productPanel.getPreferredSize().width - 20, productDeposit.getPreferredSize().height);

		productStatus.setFont(_defaultFont);
		productStatus.setForeground(Color.white);
		productStatus.setBounds(10, 40 + productTitle.getBounds().y + productTitle.getBounds().height, productPanel.getPreferredSize().width - 20, productStatus.getPreferredSize().height);
		
		productPanel.add(productTitle);
		productPanel.add(productDeposit);
		productPanel.add(productStatus);

		int mButtonsWidth = numpad.getWidth() / 2 - 15, mButtonsHeight = 50;

		deliveriesButton.setPreferredSize(new Dimension(mButtonsWidth, mButtonsHeight));
		deliveriesButton.setFont(_defaultFont);
		deliveriesButton.setFocusPainted(false);
		deliveriesButton.setForeground(Color.white);
		deliveriesButton.setColor(new Color(237, 237, 237, 150));
		deliveriesButton.setArmedColor(new Color(237, 237, 237, 200));
		deliveriesButton.setBounds(width - mButtonsWidth - 10, 10, mButtonsWidth, mButtonsHeight);

		registersButton.setPreferredSize(new Dimension(mButtonsWidth, mButtonsHeight));
		registersButton.setFont(_defaultFont);
		registersButton.setFocusPainted(false);
		registersButton.setForeground(Color.white);
		registersButton.setColor(new Color(237, 237, 237, 150));
		registersButton.setArmedColor(new Color(237, 237, 237, 200));
		registersButton.setBounds(width - mButtonsWidth - mButtonsWidth - 20, 10, mButtonsWidth, mButtonsHeight);
		registersButton.addActionListener((ActionEvent e) -> registersPage());

		staffButton.setPreferredSize(new Dimension(mButtonsWidth, mButtonsHeight));
		staffButton.setFont(_defaultFont);
		staffButton.setFocusPainted(false);
		staffButton.setForeground(Color.white);
		staffButton.setColor(new Color(237, 237, 237, 150));
		staffButton.setArmedColor(new Color(237, 237, 237, 200));
		staffButton.setBounds(width - mButtonsWidth - mButtonsWidth - 20, 20 + mButtonsHeight, mButtonsWidth, mButtonsHeight);

		scheduleButton.setPreferredSize(new Dimension(mButtonsWidth, mButtonsHeight));
		scheduleButton.setFont(_defaultFont);
		scheduleButton.setFocusPainted(false);
		scheduleButton.setForeground(Color.white);
		scheduleButton.setColor(new Color(237, 237, 237, 150));
		scheduleButton.setArmedColor(new Color(237, 237, 237, 200));
		scheduleButton.setBounds(width - mButtonsWidth - 10, 20 + mButtonsHeight, mButtonsWidth, mButtonsHeight);

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
		productCodeField.addActionListener((ActionEvent e) -> searchProduct());

		numpad.setTargetField(productCodeField);
		numpad.setEnterKeyListener(() -> searchProduct());

		_jPanel.add(signoutButton);
		_jPanel.add(keyboardPanel);
		_jPanel.add(productPanel);
		_jPanel.add(productCodeField);
		_jPanel.add(deliveriesButton);
		_jPanel.add(registersButton);
		_jPanel.add(staffButton);
		_jPanel.add(scheduleButton);
	}

	private void searchProduct() {
		ProductController productController = new ProductController();
		ProductInfo       productInfo       = productController.getProductInfo(productCodeField.getText(), currentUser.getToken());

		productCodeField.requestFocus();
		productCodeField.selectAll();

		if(productInfo == null) {
			OkPopup popup = new OkPopup(this, "Error", "This product could not be found!");
			popup.setNextFocus(productCodeField);
			popup.show();

			return;
		}

		DecimalFormat df = new DecimalFormat("0.00");
		productTitle.setText(productInfo.getName() + " - $" + productInfo.getPriceInfo().getPrice());

		productDeposit.setVisible(false);
		productDeposit.setText("");
		if(productInfo.getPriceInfo().hasDeposit()) {
			productDeposit.setText("Deposit: $" + df.format(productInfo.getPriceInfo().getDeposit()));
			productDeposit.setVisible(true);
		}
		if(productInfo.getPriceInfo().hasPlasticTax()) {
			if(productInfo.getPriceInfo().hasDeposit())
				productDeposit.setText(productDeposit.getText() + " - ");

			productDeposit.setText(productDeposit.getText() + "Plastic Tax: $" + df.format(productInfo.getPriceInfo().getPlasticTax()));
			productDeposit.setVisible(true);
		}

		if(productInfo.getPriceInfo().hasDeposit() || productInfo.getPriceInfo().hasPlasticTax())
			productDeposit.setText(productDeposit.getText() + " - ");

		productDeposit.setText(productDeposit.getText() + "Tax: " + (int) (100 * productInfo.getPriceInfo().getTaxPercentage()) + "% ($" + df.format((productInfo.getPriceInfo().getPrice() * productInfo.getPriceInfo().getTaxPercentage())) + ")");
		
		if(!productInfo.getStatus().equals(ProductStatus.Active)) {
			productStatus.setText("Status: " + productInfo.getStatus().toString());
			productStatus.setVisible(true);
		} else {
			productStatus.setVisible(false);
		}
		
		productPanel.setVisible(true);
	}

	private void signOut() {
		currentUser = null;
		productCodeField.setText("");
		productPanel.setVisible(false);

		PageHandler.closePage(this);
		PageHandler.openPage(new LoginPage(null));
	}

	private void registersPage() {
		productCodeField.setText("");
		productPanel.setVisible(false);
		
		PageHandler.closePage(this);
		PageHandler.openPage(new ManageRegistersPage(currentUser));
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
