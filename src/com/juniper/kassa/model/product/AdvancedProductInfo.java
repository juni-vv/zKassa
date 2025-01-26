package com.juniper.kassa.model.product;

import java.util.UUID;

public class AdvancedProductInfo {

	private UUID   id;
	private String name;

	private ProductPriceInfo priceInfo;

	public AdvancedProductInfo(UUID id, String name, ProductPriceInfo priceInfo) {
		this.id = id;
		this.name = name;
		this.priceInfo = priceInfo;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ProductPriceInfo getPriceInfo() {
		return priceInfo;
	}
	
}
