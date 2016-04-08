package main.java.net.internetengineering.myServiceHandlers;

import main.java.net.internetengineering.domain.dealing.TransactionType;
import main.java.net.internetengineering.exception.DataIllegalException;
import main.java.net.internetengineering.server.StockMarket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deposit")
public class DepositHandler extends MyHttpServlet{

	public void doMyPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		Long amount =null;
		try{
			amount= Long.parseLong(request.getParameter("amount"));
			if(id==null ||id.isEmpty())
				throw new Exception();
		}catch (Exception e){
			out.println("Mismatched Parameters");
		}

		if (StockMarket.getInstance().containCustomer(id)){
			StockMarket.getInstance().executeFinancialTransaction(id, TransactionType.DEPOSIT,amount);
			out.println("Successful");
		}
		else {
			out.println("Unknown user id");
		}

		StockMarket.getInstance().getDepositRequests().remove(id);
	}
	

	public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doMyPost(request,response);
    }

}