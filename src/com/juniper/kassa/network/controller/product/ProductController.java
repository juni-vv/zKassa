package com.juniper.kassa.network.controller.product;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.juniper.kassa.model.product.AdvancedProductInfo;
import com.juniper.kassa.model.product.ProductInfo;
import com.juniper.kassa.model.product.ProductPriceInfo;
import com.juniper.kassa.model.product.ProductStatus;
import com.juniper.kassa.network.GetRequest;
import com.juniper.kassa.network.PostRequest;

public class ProductController {

	public ProductInfo getProductInfo(String code, String jwt) {
		String route = "/Product/" + code;

		try {
			GetRequest request = new GetRequest(route);
			request.useToken(jwt);

			HttpResponse<String> response = (HttpResponse<String>) request.send();

			if(response.statusCode() != 200) {
				return null;
			}

			JSONObject responseObject = new JSONObject(response.body());

			ProductPriceInfo priceInfo = new ProductPriceInfo(responseObject.getDouble("price"), responseObject.getDouble("deposit"), responseObject.getDouble("plasticTax"), responseObject.getDouble("salesTax"));
			return new ProductInfo(UUID.fromString(responseObject.getString("id")), responseObject.getString("name"), priceInfo, ProductStatus.fromInt(responseObject.getInt("productStatus")));
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public AdvancedProductInfo getAdvancedProductInfo(String code, String jwt) {
		String route = "/Test/" + code;

		try {
			GetRequest request = new GetRequest(route);
			request.useToken(jwt);

			HttpResponse<String> response = (HttpResponse<String>) request.send();

			if(response.statusCode() != 200) {
				return null;
			}

			JSONObject responseObject = new JSONObject(response.body());

			ProductPriceInfo priceInfo = new ProductPriceInfo(responseObject.getDouble("price"), responseObject.getDouble("deposit"), responseObject.getDouble("plasticTax"), responseObject.getDouble("salesTax"));
			return new AdvancedProductInfo(UUID.fromString(responseObject.getString("id")), responseObject.getString("name"), priceInfo);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public List<String> getCategoryNames(String token) {
		String route = "/Category";
		
		try {
			GetRequest request = new GetRequest(route);
			request.useToken(token);
			
			HttpResponse<String> response = (HttpResponse<String>) request.send();

			List<String> categories = new ArrayList<String>();
			
			JSONArray jsonArray = new JSONArray(response.body());
	        for (int i = 0; i < jsonArray.length(); i++)
	            categories.add(jsonArray.getString(i));
	        
	        return categories;
			
		} catch(IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean addCategory(String token, String categoryName) {
		String route = "/Category/" + categoryName;
		
		try {
			PostRequest request = new PostRequest(route);
			request.sendJSON(new JSONObject());
			request.useToken(token);
			
			HttpResponse<?> response = request.send();
			System.out.println(response.body());
			
			return response.statusCode() == 200;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
