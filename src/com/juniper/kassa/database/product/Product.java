package com.juniper.kassa.database.product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {

	private final ResultSet _resultSet;

	public Product(ResultSet resultSet) {
		_resultSet = resultSet;
	}

	public String getProductCode() {
		try {
			return _resultSet.getString("productCode");
		} catch(SQLException e) {
			return "";
		}
	}

	public String getProductName() {
		try {
			return _resultSet.getString("productName");
		} catch(SQLException e) {
			return "";
		}
	}

	public String getProductDescription() {
		try {
			return _resultSet.getString("productDesc");
		} catch(SQLException e) {
			return "";
		}
	}

	public double getPrice() {
		try {
			return _resultSet.getDouble("price");
		} catch(SQLException e) {
			return 0.0;
		}
	}
	
	public double getStatiegeld() {
		try {
			return _resultSet.getDouble("statiegeld");
		} catch(SQLException e) {
			return 0.0;
		}
	}

	public boolean isVegan() {
		try {
			return _resultSet.getBoolean("vegan");
		} catch(SQLException e) {
			return false;
		}
	}

	public boolean isVegetarian() {
		try {
			return _resultSet.getBoolean("vegetarian");
		} catch(SQLException e) {
			return false;
		}
	}
	
	public boolean isHalal() {
		try {
			return _resultSet.getBoolean("halal");
		} catch(SQLException e) {
			return false;
		}
	}
	
	public boolean isKosher() {
		try {
			return _resultSet.getBoolean("kosher");
		} catch(SQLException e) {
			return false;
		}
	}
	
	public boolean isGlutenFree() {
		try {
			return _resultSet.getBoolean("gluten_free");
		} catch(SQLException e) {
			return false;
		}
	}
	
	public boolean isSugarFree() {
		try {
			return _resultSet.getBoolean("sugar_free");
		} catch(SQLException e) {
			return false;
		}
	}
	
	public boolean isLowCarb() {
		try {
			return _resultSet.getBoolean("low_carb");
		} catch(SQLException e) {
			return false;
		}
	}
	
	public boolean isPopular() {
		try {
			return _resultSet.getBoolean("popular");
		} catch(SQLException e) {
			return false;
		}
	}
	
	public boolean isHazardous() {
		try {
			return _resultSet.getBoolean("PGS");
		} catch(SQLException e) {
			return false;
		}
	}
	
	public ProductAvailability getAvailability() {
		int availabilityStatus = -1;
		
		try {
			availabilityStatus = _resultSet.getInt("availability");
		} catch(SQLException e) {
			availabilityStatus = -1;
		}
		
		switch (availabilityStatus) {
			case -1:
				return ProductAvailability.UNKNOWN;
				
			case 0:
				return ProductAvailability.AVAILABLE;
				
			case 1:
				return ProductAvailability.TEMPORARILY_NOT_AVAILABLE;
				
			case 2:
				return ProductAvailability.PASSIVE;
				
			case 3:
				return ProductAvailability.RECALL;
				
			default:
				return ProductAvailability.UNKNOWN;
		}
	}
	

}
