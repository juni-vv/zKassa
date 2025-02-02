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

	public static void init() {
		if(_jFrame == null) {
			_jFrame = new JFrame();

			_jFrame.setDefaultCloseOperation(3);
			_jFrame.setTitle("zKassa");
			_jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			_jFrame.setUndecorated(true);
			_jFrame.setLocationRelativeTo(null);
		}
	}
	
	public static void openPage(Page page) {
		_jFrame.setContentPane(page.getPanel());
		_jFrame.setVisible(true);
		
		page.open();
		page.start();
		
		_jFrame.revalidate();
		_jFrame.repaint();
	}
	
	public static void closePage(Page page) {
		page.close();
	}

}
