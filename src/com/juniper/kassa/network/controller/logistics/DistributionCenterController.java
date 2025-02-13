package com.juniper.kassa.network.controller.logistics;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.juniper.kassa.network.GetRequest;

public class DistributionCenterController {

	public Map<String, String> getDistributionCenterNames(String jwt) {
		String route = "/DistributionCenter/GetAllNames";

		try {
			GetRequest request = new GetRequest(route);
			request.useToken(jwt);

			HttpResponse<String> response = (HttpResponse<String>) request.send();

			if(response.statusCode() != 200) {
				return null;
			}

			JSONArray           responseArray = new JSONArray(response.body());
			Map<String, String> map           = new HashMap<String, String>();
			
			for(int i = 0; i < responseArray.length(); i++) {
				JSONObject obj = responseArray.getJSONObject(i);
				map.put(obj.getString("name"), obj.getString("id"));
			}
			
			return map;

		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
