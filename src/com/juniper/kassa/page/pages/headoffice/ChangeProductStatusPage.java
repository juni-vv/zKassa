package com.juniper.kassa.page.pages.headoffice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.juniper.kassa.model.User;
import com.juniper.kassa.model.product.AdvancedProductInfo;
import com.juniper.kassa.network.controller.logistics.DistributionCenterController;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;
import com.juniper.kassa.swing.ModernScrollBarUI;
import com.juniper.kassa.swing.custom.Gradient;
import com.juniper.kassa.swing.custom.YesNoPopup;

public class ChangeProductStatusPage extends Page {

	private JPanel _jPanel;

	private JPanel      distributionCentersPanel = new JPanel(15);
	private JScrollPane dcScrollPane             = new JScrollPane();

	private JButton backButton = new JButton("Back", 40);
	private JButton activeButton = new JButton("Active", 15);
	private JButton passiveButton = new JButton("Passive (D)", 15);
	private JButton tempButton = new JButton("Passive (T)", 15);
	private JButton recallButton = new JButton("Recall", 15);

	private JLabel titleLabel    = new JLabel("Set activity status for:");
	private JLabel subtitleLabel = new JLabel("{productInfo}");

	private AdvancedProductInfo productInfo;

	public ChangeProductStatusPage(User user, AdvancedProductInfo productInfo) {
		super(user);

		this.productInfo = productInfo;
		this._jPanel = new JPanel();

		subtitleLabel.setText(productInfo.getName() + " - " + productInfo.getId().toString());
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
		distributionCentersPanel.setBounds(width / 2 - dcScrollWidth / 2, 10 + subtitleLabel.getBounds().y + subtitleLabel.getBounds().height, dcScrollWidth, dcScrollHeight);
		dcScrollPane.setBounds(width / 2 - dcScrollWidth / 2 + 5, 15 + subtitleLabel.getBounds().y + subtitleLabel.getBounds().height, dcScrollWidth - 10, dcScrollHeight - 10);

		distributionCentersPanel.setBackground(Color.white);
		dcScrollPane.setOpaque(false);
		dcScrollPane.setBorder(null);
		dcScrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
		
		int statusBtnWidth = distributionCentersPanel.getBounds().width / 4 - (30 / 4), statusBtnHeight = 50;
		activeButton.setPreferredSize(new Dimension(statusBtnWidth, statusBtnHeight));
		activeButton.setFont(_defaultFont);
		activeButton.setFocusPainted(false);
		activeButton.setForeground(Color.WHITE);
		activeButton.setColor(new Color(237, 237, 237, 150));
		activeButton.setArmedColor(new Color(237, 237, 237, 200));
		activeButton.setBounds(distributionCentersPanel.getBounds().x, distributionCentersPanel.getBounds().y + dcScrollHeight + 10, statusBtnWidth, statusBtnHeight);
		activeButton.setEnabled(false);
		activeButton.addActionListener((e) -> active());
		
		passiveButton.setPreferredSize(new Dimension(statusBtnWidth, statusBtnHeight));
		passiveButton.setFont(_defaultFont);
		passiveButton.setFocusPainted(false);
		passiveButton.setForeground(Color.WHITE);
		passiveButton.setColor(new Color(237, 237, 237, 150));
		passiveButton.setArmedColor(new Color(237, 237, 237, 200));
		passiveButton.setBounds(activeButton.getBounds().x + statusBtnWidth + 10, distributionCentersPanel.getBounds().y + dcScrollHeight + 10, statusBtnWidth, statusBtnHeight);
		passiveButton.setEnabled(false);
		passiveButton.addActionListener((e) -> passive());
		
		tempButton.setPreferredSize(new Dimension(statusBtnWidth, statusBtnHeight));
		tempButton.setFont(_defaultFont);
		tempButton.setFocusPainted(false);
		tempButton.setForeground(Color.WHITE);
		tempButton.setColor(new Color(237, 237, 237, 150));
		tempButton.setArmedColor(new Color(237, 237, 237, 200));
		tempButton.setBounds(passiveButton.getBounds().x + statusBtnWidth + 10, distributionCentersPanel.getBounds().y + dcScrollHeight + 10, statusBtnWidth, statusBtnHeight);
		tempButton.setEnabled(false);
		tempButton.addActionListener((e) -> temporarilyUnavailable());
		
		recallButton.setPreferredSize(new Dimension(statusBtnWidth, statusBtnHeight));
		recallButton.setFont(_defaultFont);
		recallButton.setFocusPainted(false);
		recallButton.setForeground(Color.WHITE);
		recallButton.setColor(new Color(237, 237, 237, 150));
		recallButton.setArmedColor(new Color(237, 237, 237, 200));
		recallButton.setBounds(tempButton.getBounds().x + statusBtnWidth + 10, distributionCentersPanel.getBounds().y + dcScrollHeight + 10, statusBtnWidth, statusBtnHeight);
		recallButton.addActionListener((e) -> recall());
		
		_jPanel.add(activeButton);
		_jPanel.add(passiveButton);
		_jPanel.add(tempButton);
		_jPanel.add(recallButton);
		
		_jPanel.add(dcScrollPane);
		_jPanel.add(distributionCentersPanel);

	}
	
	public void active() {
		YesNoPopup popup = new YesNoPopup(this, "Are you sure?", "Are you sure you want to mark this product as \"active\"?");
		popup.show();
		
		popup.getResult().thenAccept(confirm -> {
			if(confirm) {
				// Mark active
			}
		});
	}
	
	public void passive() {
		YesNoPopup popup = new YesNoPopup(this, "Are you sure?", "Are you sure you want to mark this product as \"passive\"?");
		popup.show();
		
		popup.getResult().thenAccept(confirm -> {
			if(confirm) {
				// Mark passive
			}
		});
	}
	
	public void temporarilyUnavailable() {
		YesNoPopup popup = new YesNoPopup(this, "Are you sure?", "Are you sure you want to mark this product as \"temporarily passive\"?");
		popup.show();
		
		popup.getResult().thenAccept(confirm -> {
			if(confirm) {
				// Mark temppassive
			}
		});
	}
	
	public void recall() {
		YesNoPopup popup = new YesNoPopup(this, "Are you sure?", "Are you sure you want to recall this product?\nThis applies for all distribution centers.");
		popup.show();
		
		popup.getResult().thenAccept(confirm -> {
			if(confirm) {
				// Mark recall
			}
		});
	}

	private void fillDistributionCenters() {
		DistributionCenterController dcController = new DistributionCenterController();
		Map<String, String>          dcMap        = dcController.getDistributionCenterNames(currentUser.getToken());

		List<String>  dcList  = dcMap.entrySet().stream().map(entry -> entry.getKey() + " - " + entry.getValue()).collect(Collectors.toList());
		JList<String> dcJList = new JList<String>((String[]) dcList.toArray(new String[0]));

		dcJList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					activeButton.setEnabled(true);
					passiveButton.setEnabled(true);
					tempButton.setEnabled(true);
				}
			}
		});

		dcScrollPane.setViewportView(dcJList);
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
		fillDistributionCenters();
	}

}
