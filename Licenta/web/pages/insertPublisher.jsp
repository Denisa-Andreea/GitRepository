<%@include file="header.jsp" %>
	<div>
		<s:form action="addPublisher">
			<div>
				<s:fielderror><s:param>name</s:param></s:fielderror>
				<s:label>Publisher Name*:</s:label>
				<s:textfield name="name" value="%{name}"/>
			</div>
			<div>
				<s:fielderror><s:param>address</s:param></s:fielderror>
				<s:label>Publisher Address*:</s:label>
				<s:textfield name="address" value="%{address}"/>
			</div>
			<s:if test="!session.isEmpty()"><s:submit value="Insert"/></s:if>
			<s:else>
				<s:submit value="Insert" method="execute2"/>
			</s:else>
		</s:form>
	</div>
<%@include file="footer.jsp"%>