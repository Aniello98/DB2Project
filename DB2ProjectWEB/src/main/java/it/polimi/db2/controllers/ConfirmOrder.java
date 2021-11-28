package it.polimi.db2.controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import javax.servlet.http.Cookie;

import it.polimi.db2.entities.Order;
import it.polimi.db2.entities.User;
import it.polimi.db2.services.OptionalProductService;
import it.polimi.db2.services.OrderService;
import it.polimi.db2.services.UserService;

/**
 * Servlet implementation class ConfirmOrder
 */
@WebServlet("/ConfirmOrder")
@MultipartConfig
public class ConfirmOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.services/OrderService")
	private OrderService oService;
	@EJB(name = "it.polimi.db2.services/UserService")
	private UserService uService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String json = request.getParameter("order");
		String username = request.getParameter("user");
		Gson gson = new Gson(); // Or use new GsonBuilder().create();
		
		Order orderRequest = gson.fromJson(json, Order.class);
		
		Order order = oService.findOrder(orderRequest.getId());
		
		if(order == null) {
			order = orderRequest;
			User user = uService.findUserByUsername(username);
			Date date = new Date(System.currentTimeMillis());
			order.setUser(user);
			order.setCreationDate(date);
			oService.persistOrder(order);
		}
		
		request.setAttribute("order", order);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ExternalPaymentService");
		rd.forward(request, response);
	}

}
