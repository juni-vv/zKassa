package com.juniper.kassa.model.product;

public class ProductPriceInfo {
	
	private double price;
	private double deposit;
	private double plasticTax;
	private double taxPercentage;
	
	public ProductPriceInfo(double price, double deposit, double plasticTax, double taxPercentage) {
		this.price = price;
		this.deposit = deposit;
		this.plasticTax = plasticTax;
		this.taxPercentage = taxPercentage;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getDeposit() {
		return deposit;
	}
	
	public boolean hasDeposit() {
		return deposit > 0;
	}
	
	public double getPlasticTax() {
		return plasticTax;
	}
	
	public boolean hasPlasticTax() {
		return plasticTax > 0;
	}
	
	public double getTaxPercentage() {
		return taxPercentage;
	}

}
