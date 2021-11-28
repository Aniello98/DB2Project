package it.polimi.db2.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.entities.User;
import it.polimi.db2.exceptions.UpdateProfileException;
import it.polimi.db2.services.UserService;

/**
 * Servlet implementation class TestUser
 */
@WebServlet("/TestUser")
public class TestUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.mission.services/UserService")
	private UserService usrService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int name = Integer.parseInt(request.getParameter("name"));
		
		User user = usrService.findUserById(name);
		
		user.setEmail("Prova");
		
		try {
			usrService.updateProfile(user);
		} catch (UpdateProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
