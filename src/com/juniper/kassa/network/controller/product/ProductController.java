package com.juniper.kassa.network.controller.product;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.UUID;

import org.json.JSONObject;

import com.juniper.kassa.model.product.AdvancedProductInfo;
import com.juniper.kassa.model.product.ProductInfo;
import com.juniper.kassa.model.product.ProductPriceInfo;
import com.juniper.kassa.model.product.ProductStatus;
import com.juniper.kassa.network.controller.Controller;

public class ProductController extends Controller {

	public ProductInfo getProductInfo(String code, String jwt) {
		String route = "/Product/" + code;

		try {
			HttpRequest request = getRequest(route, jwt);

			try {
				HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
				
				if(response.statusCode() != 200) {
					return null;
				}

				JSONObject responseObject = new JSONObject(response.body());
				
				ProductPriceInfo priceInfo = new ProductPriceInfo(responseObject.getDouble("price"), responseObject.getDouble("deposit"), responseObject.getDouble("plasticTax"), responseObject.getDouble("salesTax"));
				return new ProductInfo(UUID.fromString(responseObject.getString("id")), responseObject.getString("name"), priceInfo, ProductStatus.fromInt(responseObject.getInt("productStatus")));
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(IllegalArgumentException e) {
			return null;
		}

		return null;
	}
	
	public AdvancedProductInfo getAdvancedProductInfo(String code, String jwt) {
		String route = "/Test/" + code;

		try {
			HttpRequest request = getRequest(route, jwt);

			try {
				HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
				
				if(response.statusCode() != 200) {
					return null;
				}

				JSONObject responseObject = new JSONObject(response.body());
				
				ProductPriceInfo priceInfo = new ProductPriceInfo(responseObject.getDouble("price"), responseObject.getDouble("deposit"), responseObject.getDouble("plasticTax"), responseObject.getDouble("salesTax"));
				return new AdvancedProductInfo(UUID.fromString(responseObject.getString("id")), 
						responseObject.getString("name"), 
						priceInfo);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(IllegalArgumentException e) {
			return null;
		}

		return null;
	}

}
