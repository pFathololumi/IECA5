<%@page import="main.java.net.internetengineering.myServiceHandlers.*"%>

<HTML>
    <HEAD>
        <TITLE>Reading Radio Buttons</TITLE>
    </HEAD>

    <BODY>
        
        <%
            if(request.getParameter("radio") != null) {
                if(request.getParameter("radio").equals("buy")) {
				ServletContext context= getServletContext();
				RequestDispatcher rd= context.getRequestDispatcher("/buy");
				out.println(request.getParameter("id"));
				rd.forward(request, response);
                    
                }
                
                else if(request.getParameter("radio").equals("sell")) {
                    out.println("sell was selected.<BR>");
                }  
            }
        %>

		
    </BODY>
</HTML>