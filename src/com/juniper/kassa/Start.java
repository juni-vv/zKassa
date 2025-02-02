package com.juniper.kassa;

import java.util.UUID;

import com.juniper.kassa.model.User;
import com.juniper.kassa.model.product.AdvancedProductInfo;
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
		
		PageHandler.openPage(new LoginPage(null));
		//PageHandler.openPage(new ChangeProductStatusPage(new User(null, "token"), new AdvancedProductInfo(UUID.randomUUID(), "test", null)));
	}

}
