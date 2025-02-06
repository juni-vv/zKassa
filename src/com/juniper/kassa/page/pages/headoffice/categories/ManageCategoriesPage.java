package com.juniper.kassa.page.pages.headoffice.categories;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.juniper.kassa.model.User;
import com.juniper.kassa.network.controller.product.ProductController;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.page.pages.headoffice.ProductManagementPage;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.ModernScrollBarUI;
import com.juniper.kassa.swing.custom.Gradient;

public class ManageCategoriesPage extends Page {

	private JPanel _jPanel;

	private JPanel      categoriesPanel      = new JPanel(15);
	private JScrollPane categoriesScrollPane = new JScrollPane();

	private JButton backButton   = new JButton("Back", 40);
	private JButton addButton    = new JButton("Add category", 15);
	private JButton removeButton = new JButton("Remove category", 15);

	private JLabel titleLabel    = new JLabel("Manage categories");
	private JLabel subtitleLabel = new JLabel("Add or remove categories");
	private JLabel timeLabel     = new JLabel("01-01-2000 00:00:00");

	public ManageCategoriesPage(User user) {
		super(user);

		_jPanel = new JPanel();
	}

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

		int signoutWidth = width / 4, signoutHeight = 50;
		backButton.setPreferredSize(new Dimension(signoutWidth, signoutHeight));
		backButton.setFont(_defaultFont);
		backButton.setFocusPainted(false);
		backButton.setForeground(Color.WHITE);
		backButton.setColor(new Color(237, 237, 237, 150));
		backButton.setArmedColor(new Color(237, 237, 237, 200));
		backButton.setBounds(width - signoutWidth - 10, height - signoutHeight - 10 - timeLabel.getPreferredSize().height, signoutWidth, signoutHeight);
		backButton.addActionListener((ActionEvent e) -> back());

		_jPanel.add(backButton);

		titleLabel.setFont(_subtitleFont);
		titleLabel.setForeground(Color.white);
		titleLabel.setBounds(width / 2 - titleLabel.getPreferredSize().width / 2, height / 5, width, titleLabel.getPreferredSize().height);

		_jPanel.add(titleLabel);

		subtitleLabel.setFont(_defaultFont);
		subtitleLabel.setForeground(Color.white);
		subtitleLabel.setBounds(width / 2 - subtitleLabel.getPreferredSize().width / 2, titleLabel.getBounds().y + titleLabel.getBounds().height, width, subtitleLabel.getPreferredSize().height);

		_jPanel.add(subtitleLabel);

		int dcScrollWidth = width / 3, dcScrollHeight = height / 3;
		categoriesPanel.setBounds(width / 2 - dcScrollWidth / 2, 10 + subtitleLabel.getBounds().y + subtitleLabel.getBounds().height, dcScrollWidth, dcScrollHeight);
		categoriesScrollPane.setBounds(width / 2 - dcScrollWidth / 2 + 5, 15 + subtitleLabel.getBounds().y + subtitleLabel.getBounds().height, dcScrollWidth - 10, dcScrollHeight - 10);

		categoriesPanel.setBackground(Color.white);
		categoriesScrollPane.setOpaque(false);
		categoriesScrollPane.setBorder(null);
		categoriesScrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
		
		int catButtonsW = (int) (categoriesPanel.getBounds().getWidth() / 2 - 5);
		addButton.setPreferredSize(new Dimension(catButtonsW, signoutHeight));
		addButton.setFont(_defaultFont);
		addButton.setFocusPainted(false);
		addButton.setForeground(Color.WHITE);
		addButton.setColor(new Color(237, 237, 237, 150));
		addButton.setArmedColor(new Color(237, 237, 237, 200));
		addButton.setBounds(categoriesPanel.getBounds().x, categoriesPanel.getBounds().y + categoriesPanel.getBounds().height + 10, catButtonsW, signoutHeight);
		addButton.addActionListener((ActionEvent e) -> addCategory());
		
		removeButton.setPreferredSize(new Dimension(catButtonsW, signoutHeight));
		removeButton.setFont(_defaultFont);
		removeButton.setFocusPainted(false);
		removeButton.setForeground(Color.WHITE);
		removeButton.setColor(new Color(237, 237, 237, 100));
		removeButton.setArmedColor(new Color(237, 237, 237, 200));
		removeButton.setBounds(addButton.getBounds().x + addButton.getBounds().width + 10, categoriesPanel.getBounds().y + categoriesPanel.getBounds().height + 10, catButtonsW, signoutHeight);
		removeButton.setEnabled(false);
		
		_jPanel.add(categoriesScrollPane);
		_jPanel.add(categoriesPanel);
		_jPanel.add(addButton);
		_jPanel.add(removeButton);
	}
	
	public void addCategory() {
		PageHandler.closePage(this);
		PageHandler.openPage(new AddCategoryPage(currentUser));
	}

	public void back() {
		PageHandler.closePage(this);
		PageHandler.openPage(new ProductManagementPage(currentUser));
	}

	@Override
	public void close() {

	}

	@Override
	public void start() {
		ProductController productController = new ProductController();
		List<String> catList = productController.getCategoryNames(currentUser.getToken());
		
		JList<String> catJList = new JList<String>((String[]) catList.toArray(new String[0]));
		categoriesScrollPane.setViewportView(catJList);
	}

	@Override
	public JPanel getPanel() {
		return _jPanel;
	}

}
