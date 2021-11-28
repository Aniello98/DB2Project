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

import it.polimi.db2.entities.Order;
import it.polimi.db2.entities.User;
import it.polimi.db2.services.OrderService;
import it.polimi.db2.services.UserService;

/**
 * Servlet implementation class LoadRejected
 */
@WebServlet("/LoadRejected")
public class LoadRejected extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.services/UserService")
	private UserService uService;
	@EJB(name = "it.polimi.db2.services/OrderService")
	private OrderService oService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadRejected() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = uService.findUserByUsername(request.getParameter("username"));
		
		List<Order> rejectedOrders = oService.findRejectedByUser(user);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
		String json = gson.toJson(rejectedOrders);
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(json);
		
		
	}

}
