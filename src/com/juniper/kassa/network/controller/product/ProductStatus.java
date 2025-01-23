package com.juniper.kassa.network.controller.product;

public enum ProductStatus {

	Active(0), Passive(1), TemporarilyPassive(2), Recall(3);

	private final int value;

	ProductStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static ProductStatus fromInt(int intValue) {
		for(ProductStatus e : ProductStatus.values())
			if(e.value == intValue)
				return e;
		
		throw new IllegalArgumentException("No enum constant with value " + intValue);
	}
	
	@Override
	public String toString() {
	    if (this == TemporarilyPassive) {
	        return "Temporarily Unavailable";
	    }
	    return name();
	}

}
