<%@include file="header.jsp" %>
	<div class="bookBody">
		<p>Tabel of books</p>
		<table class="tabel">
			<tr class="headrow"><th>Name</th><th>Address</th></tr>
			<s:iterator value="listPublisher" var="publisher">
				<tr><td>
						<table>
							<tr class="headrow"><th><br/></th></tr>
							<tr>
								<td><s:property value="name"/></td>
							</tr>
						</table>
					</td>
					<td>
						<table><tr class="headrow"><th>Country</th><th>City</th></tr>
							<tr><td><s:property value="country"/></td>
								<td></td>
							</tr>
						</table>
					</td>
				</tr>
			</s:iterator>
		</table>
		<div class="buttons">
			<a href="PublisherFields">Insert</a>
			<a href="index.jsp">Back</a>
		</div>
	</div>
<%@include file="footer.jsp" %>