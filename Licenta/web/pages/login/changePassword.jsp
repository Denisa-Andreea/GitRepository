<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Change Password</title>
</head>
<body>
	<div>
		<s:form action="changePass" method="post">
			<div>
				<label>Old Password:</label>
				<span><s:password name="oldPassword"/></span>
				<span><s:fielderror><s:param>oldPassword</s:param></s:fielderror></span>
				<label>New Password:</label>
				<span><s:password name="newPassword"/></span>
				<span><s:fielderror><s:param>newPassword</s:param></s:fielderror></span>
			</div>
			<div class="button" >
				<s:submit value="Change" onclick="return confirm('Are you sure you want to change the password')"/>
				<s:submit value="Cancel" method="cancel"/>
			</div>
		</s:form>
	</div>
</body>
</html>