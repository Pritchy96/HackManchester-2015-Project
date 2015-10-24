package com.policedata.main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.policedata.objects.Coordinates;
import com.policedata.objects.Neighbourhood;
import com.policedata.postcode.PostcodeCheck;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/out")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super(); 
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String postcodeString = request.getParameter("postcode");
		Coordinates postcodeCoordinates = PostcodeCheck.postcodeToCoordinates(postcodeString);
		Neighbourhood postcodeNeighbourhood = PostcodeCheck.coordinatesToNeighbourhood(postcodeCoordinates);
		
		request.setAttribute("postcode", postcodeString);
		
		request.setAttribute("force", postcodeNeighbourhood.getForce());
		request.setAttribute("neighbourhood", postcodeNeighbourhood.getNeighbourhood());
		
		request.setAttribute("longitude", postcodeCoordinates.getLongitude());
		request.setAttribute("latitude", postcodeCoordinates.getLatitude());
		
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}
}
