package priorities;

import com.policedata.objects.Neighbourhood;

public class Priorities {

	public static void main(String[] args) {
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

	    String urlString = "https://data.police.uk/api/" + force + "/" + neighbourhood + "/people";
	    
	    return urlString;
	  } // urlGeneration

}
