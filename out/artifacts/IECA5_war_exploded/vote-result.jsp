<%@page import="ir.ramtung.polling.*"%>
<%@page import="java.util.*,java.text.*"%>

<html>
<head>
	<title>Poll Result</title>
</head>
<body>
<%
	PollRepository repo = PollRepository.getRepository();
	Poll poll = repo.findByCode(request.getParameter("code"));
%>
	Vote results for <b><big><%= poll.getSubject() %></big></b><p>
	<table cellpadding=5>
<%
	DecimalFormat oneDigit = new DecimalFormat("0.0");
	for (int i = 0; i < poll.getChoices().length; i++) {
%>
		<tr>
			<td><%= oneDigit.format(poll.getVotePercents()[i]) %>%</td>
			<td><%= poll.getChoices()[i] %></td>
		</tr>
<%
	}
%>
	</table>
	<p>
	<a href="poll-list.jsp">Return to the list of polls</a>
</body>
</html>