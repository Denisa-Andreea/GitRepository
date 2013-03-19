<%@include file="header.jsp" %>
	<div class="bookBody">
		<p>Tabel of books</p>
		<table class="tabel">
			<tr class="headrow"><th>Name</th><th>Address</th></tr>
			<s:iterator value="listPublisher" var="publisher">
				<tr><td><s:property value="name"/></td>
				<td><s:property value="address"/></td>
				</tr>
			</s:iterator>
		</table>
		<div class="buttons">
			<a href="addPublisherDirectly">Insert</a>
			<a href="index.jsp">Back</a>
		</div>
	</div>
<%@include file="footer.jsp" %>