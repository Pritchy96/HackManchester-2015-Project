package com.policedata.crimedata;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

import com.policedata.objects.*;
import com.policedata.objects.Objects.CrimesAtLocation;
import com.policedata.parsing.ObjectMaker;

// Does calculations on the crime data got from JSON for stuff like
//Number of crimes

public class CrimeBreakdown {
	
	public static void test() throws Exception
	{
		
	Date currentDate = new Date();
	//date=2012-02 (YYYY-MM)
	String url = "date=" + new SimpleDateFormat("yyyy'-'MM").format(currentDate);

	System.out.println(url);
	//ArrayList<Objects.CrimesAtLocation> oneMonth = ObjectMaker.GetCrimesAtLocation("https://data.police.uk/api/crimes-at-location?date=2012-02&lat=52.629729&lng=-1.131592"), sixMonths, twelveMonths;
	
	
	ArrayList<Object> categories 
		= ObjectMaker.generateObjectList("https://data.police.uk/api/crime-categories", Objects.CrimeCategories.class);
	
	Dictionary<String, ArrayList<CrimesAtLocation>> sortedCrimes = new Hashtable<String, ArrayList<CrimesAtLocation>>();
	
	for(CrimesAtLocation crime : oneMonth)
	{
		try
		{
			sortedCrimes.get(crime.getCategory()).add(crime);
		}
		catch(Exception nullPointerException)
		{
			sortedCrimes.put(crime.getCategory(), new ArrayList<Objects.CrimesAtLocation>());
			sortedCrimes.get(crime.getCategory()).add(crime);
		}
	}
	
	for(int i = 0; i < sortedCrimes.get("anti-social-behaviour").size(); i++)
	{
		System.out.println(sortedCrimes.get("anti-social-behaviour").get(i).getLatitude());
	}
	//categories.
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
