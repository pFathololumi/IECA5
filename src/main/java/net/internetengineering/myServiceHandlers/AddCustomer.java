package main.java.net.internetengineering.myServiceHandlers;

import main.java.net.internetengineering.domain.Customer;
import main.java.net.internetengineering.exception.DataIllegalException;
import main.java.net.internetengineering.server.StockMarket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add")
public class AddCustomer extends MyHttpServlet{

	public void doMyPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
    	String name = request.getParameter("name");
    	String family = request.getParameter("family");
    	if(id==null || id.isEmpty() || name==null || name.isEmpty()
				|| family==null || family.isEmpty()){
			out.println("Mismatched Parameters");		
		}
    	if (StockMarket.getInstance().containCustomer(id)) {
			out.println("Repeated id");
			
		}else{
       	StockMarket.getInstance().addNewCustomer(new Customer(id, name, family));
       	out.println("New user is added");
       }
	}

	public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doMyPost(request,response);
	}
}