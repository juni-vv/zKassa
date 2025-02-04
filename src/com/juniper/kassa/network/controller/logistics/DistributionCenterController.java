package com.juniper.kassa.network.controller.logistics;

import java.net.http.HttpResponse;
import java.util.List;

import org.json.JSONObject;

import com.juniper.kassa.network.GetRequest;

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
