<%@include file="header.jsp" %>
	<div class="publisherBody">
		<div class="contentPublisher">
			<p>Tabel of books</p>
			<div class="tablePub">
				<table class="tabelPublisher">
					<tr class="headrow"><th>Name</th><th>Address</th><th></th></tr>
					<s:iterator value="listPublisher" var="publisher">
						<tr><td>
								<table class="tableName">
									<tr class="headrow"><th><br/></th></tr>
									<tr>
										<td><s:property value="name"/></td>
									</tr>
								</table>
							</td>
							<td>
								<table><tr class="headrow"><th>Country</th><th>City</th></tr>
									<tr><td><s:property value="country"/></td>
										<td><s:property value="city"/></td>
									</tr>
								</table>
							</td>
							<td>
								<table><tr class="headrow"><th><br/></th></tr>
									<tr>
										<td>edit</td>
									</tr>
								</table>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div class="buttons">
				<a href="PublisherFields">Insert</a>
				<a href="index.jsp">Back</a>
			</div>
		</div>
	</div>
<%@include file="footer.jsp" %>