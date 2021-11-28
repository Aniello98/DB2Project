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

import it.polimi.db2.entities.Order;
import it.polimi.db2.services.OrderService;

/**
 * Servlet implementation class GetOrder
 */
@WebServlet("/GetOrder")

public class GetOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.services/OrderService")
	private OrderService oService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int orderId = Integer.parseInt(request.getParameter("id"));
		
		Order order = oService.findOrder(orderId);
		
		if(order == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("Order not found");
			return;
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
		String json = gson.toJson(order);
	
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().println(json);
		
	}

	

}
