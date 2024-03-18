<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Custom Login Page</title>
</head>
<body>
	<h1>Giriş Sayfası</h1>
	
	
	<form action="login" method="post">
		Username:<input type="text" name="username"/><br/>
		Password:<input type="password" name="password"/><br/>
		Remember Me:<input type="checkbox" name="remember-me"/><br/>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/><br/>
		
		<input type="submit" value="Go"/>
		
		<font color="red">
			<c:if test="${not empty param.loginFailed}">
			
				<c:out value="Login Failed, Username or Password not correct"></c:out>
			</c:if>
		</font>
	</form>
</body>
</html>