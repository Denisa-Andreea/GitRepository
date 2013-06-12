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
	href="<s:url value="/stile/headerMenu.css"/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/firstPageAdminStyle.css"/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/table.css"/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/formularStyle.css"/>">
<script type="text/javascript" src="functionJS/fildsAuthor.js"></script>
<script type="text/javascript" src="functionJS/delete.js"></script>
<script type="text/javascript" src="functionJS/selectAll.js"></script>
<script type="text/javascript" src="functionJS/dropdown.js"></script>
<script type="text/javascript" src="functionJS/search.js"></script>
</head>
<body>
	<div id="header">
		<div id="colorText">
			<div style="font-size: 26px;">
				<a id="logoText" style="text-decoration: none;"
					href="firstPageAdmin"> <span class="color1">Book</span><span
					class="color2"> &amp; Article</span>
				</a>
			</div>
		</div>
		<ul id="topAdditionaMenu" class="dropdown dropdown-horizontal">
			<li><span class="dir">Books </span>
				<ul>
					<li><a href="fetchBooks">View Books</a></li>
					<li><a href="fetchPublisher">Insert book</a></li>
				</ul></li>
			<li><span class="dir">Publisher</span>
				<ul>
					<li><a href="viewPublisher">View Publisher</a></li>
					<li><a href="PublisherFields">Insert publisher</a></li>
				</ul></li>
			<li><span class="dir">Article</span>
				<ul>
					<li><a href="fetchArticle">View Article</a></li>
					<li><a href="fetchJournal">Insert article</a></li>
				</ul></li>
			<li><span class="dir">Journal</span>
				<ul>
					<li><a href="viewJournal">View Journal</a></li>
					<li><a href="JournalFields">Insert journal</a></li>
				</ul></li>
			<li><a href="logout">Logout</a>
		</ul>
	</div>