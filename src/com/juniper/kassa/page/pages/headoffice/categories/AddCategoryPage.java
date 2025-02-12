package com.juniper.kassa.page.pages.headoffice.categories;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import com.juniper.kassa.model.User;
import com.juniper.kassa.network.controller.product.ProductController;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.JTextField;
import com.juniper.kassa.swing.custom.Gradient;
import com.juniper.kassa.swing.custom.OkPopup;
import com.juniper.kassa.swing.custom.YesNoPopup;

public class AddCategoryPage extends Page {

	private JPanel _jPanel;

	private JLabel titleLabel = new JLabel("Add new category");

	private JButton backButton   = new JButton("Back", 40);
	private JButton createButton = new JButton("Create category", 15);

	private JTextField categoryNameField = new JTextField();

	public AddCategoryPage(User user) {
		super(user);

		this._jPanel = new JPanel();
	}

	@Override
	public void open() {
		int width  = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		_jPanel.setGradient(new Gradient(0, 0, width, height, Color.decode("#0860C4"), Color.decode("#d5418f")));
		_jPanel.setLayout(null);

		titleLabel.setFont(_titleFont);
		titleLabel.setForeground(Color.white);
		titleLabel.setBounds(width / 2 - titleLabel.getPreferredSize().width / 2, height / 5, width, titleLabel.getPreferredSize().height);

		timeLabel.setFont(_footerFont);
		timeLabel.setForeground(Color.white);
		timeLabel.setBounds(width - timeLabel.getPreferredSize().width - 10, height - timeLabel.getPreferredSize().height - 10, timeLabel.getPreferredSize().width, timeLabel.getPreferredSize().height);

		int signoutWidth = width / 4, signoutHeight = 50;
		backButton.setPreferredSize(new Dimension(signoutWidth, signoutHeight));
		backButton.setFont(_defaultFont);
		backButton.setFocusPainted(false);
		backButton.setForeground(Color.WHITE);
		backButton.setColor(new Color(237, 237, 237, 150));
		backButton.setArmedColor(new Color(237, 237, 237, 200));
		backButton.setBounds(width - signoutWidth - 10, height - signoutHeight - 10 - timeLabel.getPreferredSize().height, signoutWidth, signoutHeight);
		backButton.addActionListener((ActionEvent e) -> back());

		categoryNameField.setPreferredSize(new Dimension(400, 50));
		categoryNameField.setHintText("Category name");
		categoryNameField.setFont(_defaultFont);
		categoryNameField.setForeground(Color.WHITE);
		categoryNameField.setMargin(new Insets(0, 10, 0, 0));
		categoryNameField.setHintTextColor(new Color(237, 237, 237, 200));
		categoryNameField.setCornerRadius(10);
		categoryNameField.setBorderColor(new Color(0xEF4550));
		categoryNameField.addActionListener((ActionEvent e) -> enterName());

		int fieldsX  = width / 2 - categoryNameField.getPreferredSize().width / 2;
		int catNameY = height / 5 + titleLabel.getPreferredSize().height + 20;
		categoryNameField.setBounds(fieldsX, catNameY, categoryNameField.getPreferredSize().width, categoryNameField.getPreferredSize().height);

		createButton.setPreferredSize(new Dimension(400, 50));
		createButton.setFont(_defaultFont);
		createButton.setFocusPainted(false);
		createButton.setForeground(Color.WHITE);
		createButton.setColor(new Color(237, 237, 237, 150));
		createButton.setArmedColor(new Color(237, 237, 237, 200));
		createButton.addActionListener((ActionEvent e) -> enterName());

		int createButtonY = catNameY + categoryNameField.getPreferredSize().height + 10;
		createButton.setBounds(fieldsX, createButtonY, createButton.getPreferredSize().width, createButton.getPreferredSize().height);

		_jPanel.add(backButton);
		_jPanel.add(titleLabel);
		_jPanel.add(timeLabel);
		_jPanel.add(categoryNameField);
		_jPanel.add(createButton);
	}

	private void enterName() {
		if(categoryNameField.getText().isBlank()) {
			OkPopup popup = new OkPopup(this, "Error", "You must specify a name.");
			popup.show();

			return;
		}

		YesNoPopup popup = new YesNoPopup(this, "Are you sure?", "Are you sure you want to add this category?");
		popup.show();

		popup.getResult().thenAccept(confirm -> {
			if(confirm) {
				String categoryName = categoryNameField.getText();

				ProductController pc      = new ProductController();
				boolean           success = pc.addCategory(currentUser.getToken(), categoryName);

				if(success)
					PageHandler.closePage(this);
				else {
					OkPopup popup1 = new OkPopup(this, "Error", "Could not add category name");
					popup1.show();
				}
			}
		});
	}

	private void back() {
		PageHandler.closePage(this);
	}

	@Override
	public void close() {
		PageHandler.openPage(new ManageCategoriesPage(currentUser));
	}

	@Override
	public void start() {

	}

	@Override
	public JPanel getPanel() {
		return _jPanel;
	}

}
