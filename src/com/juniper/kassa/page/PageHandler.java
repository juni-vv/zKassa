package com.juniper.kassa.page;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.juniper.kassa.model.User;

public class PageHandler {

	private static JFrame _jFrame;
	private static JPanel _jPagesPanel;

	private static CardLayout        _cardLayout;
	private static Map<String, Page> _pages;
	
	private static List<NewPage> _newPages;

	public static void init() {
		if(_jFrame == null) {
			_jFrame = new JFrame();

			_jFrame.setDefaultCloseOperation(3);
			_jFrame.setTitle("zKassa");
			_jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			_jFrame.setUndecorated(true);
			_jFrame.setLocationRelativeTo(null);

			_jPagesPanel = new JPanel();
			_cardLayout = new CardLayout();
			
			_jPagesPanel.setLayout(_cardLayout);
			_pages = new HashMap<String, Page>();
			_newPages = new ArrayList<NewPage>();
			_jFrame.add(_jPagesPanel);
		}
	}

	public static void addPage(String name, Page page) {
		_pages.put(name, page);
		page.init();
		page.populate();
		_jPagesPanel.add(page.getPanel(), name);
	}
	
	public static void openPage(NewPage page) {
		_jPagesPanel.add(page.getPanel(), "page");
		_jFrame.setVisible(true);
		page.open();
		
		_cardLayout.show(_jPagesPanel, "page");
		page.start();
	}
	
	public static void closePage(NewPage page) {
		_jPagesPanel.remove(page.getPanel());
		page.close();
	}

	public static void switchPage(String name) {
		_cardLayout.show(_jPagesPanel, name);
		_jFrame.setVisible(true);
		
		Page page = _pages.get(name);
		if(page != null) {
			page.resume();
			return;
		}
		
		System.out.println("Page \"" + name + "\" was not found.");
	}
	
	public static void sendUser(String pageName, User user) {
		Page page = _pages.get(pageName);
		if(page != null) {
			page.setUser(user);
		}
	}

}
