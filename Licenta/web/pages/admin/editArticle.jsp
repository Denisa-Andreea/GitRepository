<%@include file="header.jsp" %>
<div class="content">
	<div class="formular">
		<s:if test="sessionEdit.get('login') == true">
		<s:form action="updateArticle" method="post" name="updateArticle">
			<div>		
				<table>
					<tr>
						<td><label>Title*:</label></td>
						<td>
							<s:hidden  name="id" value="%{articleList.get(0).getIdArticle()}"/>
							<s:textfield name="title" value="%{articleList.get(0).getTitle()}"/>			
						</td>
						<td><s:fielderror><s:param>title</s:param></s:fielderror></td>
					</tr>
					<tr>
						<td><label>Athor(s)*:</label></td>
						<td>
							<div id="autors">
									<div>
										<label id="authorFNLabel">First Name:</label><s:textfield id="authorFN" name="authorFN" value="%{articleList.get(0).getAutors().get(0).getFirstName()}"/>
										<label id="authorLNLabel">Last Name:</label><s:textfield id="authorLN" name="authorLN" value="%{articleList.get(0).getAutors().get(0).getLastName()}"/>
									</div>						
									<s:iterator value="articleList.get(0).getAutors()" begin="1" status="part">
										<div>							
											<label id="authorFNLabel">First Name:</label><s:textfield id="%{#part.index+1}" name="authorFN" value="%{getFirstName()}"/>
											<label id="authorLNLabel">Last Name:</label><s:textfield id="authorLN%{#part.index+1}" name="authorLN" value="%{getLastName()}"/>
										</div>
									</s:iterator>
							</div>
						</td>
						<td><s:fielderror><s:param>authors</s:param></s:fielderror></td>
					</tr>		
					<tr>
						<td></td>
						<td>
							<input type="button" onclick="addInput('autors','<s:property value='size'/>')" id="buttonAdd" name="add" value="Add author" />
						</td>
					</tr>
					<tr>
						<td><label>Journal*:</label></td>
						<td>
							<select name="journal">
								<option value="0">-Select a Journal-</option>
								<s:iterator value="listJournal" var="journal">
									<s:if test="%{journalSelected == id_journal}">
										<option value="<s:property value='id_journal'/>" selected="selected">
											<s:property value="name"/>
										</option>
									</s:if>
									<s:else>
										<option value="<s:property value='id_journal'/>">
											<s:property value="name"/>
										</option>
									</s:else>
								</s:iterator>
							</select>
						</td>
						<td><s:fielderror><s:param>journal</s:param></s:fielderror></td>
					</tr>
					<tr>
						<td><label>Year*:</label></td>
						<td>	
							<s:textfield name="year" value="%{articleList.get(0).getYear()}"/>
						</td>
						<td><s:fielderror><s:param>year</s:param></s:fielderror></td>
					</tr>
					<tr>
						<td><label>Volume:</label></td>
						<td>
							<s:textfield name="volume" value="%{articleList.get(0).getVolume()}"/>
						</td>
					</tr>
					<tr>
						<td><label>Number:</label></td>
						<td>
							<s:textfield name="number" value="%{articleList.get(0).getNumber()}"/>
						</td>	
					</tr>
					<tr>
						<td><label>Month:</label></td>
						<td><select name="month">
							<option value="">-Select a month-</option>
							<s:iterator value="monthList" status="part">
								<s:if test="%{articleList.get(0).getMonth() == monthList.get(#part.index)}">
									<option selected="selected" value="<s:property value='monthList.get(#part.index)'/>"><s:property value="monthList.get(#part.index)"/></option>
								</s:if>
								<s:else>
									<option value="<s:property value='monthList.get(#part.index)'/>"><s:property value="monthList.get(#part.index)"/></option>
								</s:else>
							</s:iterator>
						</select>
						</td>
					</tr>
					<tr>
						<td><label>Note:</label></td>
						<td>
							<s:textarea name="note" value="%{articleList.get(0).getNote()}"/>
						</td>
					</tr>
				</table>
			</div>
			<div>
				<s:submit value="Update" onclick="return confirm('Are you sure you want to update this article')"/>
				<s:submit value="Cancel Update" method="cancel"/>
			</div>
		</s:form>
		</s:if><s:else>Please login!!!</s:else>
	</div>
</div>
<%@include file="footer.jsp" %>