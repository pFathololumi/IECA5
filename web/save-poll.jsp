<%@page import="ir.ramtung.polling.*"%>
<%@page import="java.util.*"%>

<% 
	PollRepository repo = PollRepository.getRepository();
	repo.store(new Poll(request.getParameter("subject"), request.getParameterValues("choices[]")));
%>

<jsp:forward page="poll-list.jsp"/>
