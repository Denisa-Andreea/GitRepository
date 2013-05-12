<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/loginStile.css"/>">
</head>
<body>
	<div class = "login">
		<s:form action="login" method="post">
			<div>
				<label>User:</label>
				<span><s:textfield name="user"/></span>
				<span><s:fielderror><s:param>user</s:param></s:fielderror></span>
				<label>Password:</label>
				<span><s:password name="password"/></span>
				<span><s:fielderror><s:param>password</s:param></s:fielderror></span>
			</div>
			<div class="button" >
				<s:submit value="Login"/>
				<s:submit value="Change Password" method="browse"/>
			</div>
		</s:form>
	</div>
</body>
</html>