package it.polimi.db2.controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.db2.entities.User;
import it.polimi.db2.services.ActivationScheduleService;
import it.polimi.db2.services.ReportService;
import it.polimi.db2.services.UserService;
import it.polimi.db2.views.ActivationSchedule;

/**
 * Servlet implementation class LoadSchedule
 */
@WebServlet("/LoadSchedule")
@MultipartConfig
public class LoadSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.services/UserService")
	private UserService uService;
	@EJB(name = "it.polimi.db2.services/ActivationScheduleService")
	private ActivationScheduleService asService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> users = uService.findUserByUsername(request.getParameter("username"));
		
		if(users.size() == 0) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("The user does not exist!");
			return;
		}
		
		List<ActivationSchedule> schedule = asService.getActivationSchedule(users.get(0).getUsername());
		
	
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(schedule);
		

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(json);
		
	}

	
}
