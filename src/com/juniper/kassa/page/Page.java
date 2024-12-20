package com.juniper.kassa.page;

import javax.swing.JPanel;

public interface Page {

	public void populate();

	public void resume();

	public void init();

	public JPanel getPanel();

}
