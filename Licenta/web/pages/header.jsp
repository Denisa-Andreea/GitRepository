<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/headerBodyFooter.css"/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/books/succsesStileBooks.css"/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stile/books/book.css"/>">
<script type="text/javascript" src="functionJS/fildsAuthor.js"></script>
<script type="text/javascript" src="functionJS/delete.js"></script>
<script type="text/javascript" src="functionJS/selectAll.js"></script>
<script type="text/javascript">
// 	function loadCities(value) {
// 		var selectCity = document.publisher.city;
// 		selectCity.options.lenght = 0;;
// 		if (value == "NON") {
// 			selectCity.options.lenght = 0;
// 		}
// 		var url = "cityToGet";
// 		url += "?code=" + value;
// 		alert(url);
// 		window.location = url;
// 	}

	$(function(){ 
$("#publisher").onclick(function(){
 
var formInput=$(this).serialize();
 
$.getJSON('cityToGet.action', formInput,function(data) {
 
 
$.each(data.countryList,function(index, value){
console.log("value " + value);
});
 
});
 
return false;
 
});
 
});
</script>
</head>
<body id="body">
	<div id="header">
		<img class="image" alt="book" src="stile/pictures/book.jpg">
	</div>