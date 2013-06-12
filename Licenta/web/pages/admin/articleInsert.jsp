<%@include file="header.jsp"%>
<div id="content">
	<s:if test="sessionArticle.get('login') == true">
		<div id="formularSpan">
			<span>Insert Article</span>
		</div>
		<center>
			<div id="formular">
				<s:form action="articleInsert" method="post" name="articleInsert">
					<table id="formularContent">
						<tr>
							<td><label>Title*:</label></td>
							<td><s:if test="sessionArticle.get('article') != true">
									<s:textfield id="title" name="title" value="%{title}" />
								</s:if> <s:else>
									<s:textfield id="title" name="title"
										value="%{sessionArticle.title}" />
								</s:else></td>
							<td><s:fielderror>
									<s:param>title</s:param>
								</s:fielderror></td>
						</tr>
						<tr>
							<td><label>Athor(s)*:</label></td>
							<td>
								<div id="authors">
									<s:if test="sessionArticle.get('article')">
										<!-- 				<p>session not empty</p>				 -->
										<div>
											<label id="authorFNLabel">First Name:</label>
											<s:textfield id="authorFN" name="authorFN"
												value="%{sessionArticle.get('authorList').get(0).getFirstName()}" />
											<label id="authorLNLabel">Last Name:</label>
											<s:textfield id="authorLN" name="authorLN"
												value="%{sessionArticle.get('authorList').get(0).getLastName()}" />
										</div>
										<s:iterator value="sessionArticle.get('authorList')" begin="1"
											status="part">
											<div>
												<label id="authorFNLabel">First Name:</label>
												<s:textfield id="%{#part.index+1}" name="authorFN"
													value="%{getFirstName()}" />
												<label id="authorLNLabel">Last Name:</label>
												<s:textfield id="authorLN%{#part.index+1}" name="authorLN"
													value="%{getLastName()}" />
											</div>
										</s:iterator>
									</s:if>
									<s:else>
										<!--						<p>session empty</p> -->
										<s:if test="authorList != null">
											<div>
												<label id="authorFNLabel">First Name:</label>
												<s:textfield id="authorFN" name="authorFN"
													value="%{authorList.get(0).getFirstName()}" />
												<label id="authorLNLabel">Last Name:</label>
												<s:textfield id="authorLN" name="authorLN"
													value="%{authorList.get(0).getLastName()}" />
											</div>
											<s:iterator value="authorList" begin="1" status="part">
												<div>
													<label id="authorFNLabel">First Name:</label>
													<s:textfield id="%{#part.index+1}" name="authorFN"
														value="%{getFirstName()}" />
													<label id="authorLNLabel">Last Name:</label>
													<s:textfield id="authorLN%{#part.index+1}" name="authorLN"
														value="%{getLastName()}" />
												</div>
											</s:iterator>
										</s:if>
										<s:else>
											<label id="authorFNLabel">First Name:</label>
											<s:textfield id="authorFN" name="authorFN" value="" />
											<label id="authorLNLabel">Last Name:</label>
											<s:textfield id="authorLN" name="authorLN" value="" />
										</s:else>
									</s:else>
								</div>
							</td>
							<td><s:fielderror>
									<s:param>authors</s:param>
								</s:fielderror></td>
						</tr>
						<tr>
							<td></td>
							<td><s:if test="sessionArticle.get('article')">
									<input type="button"
										onclick="addInput('authors','<s:property value='sessionArticle.get("size")'/>')"
										id="buttonAdd" name="add" value="Add author" />
								</s:if> <s:else>
									<input type="button"
										onclick="addInput('authors','<s:property value='size'/>')"
										id="buttonAdd" name="add" value="Add author" />
								</s:else></td>
						</tr>
						<tr>
							<td><label>Journal*:</label></td>
							<td><select name="journal">
									<option value="0" selected="selected">-Select a
										Journal-</option>
									<s:iterator value="listJournal" var="journal">
										<s:if test="%{journalSelected == id_journal}">
											<option value="<s:property value='id_journal'/>"
												selected="selected">
												<s:property value="name" />
											</option>
										</s:if>
										<s:else>
											<option value="<s:property value='id_journal'/>">
												<s:property value="name" />
											</option>
										</s:else>
									</s:iterator>
							</select></td>
							<td><s:fielderror>
									<s:param>journal</s:param>
								</s:fielderror></td>
						</tr>
						<tr>
							<td></td>
							<td><s:submit value="Add Journal" method="browse" /></td>
						</tr>
						<tr>
							<td><label>Year*:</label></td>
							<td><s:if test="sessionArticle.get('article')">
									<s:textfield id="year" name="year"
										value="%{sessionArticle.get('year')}" />
								</s:if> <s:else>
									<s:textfield id="year" name="year" value="%{year}" />
								</s:else></td>
							<td><s:fielderror>
									<s:param>year</s:param>
								</s:fielderror></td>
						</tr>
						<tr>
							<td><label>Volume:</label></td>
							<td><s:if test="sessionArticle.get('article')">
									<s:textfield id="volume" name="volume"
										value="%{sessionArticle.get('volume')}" />
								</s:if> <s:else>
									<s:textfield id="volume" name="volume" value="%{volume}" />
								</s:else></td>
							<td><s:fielderror>
									<s:param>volume</s:param>
								</s:fielderror></td>
						</tr>
						<tr>
							<td><label>Number:</label></td>
							<td><s:if test="sessionArticle.get('article')">
									<s:textfield id="volume" name="number"
										value="%{sessionArticle.get('number')}" />
								</s:if> <s:else>
									<s:textfield id="volume" name="number" value="%{number}" />
								</s:else></td>
							<td><s:fielderror>
									<s:param>number</s:param>
								</s:fielderror></td>
						</tr>
						<tr>
							<td><label>Month:</label></td>
							<td><select name="month">
									<option value="">-Select a month-</option>
									<s:iterator value="monthList" status="part">
										<s:if test="sessionArticle.get('article')">
											<s:if
												test="%{sessionArticle.get('month') == monthList.get(#part.index)}">
												<option selected="selected"
													value="<s:property value='monthList.get(#part.index)'/>">
													<s:property value="monthList.get(#part.index)" />
												</option>
											</s:if>
											<s:else>
												<option
													value="<s:property value='monthList.get(#part.index)'/>">
													<s:property value="monthList.get(#part.index)" />
												</option>
											</s:else>
										</s:if>
										<s:else>
											<s:if test="%{month == monthList.get(#part.index)}">
												<option selected="selected"
													value="<s:property value='monthList.get(#part.index)'/>">
													<s:property value="monthList.get(#part.index)" />
												</option>
											</s:if>
											<s:else>
												<option
													value="<s:property value='monthList.get(#part.index)'/>">
													<s:property value="monthList.get(#part.index)" />
												</option>
											</s:else>
										</s:else>
									</s:iterator>
							</select></td>
						</tr>
						<tr>
							<td><label>Note:</label></td>
							<td><s:if test="sessionArticle.get('article')">
									<s:textarea id="note" name="note"
										value="%{sessionArticle.get('note')}" />
								</s:if> <s:else>
									<s:textarea id="note" name="note" value="%{note}" />
								</s:else></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div id="buttonForm">
									<s:submit value="Insert" />
									<s:submit value="Cancel" method="cancel" />
								</div>

							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</center>

	</s:if>
	<s:else>Please login!!!</s:else>
</div>
<%@include file="footer.jsp"%>