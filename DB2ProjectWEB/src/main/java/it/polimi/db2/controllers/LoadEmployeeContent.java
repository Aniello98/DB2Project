package it.polimi.db2.controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.db2.entities.OptionalProduct;
import it.polimi.db2.entities.Service;
import it.polimi.db2.services.OptionalProductService;
import it.polimi.db2.services.ServicePackageService;
import it.polimi.db2.services.ServiceService;

/**
 * Servlet implementation class LoadEmployeeContent
 */
@WebServlet("/LoadEmployeeContent")
@MultipartConfig
public class LoadEmployeeContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.services/OptionalProductService")
	private OptionalProductService opService;
	@EJB(name = "it.polimi.db2.services/ServiceService")
	private ServiceService sService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadEmployeeContent() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Service> services = sService.findAllServices();
		List<OptionalProduct> optionalProducts = opService.findAllProducts();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
		String jsonS = gson.toJson(services);
		
		String jsonOP = gson.toJson(optionalProducts);
		
		
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("{"+'"'+"services"+'"'+':');
		response.getWriter().print(jsonS);
		response.getWriter().print(","+'"'+"optionalProducts"+'"'+':');
		response.getWriter().print(jsonOP);
		response.getWriter().print("}");
	}

}
