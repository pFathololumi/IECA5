package main.java.net.internetengineering.myServiceHandlers;

import main.java.net.internetengineering.domain.dealing.BuyingOffer;
import main.java.net.internetengineering.exception.DataIllegalException;
import main.java.net.internetengineering.logger.MyLogger;
import main.java.net.internetengineering.server.StockMarket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class BuyOrder{
	public static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyLogger logger = new MyLogger(new ArrayList<String>());
		try {
			String id = request.getParameter("id");
			String instrument = request.getParameter("instrument");
			String type = request.getParameter("type");
			Long price = Long.parseLong(request.getParameter("price"));
			Long quantity = Long.parseLong(request.getParameter("quantity"));
			BuyingOffer buyingOffer = new BuyingOffer(price,quantity,type,id);
			if(instrument==null || instrument.isEmpty())
				throw new DataIllegalException("Mismatched Parameters");
			buyingOffer.validateVariables();
			StockMarket.getInstance().executeBuyingOffer(logger,buyingOffer,instrument);
			request.setAttribute("successes", logger.getAndFlushMyLogger());
		} catch (DataIllegalException e) {
			logger.info(e.getMessage());
			request.setAttribute("errors", logger.getAndFlushMyLogger());
		}catch(Exception e){
			logger.info("Mismatched Parameters");
			request.setAttribute("errors", logger.getAndFlushMyLogger());
		}
		request.getRequestDispatcher("show-info.jsp").forward(request, response);
	}

	public static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}
