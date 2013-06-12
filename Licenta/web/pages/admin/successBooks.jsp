<%@include file="header.jsp"%>
<div class="view">
	<s:if test="sessionLogin.get('login') == true">
		<span class="color1">Books</span>
		<s:if test="%{listBook.size() > 0}">
			<table id="table">
				<thead>
					<tr>
						<th class="check"><s:checkbox id="checkeAll" onclick="selectAll();"
								name="checkeAll" /></th>
						<th>Title <s:if test="%{column != 'title'}">
								<a
									href="fetchBooks?sort=ASC&page=<s:property value='page'/>&column=title">
									<img alt="non" src="stile/pictures/sort_both.jpg" />
								</a>
							</s:if> <s:else>
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
						<th>Author(s)</th>
						<th>Publisher</th>
						<th>Publisher City</th>
						<th>Publisher Country</th>
						<th>Year <s:if test="%{column != 'year'}">
								<a
									href="fetchBooks?sort=ASC&page=<s:property value='page'/>&column=year">
									<img alt="non" src="stile/pictures/sort_both.jpg">
								</a>
							</s:if> <s:else>
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
								<td class="check"><s:checkbox fieldValue="%{idBook}" id="checkbox"
										name="checkbox" /></td>
								<td class="title"><s:property value="title" /></td>
								<td class="author"><s:property value="autors" /></td>
								<td class="publisher"><s:property value="publisher" /></td>
								<td class="publisher"><s:property value="city" /></td>
								<td class="publisher"><s:property value="country" /></td>
								<td class="year"><s:property value="year" /></td>
								<td class="volume"><s:if test="%{volume != 0}">
										<s:property value="volume" />
									</s:if></td>
								<td class="series"><s:property value="series" /></td>
								<td class="edition"><s:property value="edition" /></td>
								<td class="month"><s:property value="month" /></td>
								<td class="note"><s:property value="note" /></td>
								<td class="year"><a
									href="editBook?id=<s:property value='idBook'/>&&action=edit">
										<img src="stile/pictures/paint_brush_plus.png" alt="edit" />
								</a> <a
									href="editBook?id=<s:property value='idBook'/>&numberOfPages=<s:property value='numberOfPages'/>&sizeTabel=<s:property value='sizeTabel'/>&action=delete&page=<s:property value='page'/>"
									onclick="return confirm('Are you sure you want to delete this author?');">
										<img src="stile/pictures/trashcan_delete2_17.png" alt="delete" />
								</a></td>
							</tr>
						</s:if>
						<s:else>
							<tr class="impar">
								<td class="check"><s:checkbox fieldValue="%{idBook}" name="checkbox" /></td>
								<td class="title"><s:property value="title" /></td>
								<td class="author"><s:property value="autors" /></td>
								<td class="publisher"><s:property value="publisher" /></td>
								<td class="publisher"><s:property value="city" /></td>
								<td class="publisher"><s:property value="country" /></td>
								<td class="year"><s:property value="year" /></td>
								<td class="volume"><s:if test="%{volume != 0}">
										<s:property value="volume" />
									</s:if></td>
								<td class="series"><s:property value="series" /></td>
								<td class="edition"><s:property value="edition" /></td>
								<td class="month"><s:property value="month" /></td>
								<td class="note"><s:property value="note" /></td>
								<td class="year"><a
									href="editBook?id=<s:property value='idBook'/>&&action=edit">
										<img src="stile/pictures/paint_brush_plus.png" alt="edit" />
								</a> <a
									href="editBook?id=<s:property value='idBook'/>&numberOfPages=<s:property value='numberOfPages'/>&sizeTabel=<s:property value='sizeTabel'/>&action=delete&page=<s:property value='page'/>"
									onclick="return confirm('Are you sure you want to delete this author?');">
										<img src="stile/pictures/trashcan_delete2_17.png" alt="delete" />
								</a></td>
							</tr>
						</s:else>
					</s:iterator>
				</tbody>
			</table>
			<%@include file="paginationBook.jsp"%>
		</s:if>
		<div class="notFound">
			<s:else>
				<span class="color1">No Books Found</span>
			</s:else>
		</div>
		<div id="button">
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
