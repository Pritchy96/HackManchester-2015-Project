package com.policedata.parsing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.policedata.objects.Objects;
import com.policedata.objects.Objects.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ObjectMaker {
	private static String ReadUrl(String urlInput) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlInput);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null) {
				reader.close();
				System.out.println("");
			}
		}
	}

	public static ArrayList<Priorities> GetPriorities(String url) throws Exception {
		String data = ReadUrl(url);
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonArray jArray = parser.parse(data).getAsJsonArray();
		ArrayList<Priorities> dataArray = new ArrayList<Priorities>();

		for (JsonElement obj : jArray) {
			Priorities item = gson.fromJson(obj, Priorities.class);
			dataArray.add(item);
		}
		return dataArray;
	}

	public static ArrayList<CrimeCategories> GetCrimeCategories(String url) throws Exception {
		String data = ReadUrl(url);
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonArray jArray = parser.parse(data).getAsJsonArray();
		ArrayList<CrimeCategories> dataArray = new ArrayList<CrimeCategories>();

		for (JsonElement obj : jArray) {
			CrimeCategories item = gson.fromJson(obj, CrimeCategories.class);
			dataArray.add(item);
		}

		return dataArray;
	}

	public static ArrayList<CrimesAtLocation> GetCrimesAtLocation(String url) throws Exception {
		String data = ReadUrl(url);
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonArray jArray = parser.parse(data).getAsJsonArray();
		ArrayList<CrimesAtLocation> dataArray = new ArrayList<CrimesAtLocation>();

		for (JsonElement obj : jArray) {
			CrimesAtLocation item = gson.fromJson(obj, CrimesAtLocation.class);
			dataArray.add(item);

			JsonObject location = item.getLocationOfCrime();
			item.setLatitude(location.get("latitude").toString());
			item.setLongitude(location.get("longitude").toString());
			
		}
		


		return dataArray;
	}
	
}
