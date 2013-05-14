<%@include file="header.jsp"%>
<div class="view">
	<s:if test="sessionLogin.get('login') == true">
		<span class="text">Books</span>
		<div>
			<s:if test="%{listBook.size() > 0}">
				<div class="books">
					<table>
						<thead>
							<tr>
								<th><s:checkbox id="checkeAll"
									onclick="selectAll();" name="checkeAll" /></th>
								<th>Title
									 <s:if test="%{column != 'title'}">
										<a
											href="fetchBooks?sort=ASC&page=<s:property value='page'/>&column=title">
											<img alt="non" src="stile/pictures/sort_both.jpg" />
										</a>
									</s:if> 
									<s:else>
										<s:if test="%{sort == 'NON'}">
											<a
											href="fetchBooks?sort=ASC&page=<s:property value='page'/>&column=title">
												<img alt="non" src="stile/pictures/sort_both.jpg">
											</a>
										</s:if>
										<s:if test="%{sort == 'ASC'}">
											<a
											href="fetchBooks?sort=DESC&page=<s:property value='page'/>&column=title">
												<img alt="non" src="stile/pictures/sort_asc.jpg">
											</a>
										</s:if>
										<s:if test="%{sort == 'DESC'}">
											<a
												href="fetchBooks?sort=NON&page=<s:property value='page'/>&column=title">
												<img alt="non" src="stile/pictures/sort_desc.jpg">
											</a>
										</s:if>
									</s:else>
								</th>
								<th>book(s)</th>
								<th>Publisher</th>
								<th>Publisher City</th>
								<th>Publisher Country</th>
								<th>Year 
									<s:if test="%{column != 'year'}">
										<a
											href="fetchBooks?sort=ASC&page=<s:property value='page'/>&column=year">
											<img alt="non" src="stile/pictures/sort_both.jpg">
										</a>
									</s:if>
									<s:else>
										<s:if test="%{sort == 'NON'}">
											<a
												href="fetchBooks?sort=ASC&page=<s:property value='page'/>&column=year">
												<img alt="non" src="stile/pictures/sort_both.jpg">
											</a>
										</s:if>
										<s:if test="%{sort == 'ASC'}">
											<a
												href="fetchBooks?sort=DESC&page=<s:property value='page'/>&column=year">
												<img alt="non" src="stile/pictures/sort_asc.jpg">
											</a>
										</s:if>
										<s:if test="%{sort == 'DESC'}">
											<a
												href="fetchBooks?sort=NON&page=<s:property value='page'/>&column=year">
												<img alt="non" src="stile/pictures/sort_desc.jpg">
											</a>
										</s:if>
									</s:else>
								</th>
								<th>Volume</th>
								<th>Series</th>
								<th>Edition</th>
								<th>Month</th>
								<th>Note</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="listBook" var="bookAuthor" status="part">
								<s:if test="%{#part.index% 2 == 0}">
									<tr class="par">
										<td><s:checkbox fieldValue="%{idBook}" id="checkbox"
											name="checkbox" /></td>
										<td><s:property value="title" /></td>
										<td><s:property value="autors" /></td>
										<td><s:property value="publisher" /></td>
										<td><s:property value="city" /></td>
										<td><s:property value="country" /></td>
										<td><s:property value="year" /></td>
										<td><s:if test="%{volume != 0}">
												<s:property value="volume" />
										</s:if></td>
										<td><s:property value="series" /></td>
										<td><s:property value="edition" /></td>
										<td><s:property value="month" /></td>
										<td><s:property value="note" /></td>
										<td><a
										href="editBook?id=<s:property value='idBook'/>&&action=edit">
											<img src="stile/pictures/paint_brush_plus.png" alt="edit"  />
										</a> <a
											href="editBook?id=<s:property value='idBook'/>&numberOfPages=<s:property value='numberOfPages'/>&sizeTabel=<s:property value='sizeTabel'/>&action=delete&page=<s:property value='page'/>" onclick="return confirm('Are you sure you want to delete this author?');">
												<img src="stile/pictures/trashcan_delete2_17.png"
												alt="delete" />
										</a></td>
									</tr>
								</s:if>
								<s:else>
									<tr class="impar">
										<td><s:checkbox fieldValue="%{idBook}" name="checkbox" /></td>
										<td><s:property value="title" /></td>
										<td><s:property value="autors" /></td>
										<td><s:property value="publisher" /></td>
										<td><s:property value="city" /></td>
										<td><s:property value="country" /></td>
										<td><s:property value="year" /></td>
										<td><s:if test="%{volume != 0}">
												<s:property value="volume" />
											</s:if></td>
										<td><s:property value="series" /></td>
										<td><s:property value="edition" /></td>
										<td><s:property value="month" /></td>
										<td><s:property value="note" /></td>
										<td><a
											href="editBook?id=<s:property value='idBook'/>&&action=edit">
												<img src="stile/pictures/paint_brush_plus.png" alt="edit" />
										</a> <a
											href="editBook?id=<s:property value='idBook'/>&numberOfPages=<s:property value='numberOfPages'/>&sizeTabel=<s:property value='sizeTabel'/>&action=delete&page=<s:property value='page'/>" onclick="return confirm('Are you sure you want to delete this author?');">
												<img src="stile/pictures/trashcan_delete2_17.png"
												alt="delete" />
										</a></td>
									</tr>
								</s:else>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<%@include file="paginationBook.jsp"%>
			</s:if>
			<div class="notFound">
				<s:else>
					<p>No Books Found</p>
				</s:else>
			</div>
		</div>
		<div class="buttonsBook">
			<a href="fetchPublisher"><img
				src="stile/pictures/edit_delete3.png" /><span class="butonText">Insert</span></a>
			<a
				onclick="href='editBook?action=deleteAll&page='+<s:property value='page'/>+'&numberOfPages='+<s:property value='numberOfPages'/>+'&sizeTabel='+<s:property value='sizeTabel'/>+'&checkedID='+checkbox_test();"><img
				src="stile/pictures/trashcan_delete2_17.png" /><span
				class="butonText">Delete</span></a> <a href="firstPageAdmin"><img
				src="stile/pictures/back.png"><span class="butonText">Back</span></a>
		</div>
	</s:if>
	<s:else>Please login!!!</s:else>
</div>
<%@include file="footer.jsp"%>
