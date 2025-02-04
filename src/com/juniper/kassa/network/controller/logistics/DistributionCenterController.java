package com.juniper.kassa.network.controller.logistics;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;

import com.juniper.kassa.model.product.ProductInfo;
import com.juniper.kassa.model.product.ProductPriceInfo;
import com.juniper.kassa.model.product.ProductStatus;
import com.juniper.kassa.network.GetRequest;
import com.juniper.kassa.network.controller.Controller;

public class DistributionCenterController {

	public List<String> getDistributionCenterNames(String jwt) {
		String route = "/DistributionCenter/GetAllNames";

		try {
			GetRequest request = new GetRequest(route);
			request.useToken(jwt);
			
			HttpResponse<String> response = (HttpResponse<String>) request.send();

			if(response.statusCode() != 200) {
				return null;
			}

			JSONObject responseObject = new JSONObject(response.body());

			System.out.println(responseObject.toString());

		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
