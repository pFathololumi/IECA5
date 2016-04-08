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
    <title>بررسی درخواست های افزایش اعتبار</title>

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="../../style/myStyle.css" />
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="../../form/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../fonts/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../form/form-elements.css">
    <link rel="stylesheet" href="../../form/style.css">
</head>
<body>

   
<%
    StockMarket s = StockMarket.getInstance();
    StockMarket.getDepositRequests().remove(request.getParameter("id"));
%>

	 <jsp:forward page="index.jsp">
</body>
</html>
