package com.juniper.kassa.page.pages.management;

import com.juniper.kassa.model.User;
import com.juniper.kassa.page.Page;
import com.juniper.kassa.swing.JPanel;

public class ManageRegistersPage extends Page {
	
	private JPanel _jPanel;
	
	public ManageRegistersPage(User user) {
		super(user);

		_jPanel = new JPanel();
	}

	@Override
	public JPanel getPanel() {
		return _jPanel;
	}

	@Override
	public void open() {
		
	}

	@Override
	public void close() {
		
	}

	@Override
	public void start() {
		
	}

}
