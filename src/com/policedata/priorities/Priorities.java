package com.policedata.priorities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.policedata.objects.Neighbourhood;
import com.policedata.objects.Objects;
import com.policedata.objects.Objects.CrimesAtLocation;
import com.policedata.parsing.ObjectMaker;
import com.policedata.requests.Requests;

public class Priorities {

	private static String parsePriorities(Neighbourhood inputNeighbourhood)
	{
		String urlString = urlGeneration(inputNeighbourhood);
		
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
		
		String y = null;
		
		for (JsonElement foo : jsonArray)
		{
			JsonObject bar = foo.getAsJsonObject();
			String str = bar.get("issue").getAsString();
			y = str;
		}
		
		return y;
	}

	public static String[] parsePriorityList(Neighbourhood inputNeighbourhood)
	{
		 String[] splitList = parsePriorities(inputNeighbourhood).split("What:");
		 //get rid of empty first element
		 String[] returnArray = Arrays.copyOfRange(splitList, 1, splitList.length);
		 return returnArray;
	}
	
	
	public static Dictionary<String, ArrayList<CrimesAtLocation>> 
		getRelatedCrimes(Dictionary<String, ArrayList<CrimesAtLocation>> sortedCrimes, String[] priorities,
				ArrayList<Object> categories)
	{
		Dictionary<String, ArrayList<CrimesAtLocation>> relatedCrimes = null;
		
		for(Object category : categories)
		{
			for(String priority: priorities)
			{
				if(priority.contains((CharSequence) category))
				{
					ArrayList<CrimesAtLocation> newList = relatedCrimes.get(priority);
					newList.addAll(sortedCrimes.get(category));
					relatedCrimes.put(priority, newList);
				}
			}
		}
		
		return relatedCrimes;
	}
	
	
	public static String urlGeneration(Neighbourhood inputNeighbourhood)
	  {
	    // do a null check on input argument & object elements
	    if (inputNeighbourhood == null || inputNeighbourhood.getForce() == null ||
	      inputNeighbourhood.getNeighbourhood() == null)
	    {
	      return null;
	    } // if
	    String force = inputNeighbourhood.getForce();
	    String neighbourhood = inputNeighbourhood.getNeighbourhood();

	    String urlString = "https://data.police.uk/api/" + force + "/" + neighbourhood + "/priorities";
	    
	    return urlString;
	  } // urlGeneration

}
