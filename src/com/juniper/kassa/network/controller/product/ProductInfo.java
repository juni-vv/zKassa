package com.juniper.kassa.network.controller.product;

import java.util.UUID;

public class ProductInfo {

	private UUID   id;
	private String name;
	private double price;
	private int    status;

	public ProductInfo(UUID id, String name, double price, int status) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.status = status;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getStatus() {
		return status;
	}

}
