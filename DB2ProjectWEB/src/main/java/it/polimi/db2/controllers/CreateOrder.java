package it.polimi.db2.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import it.polimi.db2.entities.Order;
import it.polimi.db2.exceptions.ProjectException;
import it.polimi.db2.services.OptionalProductService;
import it.polimi.db2.services.ServicePackageService;
import it.polimi.db2.services.ValidityPeriodService;

/**
 * Servlet implementation class CreateOrder
 */
@WebServlet("/CreateOrder")
@MultipartConfig
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.services/ValidityPeriodService")
	private ValidityPeriodService vpService;
	@EJB(name = "it.polimi.db2.services/ServicePackageService")
	private ServicePackageService spService;
	@EJB(name = "it.polimi.db2.services/OptionalProductService")
	private OptionalProductService opService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		String[] products = request.getParameterValues("optional-products");
		
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("startDate"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Order order = new Order();
		
		//set start date
		order.setStartDate(date);
		
		//set validity period
		try {
			order.setValidityPeriod(vpService.findPeriodById(Integer.parseInt(request.getParameter("validityPeriod"))));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//set service package
		try {
			order.setServicePackage(spService.findPackageById(Integer.parseInt(request.getParameter("packageId"))));
		} catch (NumberFormatException | ProjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<OptionalProduct> optionalProducts = new ArrayList<OptionalProduct>();
		
		if(products!=null) {
			
		float productsValue = 0;
		for(int i=0; i<products.length; i++) {
			OptionalProduct op = new OptionalProduct();
			try {
				op = opService.findProductById(Integer.parseInt(products[i]));
			} catch (NumberFormatException | ProjectException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			productsValue = productsValue + op.getMonthlyFee();
			optionalProducts.add(op);
			
		}
		//set info about optional product
			order.setProductsValue(productsValue * order.getValidityPeriod().getMonths());
			order.setTotalOptional(products.length);
		}
		
		order.setOptionalProducts(optionalProducts);	
		
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
		String json = gson.toJson(order);
	
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().println(json);
	}

}
