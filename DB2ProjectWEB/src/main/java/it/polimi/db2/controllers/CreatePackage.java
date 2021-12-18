package it.polimi.db2.controllers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import it.polimi.db2.entities.OptionalProduct;
import it.polimi.db2.entities.Service;
import it.polimi.db2.entities.ServicePackage;
import it.polimi.db2.entities.ValidityPeriod;
import it.polimi.db2.exceptions.ProjectException;
import it.polimi.db2.services.OptionalProductService;
import it.polimi.db2.services.ServicePackageService;
import it.polimi.db2.services.ServiceService;
import it.polimi.db2.services.ValidityPeriodService;

/**
 * Servlet implementation class CreatePackage
 */
@WebServlet("/CreatePackage")
@MultipartConfig
public class CreatePackage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.services/ServiceService")
	private ServiceService sService;
	@EJB(name = "it.polimi.db2.services/ValidityPeriodService")
	private ValidityPeriodService vpService;
	@EJB(name = "it.polimi.db2.services/OptionalProductService")
	private OptionalProductService opService;
	@EJB(name = "it.polimi.db2.services/ServicePackageService")
	private ServicePackageService spService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePackage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Type listType = new TypeToken<List<ValidityPeriod>>()
        {
        }.getType();
		
		
		String name = request.getParameter("name");
		String json = request.getParameter("validityPeriods");

		Gson gson = new Gson(); // Or use new GsonBuilder().create();
		
		if(name.length()==0){
			response.setStatus(200);
			response.getWriter().print("You must specify a name!");
			return;
		}
		
		if(spService.findPackageByName(name).size()!=0) {
			response.setStatus(200);
			response.getWriter().print("Package name already used!");
			return;
		}
		
		List<ValidityPeriod> validityPeriods=new ArrayList<ValidityPeriod>();
		

		
		try {
		validityPeriods = gson.fromJson(json, listType);
		}catch(NumberFormatException e){
			response.setStatus(200);
			response.getWriter().print("Each period must have a valid fee!");
			return;
		}
		
		if(validityPeriods.size()==0) {
			response.setStatus(200);
			response.getWriter().print("A package must contain at least one validity period!");
			return;
		}
		
		
		
		String[] services = request.getParameterValues("services");
		
		if(services==null) {
			response.setStatus(200);
			response.getWriter().print("A package must contain at least one service!");
			return;
		}
		
		
		String[] products = request.getParameterValues("optional-products");
		
		
		ServicePackage newPackage = new ServicePackage();
		newPackage.setName(name);
		List<ValidityPeriod> packageValidityPeriods = new ArrayList<>();
		
		for(ValidityPeriod v : validityPeriods) {
			
			
			List<ValidityPeriod> vp = vpService.findByParameters(v.getMonths(), v.getMonthlyFee());
			if(vp.size() == 0) {
				vpService.createValidityPeriod(v);
				packageValidityPeriods.add(v);
				
			}else {
				packageValidityPeriods.add(vp.get(0));
			}
		}
		
		List<Service> packageServices = new ArrayList<Service>();
		for(int i=0; i<services.length; i++) {
			packageServices.add(sService.findById(Integer.parseInt(services[i])));
		}
		
		List<OptionalProduct> packageOptionalProducts = new ArrayList<OptionalProduct>();
		if(products!=null) {
			for(int i=0; i<products.length; i++) {
				try {
					packageOptionalProducts.add(opService.findProductById(Integer.parseInt(products[i])));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ProjectException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		newPackage.setValidityPeriod(packageValidityPeriods);
		newPackage.setServices(packageServices);
		newPackage.setOptionalProducts(packageOptionalProducts);
		
		spService.createPackage(newPackage);
		
		response.setStatus(200);
		response.getWriter().print("Package created succesfully");
		
	}

}
