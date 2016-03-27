<%@page import="ir.ramtung.polling.*"%>
<%@page import="java.util.*"%>

<html>
<head>
<title>Error in Poll Form</title>
</head>
<body>
	<h3><font color="red">New poll could not be saved due to the following errors:</font></h3>
	<ul>
<%
	ArrayList<String> messages = (ArrayList<String>)request.getAttribute("errors");
	for (String msg : messages) {
%>
		<li><%= msg %></li>
<%
	}
%>
	</ul>
	<p>
	Please <a href="poll-form.jsp">try again</a>, or return to the <a href="poll-list.jsp">list of polls</a>
</body>
</html>