package com.policedata.crimedata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.policedata.objects.*;
import com.policedata.objects.Objects.CrimeCategories;
import com.policedata.objects.Objects.CrimesAtLocation;
import com.policedata.parsing.ObjectMaker;
import com.policedata.requests.Requests;

// Does calculations on the crime data got from JSON for stuff like
//Number of crimes

public class CrimeBreakdown
{
	
	public static Map<String, List<CrimesAtLocation>> test(Coordinates neighbourhoodCoordinates) throws Exception
	{
		Date currentDate = new Date();
		//date=2012-02 (YYYY-MM)
		String dateValue = "?date=2014-10";
		
		List<CrimeCategories> categories = new ArrayList<CrimeCategories>();
		String urlString = "https://data.police.uk/api/crime-categories";
		
		JsonParser jsonParser = new JsonParser();
		String httpResponse = null;
		try
		{
			httpResponse = Requests.getRequest(urlString);
		} // try
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		} // catch
		
		JsonElement jsonElement = jsonParser.parse(httpResponse);
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		
		for (JsonElement element : jsonArray)
		{
			CrimeCategories crimeCategories = new Gson().fromJson(element, CrimeCategories.class);
			System.out.println(crimeCategories.getUniqueID());
			categories.add(crimeCategories);
		}
		
		
		String longitudeValue = "&lng=" + neighbourhoodCoordinates.getLongitude();
		String latitudeValue = "&lat=" + neighbourhoodCoordinates.getLatitude();
		
		urlString = "https://data.police.uk/api/crimes-at-location" + dateValue + latitudeValue + longitudeValue;
		
		httpResponse = null;
		try
		{
			httpResponse = Requests.getRequest(urlString);
		} // try
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		} // catch
		
		jsonElement = jsonParser.parse(httpResponse);
		jsonArray = jsonElement.getAsJsonArray();
		
		List<CrimesAtLocation> crimesAtLocationList = new ArrayList<CrimesAtLocation>();
		
		for (JsonElement element : jsonArray)
		{
			CrimesAtLocation crimesAtLocation = new Gson().fromJson(element, CrimesAtLocation.class);
			crimesAtLocationList.add(crimesAtLocation);
		}
		
		Map<String, List<CrimesAtLocation>> sortedCrimes = new HashMap<String, List<CrimesAtLocation>>();
		ArrayList<CrimesAtLocation> emptyArrayList = new ArrayList<CrimesAtLocation>();
		
		for (Object obj : categories)
		{
			CrimeCategories crimeCategoryObj = (CrimeCategories) obj;
			String crimeCategoryStr = crimeCategoryObj.getUniqueID();
			sortedCrimes.put(crimeCategoryStr, emptyArrayList);
		}
		
		for (CrimesAtLocation crimesAtLocation : crimesAtLocationList)
		{
			String crimeCategory = crimesAtLocation.getCategory();
			
			sortedCrimes.get(crimeCategory).add(crimesAtLocation);
		}
		
		return sortedCrimes;
	}
	
	public static String urlGeneration(Coordinates inputCoordinates, Date inputDate)
	  {
	    // do a null check on input argument & object elements
	    if (inputCoordinates == null || inputCoordinates.getLatitude() == null ||
	    		inputCoordinates.getLongitude() == null || inputDate == null)
	    {
	      return null;
	    } // if
	    
	    Double latitude = inputCoordinates.getLatitude(), 
	    		longitude = inputCoordinates.getLongitude();
	    String date = "date=" + new SimpleDateFormat("yyyy'-'MM").format(inputDate);
	    
	    String urlString = "https://data.police.uk/api/crimes-at-location?" + date + "&lat=" + latitude + "&lng=" + longitude;
	    
	    return urlString;
	  } // urlGeneration
	
}
