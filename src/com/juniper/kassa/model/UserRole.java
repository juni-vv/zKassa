package com.juniper.kassa.model;

public enum UserRole {

	Store(0),
	Cashier(1),
	SCO(2),
	Service(3),
	TeamLead(4),
	Manager(5),
	
	HR(6),
	Admin(7),
	ProductManager(8),
	Developer(9);

	private final int value;

	UserRole(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static UserRole fromInt(int intValue) {
		for(UserRole e : UserRole.values())
			if(e.value == intValue)
				return e;
		
		throw new IllegalArgumentException("No enum constant with value " + intValue);
	}
	
}
