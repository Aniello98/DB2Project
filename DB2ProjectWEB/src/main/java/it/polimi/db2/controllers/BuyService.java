
package it.polimi.db2.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.db2.entities.ServicePackage;
import it.polimi.db2.exceptions.ProjectException;
import it.polimi.db2.services.ServicePackageService;

@WebServlet("/BuyService")
public class BuyService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.services/ServicePackageService")
	private ServicePackageService spService;

	public BuyService() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		String id = request.getParameter("packageId");
		int packageId;
		if(id != null) {
			packageId = Integer.parseInt(id);
		}
		else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println("Id cannot be null");
			return;
		}
		
		ServicePackage servicePackage = null;

		try {
			servicePackage = spService.findPackageById(packageId);
		} catch (ProjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.setStatus(HttpServletResponse.SC_OK);
		response.setCharacterEncoding("UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
		String json = gson.toJson(servicePackage);

		if (servicePackage == null) {
			response.getWriter().println("Service package not available");
		}
		else {
			response.setContentType("application/json");
			response.getWriter().println(json);
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
