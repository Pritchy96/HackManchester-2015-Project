package com.policedata.area;

import java.util.ArrayList;
import java.util.Dictionary;

import com.google.gson.JsonArray;
import com.policedata.objects.Objects;
import com.policedata.objects.Objects.CrimesAtLocation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class overview {
	public static JsonArray getMapArray(Dictionary<String, ArrayList<CrimesAtLocation>> sortedCrimes, ArrayList<Objects.CrimeCategories> categories )
	{
		JsonArray mapArray = null;
		for(int i = 0; i < categories.size(); i++)
		{
			for (CrimesAtLocation crime : sortedCrimes.get(categories.get(i)))
			{
				JsonObject obj = new JsonObject();
				obj.addProperty("lat: ", crime.getLatitude());
				obj.addProperty("lng: ", crime.getLongitude());
				obj.addProperty("cat: ", crime.getCategory());
				
				mapArray.add(obj);
			}
		}
		
		return mapArray;
	}
}
