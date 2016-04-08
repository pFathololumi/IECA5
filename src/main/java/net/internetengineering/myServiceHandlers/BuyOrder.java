package main.java.net.internetengineering.myServiceHandlers;

import main.java.net.internetengineering.domain.dealing.BuyingOffer;
import main.java.net.internetengineering.exception.DataIllegalException;
import main.java.net.internetengineering.server.StockMarket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BuyOrder{
	public static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String instrument = request.getParameter("instrument");
		String type = request.getParameter("type");
		BuyingOffer buyingOffer=null;
		PrintWriter out =response.getWriter();
		try {
			Long price = Long.parseLong(request.getParameter("price"));
			Long quantity = Long.parseLong(request.getParameter("quantity"));
			buyingOffer = new BuyingOffer(price,quantity,type,id);
			if(instrument==null || instrument.isEmpty())
				throw new DataIllegalException("Mismatched Parameters");
			buyingOffer.validateVariables();
		} catch (DataIllegalException e) {
			out.println(e.getMessage());
			return;
		}catch(Exception e){
			out.println("Mismatched Parameters");
			return;
		}
		StockMarket.getInstance().executeBuyingOffer(out,buyingOffer,instrument);
	}

	public static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}
