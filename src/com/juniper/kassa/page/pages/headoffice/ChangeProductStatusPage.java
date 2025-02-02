package com.juniper.kassa.page.pages.headoffice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import com.juniper.kassa.model.User;
import com.juniper.kassa.model.product.AdvancedProductInfo;
import com.juniper.kassa.network.controller.logistics.DistributionCenterController;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.JScrollPane;
import com.juniper.kassa.swing.custom.Gradient;

public class ChangeProductStatusPage extends Page {

	private JPanel _jPanel;

	private JScrollPane dcScrollPane;

	private JButton backButton = new JButton("Back", 40);

	private JLabel titleLabel    = new JLabel("Set activity status for:");
	private JLabel subtitleLabel = new JLabel("{productInfo}");
	private JLabel timeLabel     = new JLabel("01-01-2000 00:00:00");

	private AdvancedProductInfo productInfo;

	public ChangeProductStatusPage(User user, AdvancedProductInfo productInfo) {
		super(user);

		this.productInfo = productInfo;
		this._jPanel = new JPanel();

		subtitleLabel.setText(productInfo.getName() + " - " + productInfo.getId().toString());

		fillDistributionCenters();
	}

	@Override
	public JPanel getPanel() {
		return _jPanel;
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
		dcScrollPane.setBounds(width / 2 - dcScrollWidth / 2, 10 + subtitleLabel.getBounds().y + subtitleLabel.getBounds().height, dcScrollWidth, dcScrollHeight);
		
		_jPanel.add(dcScrollPane);

	}

	private void fillDistributionCenters() {
		DistributionCenterController dcController = new DistributionCenterController();
		dcController.getDistributionCenterNames(currentUser.getToken());
		
		List<String> dcList = new ArrayList<String>(); // TODO: Get from api
		for(int i = 0; i < 200; i++)
			dcList.add("Item " + i);
		
		
		dcScrollPane = new JScrollPane(dcList, 15);//new JScrollPane(dcJList, 15);
	}
	
	private void back() {
		PageHandler.closePage(this);
	}

	@Override
	public void close() {
		PageHandler.openPage(new ProductManagementPage(currentUser));
	}

	@Override
	public void start() {

	}

}
