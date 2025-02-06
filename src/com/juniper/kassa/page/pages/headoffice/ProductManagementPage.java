package com.juniper.kassa.page.pages.headoffice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import javax.swing.JLabel;

import com.juniper.kassa.model.User;
import com.juniper.kassa.model.product.AdvancedProductInfo;
import com.juniper.kassa.network.controller.product.ProductController;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.page.pages.LoginPage;
import com.juniper.kassa.page.pages.headoffice.categories.ManageCategoriesPage;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.JTextField;
import com.juniper.kassa.swing.custom.Gradient;
import com.juniper.kassa.swing.custom.OkPopup;
import com.juniper.kassa.swing.custom.numpad.Numpad;

public class ProductManagementPage extends Page {

	public ProductManagementPage(User user) {
		super(user);

		this._jPanel = new JPanel();

		productCodeField.requestFocus();
	}

	private JPanel _jPanel;

	private JPanel keyboardPanel = new JPanel();
	private JPanel productPanel  = new JPanel();

	private JTextField productCodeField = new JTextField("Product code");
	private Numpad     numpad           = new Numpad(15);

	private JButton signoutButton    = new JButton("Sign out", 40);
	private JButton statusButton     = new JButton("Modify activity status", 15);
	private JButton categoriesButton = new JButton("Manage categories", 15);
	private JButton addProductButton = new JButton("Add a product", 15);

	private JLabel timeLabel      = new JLabel("01-01-2000 00:00:00");
	private JLabel productTitle   = new JLabel("{productTitle}");
	private JLabel productDeposit = new JLabel("{productDeposit}");
	private JLabel productId      = new JLabel("{productId}");

	private AdvancedProductInfo productInfo;

	@Override
	public void open() {
		int width  = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		_jPanel.setGradient(new Gradient(0, 0, width, height, Color.decode("#0860C4"), Color.decode("#d5418f")));
		_jPanel.setLayout(null);

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

		int mButtonsWidth = numpad.getWidth() / 2 - 15, mButtonsHeight = 50;

		categoriesButton.setPreferredSize(new Dimension(mButtonsWidth, mButtonsHeight));
		categoriesButton.setFont(_defaultFont);
		categoriesButton.setFocusPainted(false);
		categoriesButton.setForeground(Color.white);
		categoriesButton.setColor(new Color(237, 237, 237, 150));
		categoriesButton.setArmedColor(new Color(237, 237, 237, 200));
		categoriesButton.setBounds(width - mButtonsWidth - 10, 10, mButtonsWidth, mButtonsHeight);
		categoriesButton.addActionListener((ActionEvent e) -> manageCategoriesPage());

		addProductButton.setPreferredSize(new Dimension(mButtonsWidth, mButtonsHeight));
		addProductButton.setFont(_defaultFont);
		addProductButton.setFocusPainted(false);
		addProductButton.setForeground(Color.white);
		addProductButton.setColor(new Color(237, 237, 237, 150));
		addProductButton.setArmedColor(new Color(237, 237, 237, 200));
		addProductButton.setBounds(width - mButtonsWidth - mButtonsWidth - 20, 10, mButtonsWidth, mButtonsHeight);

		keyboardPanel.add(numpad.getPanel());
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

		productId.setFont(_defaultFont);
		productId.setForeground(Color.white);
		productId.setBounds(10, productDeposit.getBounds().y + productDeposit.getBounds().height, productPanel.getPreferredSize().width - 20, productId.getPreferredSize().height);

		statusButton.setFont(_defaultFont);
		statusButton.setFocusPainted(false);
		statusButton.setForeground(Color.white);
		statusButton.setColor(new Color(237, 237, 237, 150));
		statusButton.setArmedColor(new Color(237, 237, 237, 200));
		statusButton.setBounds(10, 20 + productId.getBounds().y + productId.getBounds().height, statusButton.getPreferredSize().width * 2, 35);
		statusButton.addActionListener((ActionEvent e) -> changeProductStatus());

		productPanel.add(productTitle);
		productPanel.add(productDeposit);
		productPanel.add(statusButton);
		productPanel.add(productId);

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
		_jPanel.add(categoriesButton);
		_jPanel.add(addProductButton);
	}

	private void searchProduct() {
		ProductController productController = new ProductController();
		productInfo = productController.getAdvancedProductInfo(productCodeField.getText(), currentUser.getToken());

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

		productId.setText("ID: " + productInfo.getId().toString());

		productPanel.setVisible(true);
	}

	private void manageCategoriesPage() {
		PageHandler.closePage(this);
		PageHandler.openPage(new ManageCategoriesPage(currentUser));
	}

	private void changeProductStatus() {
		if(productInfo != null) {
			PageHandler.closePage(this);
			PageHandler.openPage(new ChangeProductStatusPage(currentUser, productInfo));
		}
	}

	private void signOut() {
		currentUser = null;
		productCodeField.setText("");
		productPanel.setVisible(false);

		PageHandler.closePage(this);
		PageHandler.openPage(new LoginPage(null));
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
		productCodeField.requestFocus();
	}

}
