package main.java.net.internetengineering.myServiceHandlers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hamed Ara on 4/8/2016.
 */
@WebServlet("/transaction")
public class Transaction extends MyHttpServlet  {
    @Override
    public void doMyPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("order");
        String id = request.getParameter("id");
        if(order!=null && order.equals("خرید"))
            BuyOrder.doPost(request,response);
        else if(order!=null && order.equals("فروش"))
            SellOrder.doPost(request,response);
        else
            request.getRequestDispatcher("page-not-found.jsp").forward(request, response);
    }

    @Override
    public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("order");
        if(order!=null && order.equals("خرید"))
            BuyOrder.doGet(request,response);
        else if(order!=null && order.equals("فروش"))
            SellOrder.doGet(request,response);
        else
            request.getRequestDispatcher("page-not-found.jsp").forward(request, response);
    }
}
