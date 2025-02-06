package com.juniper.kassa;

import com.juniper.kassa.page.PageHandler;
import com.juniper.kassa.page.pages.LoginPage;
import com.juniper.kassa.page.pages.headoffice.categories.AddCategoryPage;

public class Start {

	public static void main(String[] args) {
		PageHandler.init();
		
		PageHandler.openPage(new LoginPage(null));
		//PageHandler.openPage(new ChangeProductStatusPage(new User(null, "token"), new AdvancedProductInfo(UUID.randomUUID(), "test", null)));
		//PageHandler.openPage(new AddCategoryPage(null));
	}

}
