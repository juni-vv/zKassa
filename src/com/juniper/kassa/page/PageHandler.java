package com.juniper.kassa.page;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PageHandler {

	private static JFrame _jFrame;
	private static JPanel _jPagesPanel;

	private static CardLayout        _cardLayout;
	private static Map<String, Page> _pages;

	public static void init() {
		if(_jFrame == null) {
			_jFrame = new JFrame();

			_jFrame.setDefaultCloseOperation(3);
			_jFrame.setTitle("STORE_NAME");
			_jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			_jFrame.setUndecorated(true);
			_jFrame.setLocationRelativeTo(null);

			_jPagesPanel = new JPanel();
			_cardLayout = new CardLayout();
			
			_jPagesPanel.setLayout(_cardLayout);
			_pages = new HashMap<String, Page>();
			_jFrame.add(_jPagesPanel);
		}
	}

	public static void addPage(String name, Page page) {
		_pages.put(name, page);
		page.init();
		page.populate();
		_jPagesPanel.add(page.getPanel(), name);
	}

	public static void switchPage(String name) {
		_cardLayout.show(_jPagesPanel, name);
		_jFrame.setVisible(true);
		
		Page page = _pages.get(name);
		if(page != null)
			page.resume();
	}

}
