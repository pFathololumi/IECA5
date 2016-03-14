<%@page import="ir.ramtung.polling.*"%>
<%@page import="java.util.*"%>

<%
	PollRepository repo = PollRepository.getRepository();
	Poll poll = repo.findByCode(request.getParameter("code"));
	poll.voteFor(Integer.parseInt(request.getParameter("choice")));
%>

<jsp:forward page="vote-result.jsp">
	<jsp:param name="code" value="<%= poll.getCode() %>" />
</jsp:forward>

