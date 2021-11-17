
package it.polimi.db2.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.internal.oxm.schema.model.List;
import org.jose4j.json.internal.json_simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.db2.entities.OptionalProduct;
import it.polimi.db2.entities.ValidityPeriod;

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
		
		ValidityPeriod v = new ValidityPeriod();
		v.setMonthlyFee((float) 12.5);
		v.setMonths(24);
		
		ArrayList<ValidityPeriod> validityPeriods = new ArrayList<ValidityPeriod>();
		validityPeriods.add(v);
		
		OptionalProduct op1 = new OptionalProduct();
		op1.setMonthlyFee((float) 15.6);
		op1.setName("Prodotto1");
		
		OptionalProduct op2 = new OptionalProduct();
		op2.setMonthlyFee((float) 20.6);
		op2.setName("Prodotto2");
		
		ArrayList<OptionalProduct> optionalProducts = new ArrayList<OptionalProduct>();
		optionalProducts.add(op1);
		optionalProducts.add(op2);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy MMM dd").create();
		String json1 = gson.toJson(validityPeriods);
		String json2 = gson.toJson(optionalProducts);
		
		String json = "{"+'"'+"validityPeriods"+'"'+":"+ json1 + ", "+'"'+"optionalProducts"+'"'+" :"+ json2 + "}";
		
		
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(json);

	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
