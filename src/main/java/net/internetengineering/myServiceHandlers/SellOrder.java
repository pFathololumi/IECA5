package main.java.net.internetengineering.myServiceHandlers;

import main.java.net.internetengineering.domain.dealing.SellingOffer;
import main.java.net.internetengineering.exception.DataIllegalException;
import main.java.net.internetengineering.server.StockMarket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sell")
public class SellOrder extends MyHttpServlet {
    public void doMyPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String instrument = request.getParameter("instrument");
        String type = request.getParameter("type");
        SellingOffer sellingOffer = null;
        PrintWriter out = response.getWriter();
        try {
            Long price = Long.parseLong(request.getParameter("price"));
            Long quantity = Long.parseLong(request.getParameter("quantity"));
            sellingOffer = new SellingOffer(price, quantity, type, id);
            if (instrument == null || instrument.isEmpty())
                throw new DataIllegalException("Mismatched Parameters");
            sellingOffer.validateVariables();
        } catch (DataIllegalException e) {
            out.println(e.getMessage());
            return;
        } catch (Exception e) {
            out.println("Mismatched Parameters");
            return;
        }

        StockMarket.getInstance().executeSellingOffer(out, sellingOffer, instrument);
    }

    public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doMyPost(request,response);
    }
}
