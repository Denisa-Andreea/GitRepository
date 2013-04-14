<%@include file="header.jsp" %>
	<div class="publisherBody">
		<div class="contentPublisher">
			<p>Tabel of books</p>
			<div class="tablePub">
				<table class="tabelPublisher">
					<tr class="headrow"><th>Name <s:if test="%{column != 'name'}">
									<a
										href="viewPublisher?sort=ASC&page=<s:property value='page'/>&column=name"><img
										alt="non" src="stile/pictures/sort_both.jpg"></a>
								</s:if> <s:else>
									<s:if test="%{sort == 'NON'}">
										<a
											href="viewPublisher?sort=ASC&page=<s:property value='page'/>&column=name"><img
											alt="non" src="stile/pictures/sort_both.jpg"></a>
									</s:if>
									<s:if test="%{sort == 'ASC'}">
										<a
											href="viewPublisher?sort=DESC&page=<s:property value='page'/>&column=name"><img
											alt="non" src="stile/pictures/sort_asc.jpg"></a>
									</s:if>
									<s:if test="%{sort == 'DESC'}">
										<a
											href="viewPublisher?sort=NON&page=<s:property value='page'/>&column=name"><img
											alt="non" src="stile/pictures/sort_desc.jpg"></a>
									</s:if>
								</s:else>
								</th><th>Address</th><th></th></tr>
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
								<table><tr class="headrow"><th>Country
								<s:if test="%{column != 'country'}">
									<a
										href="viewPublisher?sort=ASC&page=<s:property value='page'/>&column=country"><img
										alt="non" src="stile/pictures/sort_both.jpg"></a>
								</s:if> <s:else>
									<s:if test="%{sort == 'NON'}">
										<a
											href="viewPublisher?sort=ASC&page=<s:property value='page'/>&column=country"><img
											alt="non" src="stile/pictures/sort_both.jpg"></a>
									</s:if>
									<s:if test="%{sort == 'ASC'}">
										<a
											href="viewPublisher?sort=DESC&page=<s:property value='page'/>&column=country"><img
											alt="non" src="stile/pictures/sort_asc.jpg"></a>
									</s:if>
									<s:if test="%{sort == 'DESC'}">
										<a
											href="viewPublisher?sort=NON&page=<s:property value='page'/>&column=country"><img
											alt="non" src="stile/pictures/sort_desc.jpg"></a>
									</s:if>
								</s:else>
								</th><th>City
								<s:if test="%{column != 'city'}">
									<a
										href="viewPublisher?sort=ASC&page=<s:property value='page'/>&column=city"><img
										alt="non" src="stile/pictures/sort_both.jpg"></a>
								</s:if> <s:else>
									<s:if test="%{sort == 'NON'}">
										<a
											href="viewPublisher?sort=ASC&page=<s:property value='page'/>&column=city"><img
											alt="non" src="stile/pictures/sort_both.jpg"></a>
									</s:if>
									<s:if test="%{sort == 'ASC'}">
										<a
											href="viewPublisher?sort=DESC&page=<s:property value='page'/>&column=city"><img
											alt="non" src="stile/pictures/sort_asc.jpg"></a>
									</s:if>
									<s:if test="%{sort == 'DESC'}">
										<a
											href="viewPublisher?sort=NON&page=<s:property value='page'/>&column=city"><img
											alt="non" src="stile/pictures/sort_desc.jpg"></a>
									</s:if>
								</s:else>
								</th></tr>
									<tr><td><s:property value="country"/></td>
										<td><s:property value="city"/></td>
									</tr>
								</table>
							</td>
							<td>
								<table><tr class="headrow"><th><br/></th></tr>
									<tr>
										<td><a
										href="editPublisher?id=<s:property value='id_publisher'/>">
											<img src="stile/pictures/edit.gif" alt="edit" />
									</a></td>
									</tr>
								</table>
							</td>
						</tr>
					</s:iterator>
				</table>
				<%@include file="paginationPublisher.jsp" %>
			</div>
			<div class="buttons">
				<a href="PublisherFields">Insert</a>
				<a href="index.jsp">Back</a>
			</div>
		</div>
	</div>
<%@include file="footer.jsp" %>