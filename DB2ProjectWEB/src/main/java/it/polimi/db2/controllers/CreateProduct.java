package it.polimi.db2.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.entities.OptionalProduct;
import it.polimi.db2.exceptions.ProjectException;
import it.polimi.db2.services.OptionalProductService;
import it.polimi.db2.services.ServicePackageService;

/**
 * Servlet implementation class CreateProduct
 */
@WebServlet("/CreateProduct")
@MultipartConfig
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.polimi.db2.services/OptionalProductService")
	private OptionalProductService opService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		float fee=0;
		try {
		 fee = Float.parseFloat(request.getParameter("fee"));
		}catch(NumberFormatException e) {
			response.setStatus(200);
			response.getWriter().print("The fee must have a valid value!");
			return;
		}
		
		if(name.length()==0) {
			response.setStatus(200);
			response.getWriter().print("You must specify a name!");
			return;
		}
		
		try {
			if(opService.findByName(name).size()!=0) {
				response.setStatus(200);
				response.getWriter().print("Name already in use!");
				return;
			}
		} catch (ProjectException | IOException e) {
			// TODO Auto-generated catch block
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());

		}
		
		
		OptionalProduct op=new OptionalProduct();
		op.setMonthlyFee(fee);
		op.setName(name);
		
		opService.createOptionalProduct(op);
		
		
		response.setStatus(200);
		response.getWriter().print("Product created succesfully");
	}

}
