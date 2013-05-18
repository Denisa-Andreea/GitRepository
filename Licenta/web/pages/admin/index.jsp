<%@include file="header.jsp" %>
<div class="content">
	<s:if test="sessionLogin.get('login') == true">
		<a href="fetchBooks">Books</a><br/>
		<a href="fetchArticle">Article</a><br/>
		<a href="viewPublisher">Publishers</a><br/>
		<a href="viewJournal">Journal</a><br/>
		<a href="goToAdvSearch">Advance Search</a>
		<s:form action="login" method="post">
			<s:submit value="Change Password" method="browse"/>
		</s:form>
	</s:if>
	<s:else>
		Please login!!!
	</s:else>
</div>
