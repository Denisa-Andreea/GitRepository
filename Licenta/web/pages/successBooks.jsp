<%@include file="header.jsp"%>
<div id="bookBody">
	<div class="content">
		<p>Books</p>
		<div class="tabel">
			<s:if test="%{listBook.size() > 0}">
				<table>
					<thead>
						<tr>
							<td class="headrow"><s:checkbox id="checkeAll"
									onclick="selectAll();" name="checkeAll" /></td>
							<th class="headrow"><a onclick="href='fetchBooks?column=title&page=<s:property value="page"/>&sort='+change('title','<s:property value="column"/>','<s:property value="sort"/>');">Title</a></th>
							<th class="headrow">Author(s)</th>
							<th class="headrow">Publisher</th>
							<th class="headrow">Address</th>
							<th class="headrow"><a onclick="href='fetchBooks?column=year&page=<s:property value="page"/>&sort='+change('year','<s:property value="column"/>','<s:property value="sort"/>');">Year</a></th>
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
									<td class="mediumField"><s:property value="address" /></td>
									<td class="smallField"><s:property value="year" /></td>
									<td class="smallField"><s:property value="volume" /></td>
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
									<td class="mediumField1"><s:property value="address" /></td>
									<td class="smallField1"><s:property value="year" /></td>
									<td class="smallField1"><s:property value="volume" /></td>
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
				<div id="pagination">
					<ul>
						<s:if test="%{page != 1}">
							<li class="notactive"><a href="fetchBooks?page=1&sort=<s:property value='sort'/>&column=<s:property value="column"/>">First</a></li>
							<li class="notactive"><a
								href="fetchBooks?page=<s:property value='page - 1'/>&sort=<s:property value='sort'/>&column=<s:property value="column"/>">Previous</a></li>
						</s:if>
						<s:else>
							<li class="inactive">First</li>
							<li class="inactive">Previous</li>
						</s:else>
						<c:forEach begin="1" end="${numberOfPages}" var="i">
							<c:choose>
								<c:when test="${page eq i}">
									<li class="active"><a href="fetchBooks?page=${i}&sort=<s:property value='sort'/>&column=<s:property value="column"/>">${i}</a></li>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${(page eq 1) or (page eq numberOfPages)}">
											<c:choose>
												<c:when test="${(i-page < 5) and (i-page > -5)}">
													<li class="notactive"><a href="fetchBooks?page=${i}&sort=<s:property value='sort'/>&column=<s:property value="column"/>">${i}</a></li>
												</c:when>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${(i-page < 3) and (i-page > -3)}">
													<li class="notactive"><a href="fetchBooks?page=${i}&sort=<s:property value='sort'/>&column=<s:property value="column"/>">${i}</a></li>
												</c:when>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<s:if test="%{page < numberOfPages}">
							<li class="notactive"><a
								href="fetchBooks?page=<s:property value='page + 1'/>&sort=<s:property value='sort'/>&column=<s:property value="column"/>">Next</a></li>
							<li class="notactive"><a
								href="fetchBooks?page=<s:property value='numberOfPages'/>&sort=<s:property value='sort'/>&column=<s:property value="column"/>">Last</a></li>
						</s:if>
						<s:else>
							<li class="inactive">Next</li>
							<li class="inactive">Last</li>
						</s:else>
					</ul>
				</div>
			</s:if>
		</div>
		<div class="tabel">
			<s:else>
				<p>No Books Found</p>
			</s:else>
		</div>
		<div class="buttons">
			<a href="fetchPublisher">Insert</a> <a href="index.jsp">Back</a> <a
				onclick="href='editBook?action=deleteAll&page='+<s:property value='page'/>+'&checkedID='+checkbox_test();">Delete</a>
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>
