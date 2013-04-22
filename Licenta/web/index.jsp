<%@ include file="pages/header.jsp" %>
<div class="content">
	<s:if test="sessionLogin.get('login') == true">
		<a href="fetchBooks">Books</a><br/>
		<a href="viewPublisher">Publishers</a>
	</s:if>
	<s:else>
		Please login!!!
	</s:else>
</div>
<%@ include file="pages/footer.jsp" %>