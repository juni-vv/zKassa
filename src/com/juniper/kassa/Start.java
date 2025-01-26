package com.juniper.kassa;

import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.page.pages.CashierPage;
import com.juniper.kassa.page.pages.LoginPage;
import com.juniper.kassa.page.pages.headoffice.ChangeProductStatusPage;
import com.juniper.kassa.page.pages.headoffice.ProductManagementPage;
import com.juniper.kassa.page.pages.management.ManageRegistersPage;
import com.juniper.kassa.page.pages.management.StoreManagementPage;

public class Start {

	public static void main(String[] args) {
		PageHandler.init();

		PageHandler.addPage("cashierPage", new CashierPage());
		PageHandler.addPage("storeManagementPage", new StoreManagementPage());
		PageHandler.addPage("manageRegistersPage", new ManageRegistersPage());
		
		PageHandler.openPage(new LoginPage(null));
	}

}
