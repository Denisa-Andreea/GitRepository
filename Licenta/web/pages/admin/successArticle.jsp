<%@include file="header.jsp"%>
<div class="view">
	<s:if test="sessionLogin.get('login') == true">
		<span class="color1">Article</span>
			<s:if test="%{listOfArticles.size() > 0}">
					<table id="table">
						<thead>
							<tr>
								<th class="check"><s:checkbox id="checkeAll"
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
										<td class="check"><s:checkbox fieldValue="%{idArticle}" id="checkbox"
											name="checkbox" /></td>
										<td class="title"><s:property value="title" /></td>
										<td class="author"><s:property value="autors" /></td>
										<td class="publisher"><s:property value="journal" /></td>
										<td class="year"><s:property value="year" /></td>
										<td class="volume"><s:if test="%{volume != 0}">
												<s:property value="volume" />
										</s:if></td>
										<td class="volume"><s:if test="%{number != 0}">
												<s:property value="number" />
										</s:if></td>
										<td class="month"><s:property value="month" /></td>
										<td class="note"><s:property value="note" /></td>
										<td class="year"><a
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
										<td class="check"><s:checkbox fieldValue="%{idArticle}" name="checkbox" /></td>
										<td class="title"><s:property value="title" /></td>
										<td class="author"><s:property value="autors" /></td>
										<td class="publisher"><s:property value="journal" /></td>
										<td class="year"><s:property value="year" /></td>
										<td class="volume"><s:if test="%{volume != 0}">
												<s:property value="volume" />
											</s:if></td>
										<td class="volume"><s:if test="%{number != 0}">
												<s:property value="number" />
											</s:if></td>
										<td class="month"><s:property value="month" /></td>
										<td class="note"><s:property value="note" /></td>
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
				<%@include file="paginationArticle.jsp"%>
			</s:if>
			<div class="notFound">
				<s:else>
					<span class="color1">No Article Found</span>
				</s:else>
			</div>
		<div id="button">
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
