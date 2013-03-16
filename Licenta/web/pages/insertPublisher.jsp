<%@include file="header.jsp" %>
<div>
	<s:form action="addPublisher">
		<div>
			<s:label>Publisher Name*:</s:label><s:textfield name="name"/>
		</div>
		<div>
			<s:label>Publisher Address:</s:label>
			<s:textarea name="address"/>
		</div>
		<s:if test="session.isEmpty()">empty</s:if>
		<s:else>
			<s:submit value="Insert"/>
		</s:else>
	</s:form>
</div>
<%@include file="footer.jsp"%>