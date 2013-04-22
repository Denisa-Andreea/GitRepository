<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/pagination.css"/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/stileCss.css"/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/header&Footer.css"/>">
<script type="text/javascript" src="functionJS/fildsAuthor.js"></script>
<script type="text/javascript" src="functionJS/delete.js"></script>
<script type="text/javascript" src="functionJS/selectAll.js"></script>
<script type="text/javascript" src="functionJS/dropdown.js"></script>
</head>
<body>
	<div id="header">
		<form action="login">
			<s:submit value="Logout" method="back"/>
		</form>
	</div>