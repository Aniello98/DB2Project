package it.polimi.db2.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.exceptions.SignUpException;
import it.polimi.db2.services.UserService;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
@MultipartConfig
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.mission.services/UserService")
	private UserService usrService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usrn = null;
		String mail = null;
		String pwd = null;
		String repeatPwd = null;
		usrn = request.getParameter("username");
		mail = request.getParameter("mail");
		pwd = request.getParameter("pwd");
		repeatPwd = request.getParameter("repeatPwd");
		
		System.out.println(usrn);
		System.out.println(mail);
		System.out.println(pwd);
		System.out.println(repeatPwd);
		
		//Check if the fields are empty
		if (usrn == null || pwd == null || repeatPwd == null || mail == null || usrn.isEmpty()  || mail.isEmpty() || repeatPwd.isEmpty() || pwd.isEmpty() ) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("Credentials cannot be empty!");
			return;
		}
		
		 String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
         java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
         java.util.regex.Matcher m = p.matcher(mail);
		
         //Check email syntax
		if(!m.matches()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("The syntax of the email is not correct!");
			return;
		}
		
		//Check if password and repeat password are the same
		if(!pwd.equals(repeatPwd)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("Passwords do not match");
			return;
		}
		
		if(usrService.findUserByUsername(usrn).size() != 0) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("The username is already used!");
			return;
		}
		
		if(usrService.findUserByEmail(mail).size() != 0) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("The email is already used!");
			return;
		}
		
		try {
			usrService.createUser(usrn, mail, pwd);
		} catch (SignUpException e) {
			// TODO Auto-generated catch block
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		
		response.setStatus(200);
		response.getWriter().print("User created succesfully");
		
		
	}

}
