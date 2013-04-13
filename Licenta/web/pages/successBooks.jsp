<%@include file="header.jsp"%>
<div id="bookBody">
	<div class="contentBook">
		<p>Books</p>
		<div class="tabel">
			<s:if test="%{listBook.size() > 0}">
				<table class="bookBodyTable">
					<thead>
						<tr>
							<td class="headrow"><s:checkbox id="checkeAll"
									onclick="selectAll();" name="checkeAll" /></td>
							<th class="headrow">Title <s:if test="%{column != 'title'}">
									<a
										href="fetchBooks?sort=ASC&page=<s:property value='page'/>&column=title"><img
										alt="non" src="stile/pictures/sort_both.jpg"></a>
								</s:if> <s:else>
									<s:if test="%{sort == 'NON'}">
										<a
											href="fetchBooks?sort=ASC&page=<s:property value='page'/>&column=title"><img
											alt="non" src="stile/pictures/sort_both.jpg"></a>
									</s:if>
									<s:if test="%{sort == 'ASC'}">
										<a
											href="fetchBooks?sort=DESC&page=<s:property value='page'/>&column=title"><img
											alt="non" src="stile/pictures/sort_asc.jpg"></a>
									</s:if>
									<s:if test="%{sort == 'DESC'}">
										<a
											href="fetchBooks?sort=NON&page=<s:property value='page'/>&column=title"><img
											alt="non" src="stile/pictures/sort_desc.jpg"></a>
									</s:if>
								</s:else>
							</th>
							<th class="headrow">Author(s)</th>
							<th class="headrow">Publisher</th>
							<th class="headrow">Publisher City</th>
							<th class="headrow">Publisher Country</th>
							<th class="headrow">Year <s:if test="%{column != 'year'}">
									<a
										href="fetchBooks?sort=ASC&page=<s:property value='page'/>&column=year"><img
										alt="non" src="stile/pictures/sort_both.jpg"></a>
								</s:if> <s:else>
									<s:if test="%{sort == 'NON'}">
										<a
											href="fetchBooks?sort=ASC&page=<s:property value='page'/>&column=year"><img
											alt="non" src="stile/pictures/sort_both.jpg"></a>
									</s:if>
									<s:if test="%{sort == 'ASC'}">
										<a
											href="fetchBooks?sort=DESC&page=<s:property value='page'/>&column=year"><img
											alt="non" src="stile/pictures/sort_asc.jpg"></a>
									</s:if>
									<s:if test="%{sort == 'DESC'}">
										<a
											href="fetchBooks?sort=NON&page=<s:property value='page'/>&column=year"><img
											alt="non" src="stile/pictures/sort_desc.jpg"></a>
									</s:if>
								</s:else>
							</th>
							<th class="headrow">Volume</th>
							<th class="headrow">Series</th>
							<th class="headrow">Edition</th>
							<th class="headrow">Month</th>
							<th class="headrow">Note</th>
							<th class="headrow"></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listBook" var="bookAuthor" status="part">
							<s:if test="%{#part.index% 2 == 0}">
								<tr>
									<td class="smallField"><s:checkbox fieldValue="%{idBook}"
											id="checkbox" name="checkbox" /></td>
									<td class="mediumField"><s:property value="title" /></td>
									<td class="bigField"><s:property value="autors" /></td>
									<td class="smallField"><s:property value="publisher" /></td>
									<td class="mediumField"><s:property value="city" /></td>
									<td class="mediumField"><s:property value="country" /></td>
									<td class="smallField"><s:property value="year" /></td>
									<td class="smallField">
										<s:if test="%{volume != 0}"><s:property value="volume" /></s:if>
									</td>
									<td class="smallField"><s:property value="series" /></td>
									<td class="smallField"><s:property value="edition" /></td>
									<td class="smallField"><s:property value="month" /></td>
									<td class="bigField"><s:property value="note" /></td>
									<td class="edit"><a
										href="editBook?id=<s:property value='idBook'/>&&action=edit">
											<img src="stile/pictures/edit.gif" alt="edit" />
									</a><br /> <a
										href="editBook?id=<s:property value='idBook'/>&numberOfPages=<s:property value='numberOfPages'/>&sizeTabel=<s:property value='sizeTabel'/>&action=delete&page=<s:property value='page'/>">
											<img src="stile/pictures/delete_button.gif" alt="delete" />
									</a></td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td class="smallField"><s:checkbox fieldValue="%{idBook}"
											name="checkbox" /></td>
									<td class="mediumField1"><s:property value="title" /></td>
									<td class="bigField1"><s:property value="autors" /></td>
									<td class="smallField1"><s:property value="publisher" /></td>
									<td class="mediumField1"><s:property value="city" /></td>
									<td class="mediumField1"><s:property value="country" /></td>
									<td class="smallField1"><s:property value="year" /></td>
									<td class="smallField1">
										<s:if test="%{volume != 0}"><s:property value="volume" /></s:if>
									</td>
									<td class="smallField1"><s:property value="series" /></td>
									<td class="smallField1"><s:property value="edition" /></td>
									<td class="smallField1"><s:property value="month" /></td>
									<td class="bigField1"><s:property value="note" /></td>
									<td class="edit1"><a
										href="editBook?id=<s:property value='idBook'/>&&action=edit">
											<img src="stile/pictures/edit.gif" alt="edit" />
									</a><br /> <a
										href="editBook?id=<s:property value='idBook'/>&numberOfPages=<s:property value='numberOfPages'/>&sizeTabel=<s:property value='sizeTabel'/>&action=delete&page=<s:property value='page'/>">
											<img src="stile/pictures/delete_button.gif" alt="delete" />
									</a></td>
								</tr>
							</s:else>
						</s:iterator>
					</tbody>
				</table>
				<%@include file="pagination.jsp" %>
			</s:if>
		</div>
		<div class="tabel">
			<s:else>
				<p>No Books Found</p>
			</s:else>
		</div>
		<div class="buttons">
			<a href="fetchPublisher">Insert</a>  <a
				onclick="href='editBook?action=deleteAll&page='+<s:property value='page'/>+'&checkedID='+checkbox_test();">Delete</a>
				<a href="index.jsp">Back</a>
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>
