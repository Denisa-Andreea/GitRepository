<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<div class="content">
	<s:if test="sessionLogin.get('login') == true">
		<a href="fetchBooks">Books</a><br/>
		<a href="fetchArticle">Article</a><br/>
		<a href="viewPublisher">Publishers</a><br/>
		<a href="viewJournal">Journal</a>
		<s:form action="login" method="post">
			<s:submit value="Change Password" method="browse"/>
		</s:form>
	</s:if>
	<s:else>
		Please login!!!
	</s:else>
</div>
