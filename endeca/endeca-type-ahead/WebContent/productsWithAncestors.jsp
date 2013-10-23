<!-- @author ourownjava.com -->

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.ourownjava.endeca.TypeAheadWithAncestors"%>
<%
	TypeAheadWithAncestors typeAheadWithAncestors = new TypeAheadWithAncestors();
	String query = request.getParameter("q");
	
	List<String> tokens = typeAheadWithAncestors.findRelatedTokens("*"+query.toLowerCase() +"*");

	Iterator<String> iterator = tokens.iterator();
	while(iterator.hasNext()) {
		String token = (String)iterator.next();
		out.println(token);
	}
	
%>