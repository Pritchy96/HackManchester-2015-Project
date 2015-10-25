package com.policedata.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.policedata.crimedata.CrimeBreakdown;
import com.policedata.homepage.PostcodeSubmit;
import com.policedata.objects.Coordinates;
import com.policedata.objects.Neighbourhood;
import com.policedata.objects.Objects.CrimesAtLocation;
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
		request.setAttribute("postcode", postcodeString);
		
		Coordinates postcodeCoordinates = PostcodeSubmit.postcodeToCoordinates(postcodeString);
		 if (postcodeCoordinates != null)
		 {
			request.setAttribute("longitude", postcodeCoordinates.getLongitude());
	 		request.setAttribute("latitude", postcodeCoordinates.getLatitude());
		 }

		Neighbourhood postcodeNeighbourhood = PostcodeSubmit.coordinatesToNeighbourhood(postcodeCoordinates);
		if (postcodeNeighbourhood != null)
		{
			request.setAttribute("force", postcodeNeighbourhood.getForce());
			request.setAttribute("neighbourhood", postcodeNeighbourhood.getNeighbourhood());
		}
		
		try
		{
			Dictionary<String, ArrayList<CrimesAtLocation>> foo = CrimeBreakdown.test(postcodeCoordinates);
			request.setAttribute("lol", foo.size() + "a");
			request.setAttribute("waka", foo);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}

		request.getRequestDispatcher("result.jsp").forward(request, response);
	}
}
