package com.policedata.team;

import java.util.ArrayList;
import java.util.List;

import com.policedata.objects.Neighbourhood;
import com.policedata.objects.Person;
import com.policedata.parsing.ObjectMaker;

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

  public static List<Person> generate(Neighbourhood inputNeighbourhood)
  {
    // input check
    if (inputNeighbourhood == null)
    {
      return null;
    }

    String urlString = urlGeneration(inputNeighbourhood);

    List<Object> objectList = ObjectMaker.generateObjectList(urlString, Person.class);
    List<Person> personList = new ArrayList<Person>();
    
    for (Object obj : objectList)
    {
    	Person person = Person.class.cast(obj);
    	personList.add(person);
    }

    return personList;
  }
}
