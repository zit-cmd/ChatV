package com.vit.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.vit.model.UserModel;
import com.vit.service.ICallAPI;
import com.vit.utils.HttpUtil;

public class CalAPI implements ICallAPI {

	@Override
	public UserModel register(UserModel user) {
		String reg = "http://localhost:8080/chat_servlet/api-register";
		try {
			URL url = new URL(reg);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			request setup
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			connection.setRequestProperty("Accept", "application/json");
//			convert object to json
			Gson gson = new Gson();    
		    String json = gson.toJson(user);
			// For POST only - START
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			os.write(json.getBytes());
			os.flush();
			os.close();
			// For POST only - END
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
//				System.out.println("POST Response Code :: " + responseCode);
				// read the response
	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            return HttpUtil.of(reader).toModel(UserModel.class);
			} else {
				System.out.println(connection.getResponseMessage());
				return null;
			}
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public UserModel login(UserModel user) {
		String login = "http://localhost:8080/chat_servlet/api-login";
		try {
			URL url = new URL(login);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			request setup
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			connection.setRequestProperty("Accept", "application/json");
//			convert object to json
			Gson gson = new Gson();    
		    String json = gson.toJson(user);
			// For POST only - START
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			os.write(json.getBytes());
			os.flush();
			os.close();
			// For POST only - END
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
//				System.out.println("POST Response Code :: " + responseCode);
				// read the response
	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            return HttpUtil.of(reader).toModel(UserModel.class);
			} else {
				System.out.println(connection.getResponseMessage());
				return null;
			}
		} catch (IOException e) {
			return null;
		}
	}
	
}
