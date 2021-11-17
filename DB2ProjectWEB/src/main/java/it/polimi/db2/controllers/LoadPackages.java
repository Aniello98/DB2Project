
package it.polimi.db2.controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jose4j.json.internal.json_simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.db2.entities.OptionalProduct;
import it.polimi.db2.entities.ServicePackage;
import it.polimi.db2.entities.ValidityPeriod;
import it.polimi.db2.exceptions.ProjectException;
import it.polimi.db2.services.ServicePackageService;

@WebServlet("/LoadPackages")
public class LoadPackages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name="it.polimi.db2.services/ServicePackageService")
	private ServicePackageService spService;
	
	
	public LoadPackages() {
		super();
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ServicePackage> servicePackages = null;
		try {
			servicePackages = spService.findAll();
		} catch (ProjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy MMM dd").create();
		String json = gson.toJson(servicePackages);
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(json);
		
	}

}
