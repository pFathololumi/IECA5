<%@ page import="main.java.net.internetengineering.server.StockMarket" %>
<%@ page import="main.java.net.internetengineering.domain.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style/myStyle.css" />
    <meta http-equiv="content-type" content="text/html; charset=windows-1252" />

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="form/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="fonts/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="form/form-elements.css">
    <link rel="stylesheet" href="form/style.css">
</head>
<body>
<%
    if(!StockMarket.getInstance().containCustomer("1"))
        StockMarket.getInstance().addNewCustomer(new Customer("1","admin","admin"));
%>
	<jsp:include page="/menu.jsp" />
	<jsp:include page="/header.jsp" />
    <div id="stuffer">به سایت خرید و فروش سهام خوش آمدید.</div>
	<jsp:include page="/footer.jsp" />
    
</body>
</html>
