package com.policedata.homepage;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.policedata.objects.Coordinates;
import com.policedata.objects.Neighbourhood;
import com.policedata.requests.Requests;

public class PostcodeSubmit
{
	/**
	 * This method will check a postcode string to make sure it is a valid format.
	 * @param postcodeString
	 * @return A boolean
	 */
	public static boolean isPostcodeValid(String postcodeString)
	{
		// Perform null check on inputted postcode string
		if (postcodeString == null)
		{
			return false;
		} // if
		
		// Get the length of the postcode string
		int postcodeLength = postcodeString.length();
		
		// A valid postcode needs to be between 6 and 8 characters long
		if (postcodeLength < 6 || postcodeLength > 8)
		{
			return false;
		}
		
		return true;
	} // validator
	
	public static Coordinates postcodeToCoordinates(String postcodeString)
	{
		// Check to see if the postcode is valid before sending a request to postcode api
		if (!isPostcodeValid(postcodeString))
		{
			return null;
		}
		
		postcodeString = postcodeString.replace(" ", "");
		String urlStr = "https://api.postcodes.io/postcodes/".concat(postcodeString);
		
		// Attempt to request postcode information from api
		String jsonResponse = "";
		try
		{
			jsonResponse = Requests.getRequest(urlStr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(jsonResponse);
		
		jsonObject = jsonObject.get("result").getAsJsonObject();
		
		Double longitudeValue = jsonObject.get("longitude").getAsDouble();
		Double latitudeValue = jsonObject.get("latitude").getAsDouble();
		
		Coordinates postcodeCoordinates = new Coordinates();
		
		postcodeCoordinates.setLongitude(longitudeValue);
		postcodeCoordinates.setLatitude(latitudeValue);
		
		return postcodeCoordinates;
	} // postcodeToCoordinates
	
	public static Neighbourhood coordinatesToNeighbourhood(Coordinates inputCoordinates)
	{
		Neighbourhood outputNeighbourhoodObj = new Neighbourhood();
		
		if (inputCoordinates == null)
		{
			return null; 
		} // if
		
		Double longitudeValue = inputCoordinates.getLongitude();
		Double latitudeValue = inputCoordinates.getLatitude();
		
		if (longitudeValue == null || latitudeValue == null)
		{
			return null;
		} // if
		
		// TODO: replace string manipulation with solid url (uri) building
		String urlString = "https://data.police.uk/api/locate-neighbourhood?q=";
		String urlParameters = latitudeValue + "," + longitudeValue;
		
		urlString += urlParameters;
		
		String jsonResponse = "";
		
		try
		{
			jsonResponse = Requests.getRequest(urlString);
		} // try
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		} // catch
		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(jsonResponse);
		
		String force = jsonObject.get("force").getAsString();
		outputNeighbourhoodObj.setForce(force);
		String neighbourhood = jsonObject.get("neighbourhood").getAsString();
		outputNeighbourhoodObj.setNeighbourhood(neighbourhood);
		
		return outputNeighbourhoodObj;
		
	} // coordinatesToNeighbourhood
} // class