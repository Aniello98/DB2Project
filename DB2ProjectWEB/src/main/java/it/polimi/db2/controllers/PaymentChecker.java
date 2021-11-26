package it.polimi.db2.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Order order = (Order)request.getAttribute("order");
		
	
		
		response.setContentType("application/json");
		
		if(!payed) {
			response.setStatus(HttpServletResponse.SC_OK);
			System.out.println("Payment false");	
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println("false");
		}
		else {
			System.out.println("Payment true");
			if(order == null) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().println("Order not found");
			}
			else {
				order.setValid(true);
				oService.updateOrder(order);
				response.setStatus(HttpServletResponse.SC_OK);
				response.setCharacterEncoding("UTF-8");
				response.getWriter().println("true");
			}
			
		}
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
