<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/headerMenu.css"/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/user.css"/>">
<script type="text/javascript" src="functionJS/search.js"></script>
</head>
<body>
	<div id="header">
		<div id="colorBoxes">
			<ul>
				<li
					style="background: none repeat scroll 0px 0px rgb(73, 175, 208);">books</li>
				<li style="background: #90a5a8;">article</li>
				<li style="background: #8ecd51;">for free</li>
			</ul>
		</div>
		<ul id="topAdditionaMenu" class="dropdown dropdown-horizontal">
			<li><span class="dir">Sing in</span>
				<ul>
					<li><a href="goToLogin">Login</a></li>
				</ul></li>
			<li><span class="dir">Books </span>
				<ul>
					<li><a href="gotoRecentlyBooks">Recently Added</a></li>
					<li><a href="gotoAllBook">All book</a></li>
				</ul></li>
			<li><span class="dir">Article </span>
				<ul>
					<li><a href="gotoRecentlyArticle">Recently Added</a></li>
					<li><a href="gotoAllArticle">All article</a></li>
				</ul></li>
			<li><a href="goToAdvSearch">Advance Search</a>
		</ul>
	</div>