<%@include file="header.jsp"%>
<div class="view">
	<s:if test="sessionLogin.get('login') == true">
		<span class="text">Tabel of books</span>
		<div>
			<s:if test="%{listPublisher.size()>0}">
				<div class="publisher">
					<table>
						<thead>
							<tr>
								<th>Name <s:if test="%{column != 'name'}">
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
								</th>
								<th>Country <s:if test="%{column != 'country'}">
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
								</th>
								<th>City <s:if test="%{column != 'city'}">
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
								</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="listPublisher" var="publisher" status="part">
								<s:if test="%{#part.index% 2 == 0}">
									<tr class="par">
										<td width="1000px"><s:property value="name" /></td>
										<td width="1500px"><s:property value="country" /></td>
										<td width="500px"><s:property value="city" /></td>
										<td><a
											href="editPublisher?id=<s:property value='id_publisher'/>">
												<img src="stile/pictures/paint_brush_plus.png" alt="edit" />
										</a></td>
									</tr>
								</s:if>
								<s:else>
									<tr class="impar">
										<td><s:property value="name" /></td>
										<td><s:property value="country" /></td>
										<td><s:property value="city" /></td>
										<td><a
											href="editPublisher?id=<s:property value='id_publisher'/>">
												<img src="stile/pictures/paint_brush_plus.png" alt="edit" />
										</a></td>
									</tr>
								</s:else>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</s:if>
			<%@include file="paginationPublisher.jsp"%>
			<div class="notFound">
				<s:else>
					<p>No Publishers Found</p>
				</s:else>
			</div>
			<div class="buttonsPublisher">
				<a href="PublisherFields"><img src="stile/pictures/edit_delete3.png"/><span class="butonText">Insert</span></a>
				<a href="firstPageAdmin"><img src="stile/pictures/back.png"><span class="butonText">Back</span></a>
			</div>
		</div>
	</s:if>
</div>
<%@include file="footer.jsp"%>