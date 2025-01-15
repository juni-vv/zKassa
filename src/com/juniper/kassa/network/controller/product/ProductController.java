package com.juniper.kassa.network.controller.product;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.UUID;

import org.json.JSONObject;

import com.juniper.kassa.network.controller.Controller;

public class ProductController extends Controller {

	public ProductInfo getProduct(String code, String jwt) {
		String route = "/Product/" + code;

		HttpRequest request = getRequest(route, jwt);

		try {
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
			System.out.println(response.body());

			if(response.statusCode() != 200) {
				return null;
			}

			JSONObject responseObject = new JSONObject(response);
			return new ProductInfo(
					UUID.fromString(responseObject.getString("id")),
					responseObject.getString("name"),
					responseObject.getDouble("price"), 
					/*responseObject.getInt("productStatus")*/0);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
