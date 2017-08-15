<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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

        <h2>Login Information</h2>
        <form id="login" action="music" method="post">
		    <label style="float: left; margin-bottom: 0.5em" class="pad_top">User Name: </label>
		    <input type="text" name="username"><br>
		    <label style="float: left; margin-bottom: 0.5em" class="pad_top">Password: </label>
		    <input type="password" name="password">
		    <br>
            <label>&nbsp;</label>
            <button type="submit" name="action" value="login" class="margin_left">Login</button>
            <button type="submit" name="action" value="register" class="margin_left">Register</button>   
        </form>                     

<c:import url="includes/footer.html" />