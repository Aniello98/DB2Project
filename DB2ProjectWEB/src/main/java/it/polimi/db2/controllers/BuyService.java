
package it.polimi.db2.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BuyService")
public class BuyService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BuyService() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		String servicePackage = request.getParameter("service");

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter()
				.println("{" + '"' + "validityPeriods" + '"' + ": [" + '"' + "validity1" + '"' + "," +'"'
						+ "validity2" + '"' + "," + '"' + "validity3" + '"' + "]," + '"' + "optionalProducts"
						+ '"' + ": [" +'"' + "optional1" + '"' + "," + '"' + "optional2" + '"' + "," +'"'
						+ "optional3" + '"'  + "]}");

	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
