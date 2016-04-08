package main.java.net.internetengineering.myServiceHandlers;

import main.java.net.internetengineering.domain.Customer;
import main.java.net.internetengineering.exception.DataIllegalException;
import main.java.net.internetengineering.logger.MyLogger;
import main.java.net.internetengineering.server.StockMarket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/add")
public class AddCustomer extends MyHttpServlet{

	public void doMyPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyLogger logger = new MyLogger(new ArrayList<String>());
		try {

			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String family = request.getParameter("family");
			if (id == null || id.isEmpty() || name == null || name.isEmpty()
					|| family == null || family.isEmpty()) {
				throw new DataIllegalException("Mismatched Parameters");
			}
			if (StockMarket.getInstance().containCustomer(id)) {
				throw new DataIllegalException("Repeated id");

			} else {
				StockMarket.getInstance().addNewCustomer(new Customer(id, name, family));
				logger.info("New user is added");
				request.setAttribute("successes", logger.getAndFlushMyLogger());
			}
		}catch (DataIllegalException ex){
			request.setAttribute("errors", logger.getAndFlushMyLogger());
		}
		request.getRequestDispatcher("show-info.jsp").forward(request, response);
	}

	public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doMyPost(request,response);
	}
}