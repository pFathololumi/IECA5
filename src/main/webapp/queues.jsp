<%@page import="main.java.net.internetengineering.server.StockMarket"%>
<%@page import="java.util.*"%>
<%@ page import="main.java.net.internetengineering.domain.dealing.Instrument" %>
<%@ page import="main.java.net.internetengineering.domain.dealing.SellingOffer" %>
<%@ page import="main.java.net.internetengineering.domain.dealing.BuyingOffer" %>
<!DOCTYPE html>
<html>
<head>
    
    <meta http-equiv="content-type" content="text/html; charset=windows-1252" />

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>نمایش صفوف خرید و فروش</title>

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="style/myStyle.css" />
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="form/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="fonts/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="form/form-elements.css">
    <link rel="stylesheet" href="form/style.css">
</head>
<body>

    <jsp:include page="/menu.jsp" />
	<jsp:include page="/header.jsp" />
<%
    StockMarket s = StockMarket.getInstance();
        for(Instrument ins : s.getInstruments()){
%>
    <div class="right-to-left width-percent">
        <div class="panel-group">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4>
                        <span class="glyphicon-plus text-primary"><div class="inline-style"><%="نام سهام : " +ins.getSymbol()+" - "+" تعداد : "+ ins.getQuantity()%></div></span>
                    </h4>
                </div>
                <div >
                    <ul class="no-padding-component">
                        <li class="list-group-item"><span class="glyphicon-pencil text-info"><div class="inline-style">لیست خرید</div></span>
                            <ul class="list-group">
<%for(SellingOffer sell : ins.getSellingOffers()) {%>
                                <li class="list-group-item"><span class="glyphicon-minus text-success">
                                    <div class="inline-style"><%="شماره فروشنده : "+sell.getID()+" & "+"تعداد : "+sell.getQuantity()+" & "+"قیمت : "+sell.getPrice()+" & "+"نوع : "+sell.getType() %></div>
                                </span>
                                </li>
<%}%>
                            </ul>
                        </li>
                        <li class="list-group-item"><span class="glyphicon-pencil text-info"><div class="inline-style">لیست فروش</div></span>
<% for(BuyingOffer buy: ins.getBuyingOffers()){%>
                            <ul class="list-group">
                                <li class="list-group-item"><span class="glyphicon-minus text-success">
                                    <div class="inline-style"><%="شماره خریدار : "+buy.getID()+" & "+"تعداد : "+buy.getQuantity()+" & "+"قیمت : "+buy.getPrice()+" & "+"نوع : "+buy.getType() %></div>
                                </span>
                                </li>
                            </ul>
<%}%>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
<%
    }
%>
     <jsp:include page="/footer.jsp" />
</body>
</html>
