<%@page import="ir.ramtung.polling.*, java.util.*"%>

<html>
<head>
	<title>List of Polls</title>
</head>
<body>
	<h3>List of Polls</h3><p>
	<table border=1 cellpadding=5>
<%
	PollRepository repo = PollRepository.getRepository();
	Collection<Poll> polls = repo.findAll();
	for (Poll poll : polls) {
%>
		<tr>
			<td><%= poll.getSubject() %></td>
			<td><a href="vote-form.jsp?code=<%= poll.getCode() %>">Vote Now</a>
			<td><a href="vote-result.jsp?code=<%= poll.getCode() %>">View Results</a>
		</tr>
<%
	}
%>
	</table>
	<p>
	<a href="poll-form.jsp">New Poll</a>
</body>
</html>