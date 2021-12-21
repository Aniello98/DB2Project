package it.polimi.db2.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

//import org.jose4j.json.internal.json_simple.JSONObject;

//import org.apache.commons.lang.StringEscapeUtils;

import it.polimi.db2.services.UserService;
import it.polimi.db2.entities.User;
import it.polimi.db2.exceptions.CredentialsException;
import javax.persistence.NonUniqueResultException;

@WebServlet("/CheckLogin")
@MultipartConfig
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.mission.services/UserService")
	private UserService usrService;

	public CheckLogin() {
		super();
	}

	public void init() throws ServletException {
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usrn = null;
		String pwd = null;
		
		usrn = request.getParameter("username");
		pwd = request.getParameter("pwd");
		
		if (usrn == null || pwd == null || usrn.isEmpty() || pwd.isEmpty() ) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("Credentials must be not null");
			return;
		}
		
		User user;
		try {
			// query db to authenticate for user
			user = usrService.checkCredentials(usrn, pwd);
		} catch (CredentialsException | NonUniqueResultException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
			return;
		}
		

		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().println("Incorrect credentials");
		} else {
			JSONObject json = new JSONObject();
			json.put("usrn", user.getUsername());
			json.put("isEmployee", user.isEmployee());
			if(user.isEmployee()) {
				request.getSession().setAttribute("role", "employee");
			}
			else {
				request.getSession().setAttribute("role", "customer");
			}
			request.getSession().setAttribute("user", user);
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(json);
		}

	}
	
	

	public void destroy() {
	}
}