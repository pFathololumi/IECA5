<%@page import="ir.ramtung.polling.*"%>
<%@page import="java.util.*"%>

<html>
<head>
	<title>Vote Form</title>
</head>
<body>
<%
	PollRepository repo = PollRepository.getRepository();
	Poll poll = repo.findByCode(request.getParameter("code"));
%>
	<form action="save-vote.jsp" method="GET">
		<h3><%= poll.getSubject() %></h3><p>
<%
	for (int i = 0; i < poll.getChoices().length; i++) {
%>
		<input type="radio" name="choice" value="<%= i %>"/>
		<%= poll.getChoices()[i] %><br>
<%
	}
%>
		<p>
		<input type="submit" value="Vote"/>
		<input type="hidden" name="code" value="<%= poll.getCode() %>"/>
	</form>
</body>
</html>











