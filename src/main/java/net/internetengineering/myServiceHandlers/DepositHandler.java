package main.java.net.internetengineering.myServiceHandlers;

import main.java.net.internetengineering.domain.dealing.TransactionType;
import main.java.net.internetengineering.exception.DataIllegalException;
import main.java.net.internetengineering.logger.MyLogger;
import main.java.net.internetengineering.server.StockMarket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/deposit")
public class DepositHandler extends MyHttpServlet{

	public void doMyPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyLogger logger = new MyLogger(new ArrayList<String>());
		try {
			String id = request.getParameter("id");
			Long amount = Long.parseLong(request.getParameter("amount"));
			if (id == null || id.isEmpty() || amount == null)
				throw new DataIllegalException("Mismatched Parameters");
			if (StockMarket.getInstance().containCustomer(id)){
				StockMarket.getInstance().executeFinancialTransaction(id, TransactionType.DEPOSIT,amount);
				logger.info("Successful");
			}
			else {
				throw new DataIllegalException("Unknown user id");
			}
			request.setAttribute("successes", logger.getAndFlushMyLogger());
		}catch (DataIllegalException ex){
			logger.info(ex.getMessage());
			request.setAttribute("errors", logger.getAndFlushMyLogger());
		}catch (Exception e){
			logger.info("Mismatched Parameters");
			request.setAttribute("errors", logger.getAndFlushMyLogger());
		}
		request.getRequestDispatcher("show-info.jsp").forward(request, response);
	}
	

	public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doMyPost(request,response);
    }

}