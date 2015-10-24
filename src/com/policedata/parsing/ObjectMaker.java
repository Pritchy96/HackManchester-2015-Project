package com.policedata.parsing;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.policedata.objects.Objects.*;
import com.policedata.requests.Requests;

/**
 * This class is used for the parsing of string json to Json Objects &
 * also mapping them to java objects.
 */
public class ObjectMaker
{
	/**
	 * This method is used to check to see whether or not a String is valid
	 * json. If it is value json it will return true, else it will return false.
	 */
	public static boolean isJsonValid(String inputJsonString)
	{
		// check the inputted string for a null value or empty string
		if (inputJsonString == null || inputJsonString.isEmpty())
		{
			return false;
		} // if

		JsonParser jsonParser = new JsonParser();

		try
		{
			jsonParser.parse(inputJsonString).getAsJsonObject();
		} // try
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		} // catch

		return true;

	} // isJsonValid

	/**
	 * This method is used to parse a string to a JsonObject. This method requires
	 * the Gson library.
	 * This method will return a null value if there is an issue. You are required
	 * to do a null check after calling it.
	 */
	public static JsonObject stringToJsonObject(String inputJsonString)
	{
		// check the inputted string for a null value or empty string.
		if (inputJsonString == null || inputJsonString.isEmpty())
		{
			return null;
		}
		else if (!isJsonValid(inputJsonString))
		{
			return null;
		} // if
		
		JsonParser jsonParser = new JsonParser();

		try
		{
			JsonObject jsonObject = jsonParser.parse(inputJsonString).getAsJsonObject();
			return jsonObject;
		} // try
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		} // catch
	} // stringToJsonObject

	/**
	 * This method return and empty list on error -> not null.
	 */
	public static List<Object> generateObjectList(String urlString, Class<?> objectClass)
	{
		List<Object> objectList = new ArrayList<Object>();

		if (urlString == null || urlString.isEmpty() || objectClass == null)
		{
			return objectList;
		} // if

		String httpResonse = null;

		try
		{
			httpResonse = Requests.getRequest(urlString);
		} // try
		catch (Exception exception)
		{
			exception.printStackTrace();
			return objectList;
		} // catch
		
		JsonObject jsonObject = stringToJsonObject(httpResonse);
		JsonArray jsonArray = jsonObject.getAsJsonArray();

		Gson gsonObj = new Gson();

		for (JsonElement jsonElement : jsonArray)
		{
			try
			{
				Priorities listItem = gsonObj.fromJson(jsonElement, Priorities.class);
				objectList.add(listItem);
			} // try
			catch (Exception exception)
			{
				exception.printStackTrace();
				return objectList;
			} // catch
		} // for
		return objectList;
	} // generatePrioritiesList
} // class
