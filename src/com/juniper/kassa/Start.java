package com.juniper.kassa;

import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.page.pages.CashierPage;
import com.juniper.kassa.page.pages.LoginPage;
import com.juniper.kassa.page.pages.management.ManageRegistersPage;
import com.juniper.kassa.page.pages.management.ManagementPage;

public class Start {

	public static void main(String[] args) {
		PageHandler.init();

		PageHandler.addPage("loginPage", new LoginPage());
		PageHandler.addPage("cashierPage", new CashierPage());
		PageHandler.addPage("managementPage", new ManagementPage());
		PageHandler.addPage("manageRegistersPage", new ManageRegistersPage());
		PageHandler.switchPage("loginPage");
	}

}
