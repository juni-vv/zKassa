package com.juniper.kassa.network.controller.product;

import java.util.UUID;

public class ProductInfo {

	private UUID   id;
	private String name;

	private ProductPriceInfo priceInfo;

	private int status;

	public ProductInfo(UUID id, String name, ProductPriceInfo priceInfo, int status) {
		this.id = id;
		this.name = name;
		this.priceInfo = priceInfo;
		this.status = status;
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

	public int getStatus() {
		return status;
	}

}
