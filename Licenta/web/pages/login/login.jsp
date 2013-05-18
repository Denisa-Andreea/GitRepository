<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/styleLogin.css"/>">
</head>

<body>
	<s:form action="login" method="post">
		<div id="wrapper">
			<div id="wrappertop"></div>
			<div id="wrappermiddle">
				<h2>Login</h2>
				<div id="username_input">
					<div id="username_inputleft"></div>
					<div id="username_inputmiddle">
						<s:textfield id="url" name="user" placeholder='"User Name"'/>
						<img id="url_user" alt=""
							src="./stile/pictures/login/mailicon.png">
					</div>
					<div id="username_inputright"></div>
					<s:fielderror>
						<s:param>user</s:param>
					</s:fielderror>
				</div>
				<div id="password_input">
					<div id="password_inputleft"></div>
					<div id="password_inputmiddle">
						<s:password name="password" id="url" placeholder='"Password"'/>
						<img id="url_password" alt=""
							src="./stile/pictures/login/passicon.png">
					</div>
					<div id="password_inputright"></div>
				</div>
				<div id="submit">
					<s:submit value="Login" id="submit1" type="image"
						src="./stile/pictures/login/submit_hover.png" style="opacity: 1;" />
				</div>
				<s:submit value="Cancel" method="cancel" type="image"
					src="./stile/pictures/login/submit_hover.png"
					style="opacity: 1; margin-left: 25px;" />

				<div id="links_left">
					<a href="#">Forgot your Password?Click here to reset it</a>
				</div>
			</div>
			<div id="wrapperbottom"></div>
		</div>
	</s:form>
</body>
</html>