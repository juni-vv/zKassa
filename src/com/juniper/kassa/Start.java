package com.juniper.kassa;

import javax.swing.SwingUtilities;

import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.page.pages.LoginPage;
import com.juniper.kassa.page.pages.ManagementPage;

public class Start {

	public static void main(String[] args) {
		PageHandler.init();

		PageHandler.addPage("loginPage", new LoginPage());
		PageHandler.addPage("managementPage", new ManagementPage());
		PageHandler.switchPage("loginPage");
	}
	
}
