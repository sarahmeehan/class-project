<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
 <%@ page import="teamc.model.*" %>
<c:import url="includes/header.html" />

		<%-- Display any error messages (will be empty first time the page loads). --%>
		<c:if test="${not empty messages}">
		    <p><i class=error>
			<%  
				// iterate over error messages and print each
				String[] messages = (String[])session.getAttribute("messages");
				for (String message : messages) {
			%>
			<%= message %><br />
			<%
				}
			%>	    
		    </i></p>
		</c:if>
		
		            
		<% 
		    String loggedIn = (String)session.getAttribute("loggedIn");
			
			// If the user is logged in, we're not going to allowing editing the user name.
			if (loggedIn == null) {
				loggedIn = "false";
			} 
			
			User user = (User)session.getAttribute("user");		
		%>		

        <h2>Account Information</h2>
        <form action="music" method="post">
		<% if (Boolean.valueOf(loggedIn)) {%>
			<label style="float: left; margin-bottom: 0.5em" class="pad_top">User Name: ${user.username}</label><br>
		<% } else { %>
		    <label style="float: left; margin-bottom: 0.5em" class="pad_top">User Name: </label>		
	    	<input type="text" name="username" value="${user.username}"><br>			
		<% } %>
		    <label style="float: left; margin-bottom: 0.5em" class="pad_top">Password: </label>
		    <input type="password" name="password" value="${user.password}"><br>
            <button type="submit" name="action" value="save_account" class="margin_left">Save</button>
            <button type="submit" name="action" value="cancel_create_account" class="margin_left">Cancel</button>  		    
        </form>

</body>
</html>