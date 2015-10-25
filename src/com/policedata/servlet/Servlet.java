package com.policedata.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.policedata.crimedata.CrimeBreakdown;
import com.policedata.homepage.PostcodeSubmit;
import com.policedata.objects.Coordinates;
import com.policedata.objects.Neighbourhood;
import com.policedata.priorities.*;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/out")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super(); 
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String postcodeString = request.getParameter("postcode");
		Coordinates postcodeCoordinates = PostcodeSubmit.postcodeToCoordinates(postcodeString);
		Neighbourhood postcodeNeighbourhood = PostcodeSubmit.coordinatesToNeighbourhood(postcodeCoordinates);
		
		request.setAttribute("postcode", postcodeString);
		
		request.setAttribute("force", postcodeNeighbourhood.getForce());
		request.setAttribute("neighbourhood", postcodeNeighbourhood.getNeighbourhood());
		
		request.setAttribute("longitude", postcodeCoordinates.getLongitude());
		request.setAttribute("latitude", postcodeCoordinates.getLatitude());
		
		
		CrimeBreakdown.
		request.setAttribute("latitude", Priorities.getRelatedCrimes(sortedCrimes, priorities, categories));
		
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}
}
