package it.polimi.db2.controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.db2.entities.User;
import it.polimi.db2.services.ReportService;
import it.polimi.db2.services.ServicePackageService;
import it.polimi.db2.services.UserService;
import it.polimi.db2.views.Alert;
import it.polimi.db2.views.AverageOptionalPerPackage;
import it.polimi.db2.views.PurchasesPerPackage;
import it.polimi.db2.views.PurchasesPerPackageAndPeriod;
import it.polimi.db2.views.SalesPerProduct;
import it.polimi.db2.views.SuspendedOrders;
import it.polimi.db2.views.ValueSalesPerPackage;

/**
 * Servlet implementation class LoadReport
 */
@WebServlet("/LoadReport")
public class LoadReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name="it.polimi.db2.services/ReportService")
	private ReportService rService;
	@EJB(name="it.polimi.db2.services/UserService")
	private UserService uService;
       //insolvent user
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<PurchasesPerPackage> purchasesPerPackage = rService.getPurchasesPerPackage();
		
		List<PurchasesPerPackageAndPeriod> purchasesPerPackageAndPeriod = rService.getPurchasesPerPackageAndPeriod();
		
		List<AverageOptionalPerPackage> averageOptionalPerPackage = rService.getAverageOptionalPerPackage();
		
		List<SalesPerProduct> bestOptionalProductSeller = rService.getBestOptionalProductSeller();
		
		List<Alert> alert = rService.getAlert();
		
		List<ValueSalesPerPackage> valueSalesPerPackage = rService.getValueSalesPerPackage();
		
		List<SuspendedOrders> suspendedOrders = rService.getSuspendedOrders();
		
		List<User> insolventUsers = uService.getInsolvent();

		
		
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String jsonPurchasesPerPackage = gson.toJson(purchasesPerPackage);
		String jsonPurchasesPerPackageAndPeriod = gson.toJson(purchasesPerPackageAndPeriod);
		String jsonAverageOptionalPerPackage = gson.toJson(averageOptionalPerPackage);
		String jsonBestOptionalProductSeller = gson.toJson(bestOptionalProductSeller);
		String jsonAlert = gson.toJson(alert);
		String jsonValueSalesPerPackage = gson.toJson(valueSalesPerPackage);
		String jsonSuspendedOrders = gson.toJson(suspendedOrders);
		String jsonInsolventUsers = gson.toJson(insolventUsers);

		

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("{"+'"'+"purchasesPerPackage"+'"'+':');
		response.getWriter().print(jsonPurchasesPerPackage);
		response.getWriter().print(","+'"'+"purchasesPerPackageAndPeriod"+'"'+':');
		response.getWriter().print(jsonPurchasesPerPackageAndPeriod);
		response.getWriter().print(","+'"'+"averageOptionalPerPackage"+'"'+':');
		response.getWriter().print(jsonAverageOptionalPerPackage);
		response.getWriter().print(","+'"'+"bestOptionalProductSeller"+'"'+':');
		response.getWriter().print(jsonBestOptionalProductSeller);
		response.getWriter().print(","+'"'+"alerts"+'"'+':');
		response.getWriter().print(jsonAlert);
		response.getWriter().print(","+'"'+"valueSalesPerPackage"+'"'+':');
		response.getWriter().print(jsonValueSalesPerPackage);
		response.getWriter().print(","+'"'+"suspendedOrders"+'"'+':');
		response.getWriter().print(jsonSuspendedOrders);
		response.getWriter().print(","+'"'+"insolventUsers"+'"'+':');
		response.getWriter().print(jsonInsolventUsers);
		response.getWriter().print("}");
	}

}
