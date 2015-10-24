package com.policedata.team;

import com.policedata.objects.Neighbourhood;

public class NeighbourhoodTeam
{
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

    String urlString = "https://data.police.uk/api/" + force + "/" + neighbourhood + "/people";
    
    return urlString;
  } // urlGeneration
}
