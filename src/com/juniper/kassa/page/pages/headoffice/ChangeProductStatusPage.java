package com.juniper.kassa.page.pages.headoffice;

import com.juniper.kassa.model.User;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.swing.JPanel;

public class ChangeProductStatusPage implements Page {
	
	private JPanel _jPanel;

	private User currentUser;
	
	@Override
	public void populate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		System.out.println("test");
	}

	@Override
	public void init() {
		_jPanel = new JPanel();
	}

	@Override
	public JPanel getPanel() {
		return _jPanel;
	}

	@Override
	public void setUser(User user) {
		currentUser = user;
	}

}
