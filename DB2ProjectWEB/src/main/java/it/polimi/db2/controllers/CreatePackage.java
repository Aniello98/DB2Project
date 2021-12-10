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

import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.jose4j.json.internal.json_simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import it.polimi.db2.entities.OptionalProduct;
import it.polimi.db2.entities.Order;
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
		
		List<ValidityPeriod> validityPeriods = gson.fromJson(json, listType);
		String[] services = request.getParameterValues("services");
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
		newPackage.setValidityPeriod(packageValidityPeriods);
		newPackage.setServices(packageServices);
		newPackage.setOptionalProducts(packageOptionalProducts);
		
		spService.createPackage(newPackage);
		
		response.setStatus(200);
		response.getWriter().print("Package created succesfully");
		
	}

}
