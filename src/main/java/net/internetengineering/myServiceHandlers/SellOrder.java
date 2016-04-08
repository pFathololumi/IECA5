package main.java.net.internetengineering.myServiceHandlers;

import main.java.net.internetengineering.domain.dealing.SellingOffer;
import main.java.net.internetengineering.exception.DataIllegalException;
import main.java.net.internetengineering.server.StockMarket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SellOrder {
    public static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    public static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doPost(request,response);
    }
}
