<%@include file="header.jsp"%>
<div class="view">
	<s:if test="sessionLogin.get('login') == true">
		<span class="text">Article</span>
		<div>
			<s:if test="%{listOfArticles.size() > 0}">
				<div class="books">
					<table>
						<thead>
							<tr>
								<th><s:checkbox id="checkeAll"
									onclick="selectAll();" name="checkeAll" /></th>
								<th>Title
									 <s:if test="%{column != 'title'}">
										<a
											href="fetchArticle?sort=ASC&page=<s:property value='page'/>&column=title">
											<img alt="non" src="stile/pictures/sort_both.jpg" />
										</a>
									</s:if> 
									<s:else>
										<s:if test="%{sort == 'NON'}">
											<a
											href="fetchArticle?sort=ASC&page=<s:property value='page'/>&column=title">
												<img alt="non" src="stile/pictures/sort_both.jpg">
											</a>
										</s:if>
										<s:if test="%{sort == 'ASC'}">
											<a
											href="fetchArticle?sort=DESC&page=<s:property value='page'/>&column=title">
												<img alt="non" src="stile/pictures/sort_asc.jpg">
											</a>
										</s:if>
										<s:if test="%{sort == 'DESC'}">
											<a
												href="fetchArticle?sort=NON&page=<s:property value='page'/>&column=title">
												<img alt="non" src="stile/pictures/sort_desc.jpg">
											</a>
										</s:if>
									</s:else>
								</th>
								<th>Author(s)</th>
								<th>Journal</th>
								<th>Year 
									<s:if test="%{column != 'year'}">
										<a
											href="fetchArticle?sort=ASC&page=<s:property value='page'/>&column=year">
											<img alt="non" src="stile/pictures/sort_both.jpg">
										</a>
									</s:if>
									<s:else>
										<s:if test="%{sort == 'NON'}">
											<a
												href="fetchArticle?sort=ASC&page=<s:property value='page'/>&column=year">
												<img alt="non" src="stile/pictures/sort_both.jpg">
											</a>
										</s:if>
										<s:if test="%{sort == 'ASC'}">
											<a
												href="fetchArticle?sort=DESC&page=<s:property value='page'/>&column=year">
												<img alt="non" src="stile/pictures/sort_asc.jpg">
											</a>
										</s:if>
										<s:if test="%{sort == 'DESC'}">
											<a
												href="fetchArticle?sort=NON&page=<s:property value='page'/>&column=year">
												<img alt="non" src="stile/pictures/sort_desc.jpg">
											</a>
										</s:if>
									</s:else>
								</th>
								<th>Volume</th>
								<th>Number</th>
								<th>Month</th>
								<th>Note</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="listOfArticles" var="articleAuthor" status="part">
								<s:if test="%{#part.index% 2 == 0}">
									<tr class="par">
										<td><s:checkbox fieldValue="%{idArticle}" id="checkbox"
											name="checkbox" /></td>
										<td><s:property value="title" /></td>
										<td><s:property value="autors" /></td>
										<td><s:property value="journal" /></td>
										<td><s:property value="year" /></td>
										<td><s:if test="%{volume != 0}">
												<s:property value="volume" />
										</s:if></td>
										<td><s:if test="%{number != 0}">
												<s:property value="number" />
										</s:if></td>
										<td><s:property value="month" /></td>
										<td><s:property value="note" /></td>
										<td><a
										href="editArticle?id=<s:property value='idArticle'/>&&action=edit">
											<img src="stile/pictures/paint_brush_plus.png" alt="edit"  />
										</a> <a
											href="editArticle?id=<s:property value='idArticle'/>&numberOfPages=<s:property value='numberOfPages'/>&sizeTabel=<s:property value='sizeTabel'/>&action=delete&page=<s:property value='page'/>" onclick="return confirm('Are you sure you want to delete this author?');">
												<img src="stile/pictures/trashcan_delete2_17.png"
												alt="delete" />
										</a></td>
									</tr>
								</s:if>
								<s:else>
									<tr class="impar">
										<td><s:checkbox fieldValue="%{idArticle}" name="checkbox" /></td>
										<td><s:property value="title" /></td>
										<td><s:property value="autors" /></td>
										<td><s:property value="journal" /></td>
										<td><s:property value="year" /></td>
										<td><s:if test="%{volume != 0}">
												<s:property value="volume" />
											</s:if></td>
										<td><s:if test="%{number != 0}">
												<s:property value="number" />
											</s:if></td>
										<td><s:property value="month" /></td>
										<td><s:property value="note" /></td>
										<td><a
											href="editArticle?id=<s:property value='idArticle'/>&&action=edit">
												<img src="stile/pictures/paint_brush_plus.png" alt="edit" />
										</a> <a
											href="editArticle?id=<s:property value='idArticle'/>&numberOfPages=<s:property value='numberOfPages'/>&sizeTabel=<s:property value='sizeTabel'/>&action=delete&page=<s:property value='page'/>" onclick="return confirm('Are you sure you want to delete this author?');">
												<img src="stile/pictures/trashcan_delete2_17.png"
												alt="delete" />
										</a></td>
									</tr>
								</s:else>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<%@include file="paginationArticle.jsp"%>
			</s:if>
			<div class="notFound">
				<s:else>
					<p>No Article Found</p>
				</s:else>
			</div>
		</div>
		<div class="buttonsBook">
			<a href="fetchJournal"><img
				src="stile/pictures/edit_delete3.png" /><span class="butonText">Insert</span></a>
			<a
				onclick="href='editArticle?action=deleteAll&page='+<s:property value='page'/>+'&numberOfPages='+<s:property value='numberOfPages'/>+'&sizeTabel='+<s:property value='sizeTabel'/>+'&checkedID='+checkbox_test();"><img
				src="stile/pictures/trashcan_delete2_17.png" /><span
				class="butonText">Delete</span></a> <a href="firstPageAdmin"><img
				src="stile/pictures/back.png"><span class="butonText">Back</span></a>
		</div>
	</s:if>
	<s:else>Please login!!!</s:else>
</div>
<%@include file="footer.jsp"%>
