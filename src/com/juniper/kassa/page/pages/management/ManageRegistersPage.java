package com.juniper.kassa.page.pages.management;

import javax.swing.JPanel;

import com.juniper.kassa.page.Page;

public class ManageRegistersPage implements Page {
	
	private JPanel _jPanel;
	private String jwt;

	@Override
	public void populate() {
		
	}

	@Override
	public void resume() {
		
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
	public void setWebToken(String token) {
		
	}

}
