package it.polimi.db2.controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.db2.entities.Order;
import it.polimi.db2.services.OrderService;

/**
 * Servlet implementation class PaymentChecker
 */
@WebServlet("/PaymentChecker")
@MultipartConfig
public class PaymentChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.services/OrderService")
	private OrderService oService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentChecker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean payed = (boolean) request.getAttribute("paymentResult");
		Order orderToUpdate = (Order)request.getAttribute("order");
		
		System.out.println("Cerco ordine #"+orderToUpdate.getId());
		
		Order order = oService.findOrder(orderToUpdate.getId());
		
		
		if(order == null) {
			System.out.println("Ordine non trovato");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("Order not found");
			return;
		}
		
		System.out.println("Ordine trovato");
		
		if(!payed){
			System.out.println("Pagamento rifiutato");
			order.setRejected(true);
			order.setLastRejected(new Date(System.currentTimeMillis()));
		}
		else {
			System.out.println("Pagamento eseguito");
			order.setRejected(false);
		}
		System.out.println("Aggiorno ordine");
		oService.updateOrder(order);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
		String json = gson.toJson(order);
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(json);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
