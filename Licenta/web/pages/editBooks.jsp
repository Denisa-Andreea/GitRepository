<%@include file="header.jsp" %>
<div class="editBook">
	<s:form action="updateBook" method="post" name="updateBook">		
		<table class="tabelBookUpdate">
		<tr>
			<td><label>Title*:</label></td>
			<td>
				<s:hidden  name="id" value="%{bookList.get(0).getIdBook()}"/>
				<s:textfield name="title" value="%{bookList.get(0).getTitle()}"/>			
			</td>
			<td><s:fielderror><s:param>title</s:param></s:fielderror></td>
		</tr>
		<tr>
			<td><label>Athor(s)*:</label></td>
			<td>
				<div id="autors">
					<s:if test="bookList.get(0).getAutors() != null">					
						<div>
							<label id="authorFNLabel">First Name:</label><s:textfield id="authorFN" name="authorFN" value="%{bookList.get(0).getAutors().get(0).getFirstName()}"/>
							<label id="authorLNLabel">Last Name:</label><s:textfield id="authorLN" name="authorLN" value="%{bookList.get(0).getAutors().get(0).getLastName()}"/>
						</div>						
						<s:iterator value="bookList.get(0).getAutors()" begin="1" status="part">
							<div>							
								<label id="authorFNLabel">First Name:</label><s:textfield id="%{#part.index+1}" name="authorFN" value="%{getFirstName()}"/>
								<label id="authorLNLabel">Last Name:</label><s:textfield id="authorLN%{#part.index+1}" name="authorLN" value="%{getLastName()}"/>
							</div>
						</s:iterator>
					</s:if>					
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
			<td><label>Publisher*:</label></td>
			<td>
				<select name="publisher">
					<option value="0">-Select a Publisher empty-</option>
					<s:iterator value="listPublisher" var="publishers">
						<s:if test="%{publisherSelected == id_publisher}">
							<option value="<s:property value='id_publisher'/>" selected="selected">
								<s:property value="name"/>-<s:property value="address"/>
							</option>
						</s:if>
						<s:else>
							<option value="<s:property value='id_publisher'/>">
								<s:property value="name"/>(<s:property value="address"/>)
							</option>
						</s:else>
					</s:iterator>
				</select>
			</td>
			<td><s:fielderror><s:param>publisher</s:param></s:fielderror></td>
		</tr>
		<tr>
			<td><label>Year*:</label></td>
			<td>	
				<s:textfield name="year" value="%{bookList.get(0).getYear()}"/>
			</td>
			<td><s:fielderror><s:param>year</s:param></s:fielderror></td>
		</tr>
		<tr>
			<td><label>Volume:</label></td>
			<td>
				<s:textfield name="volume" value="%{bookList.get(0).getVolume()}"/>
			</td>
		</tr>
		<tr>
			<td><label>Series:</label></td>
			<td>
				<s:textfield name="series" value="%{bookList.get(0).getSeries()}"/>
			</td>	
		</tr>
		<tr>
			<td><label>Edition:</label></td>
			<td>
				<s:textfield name="edition" value="%{bookList.get(0).getEdition()}"/>
			</td>
		</tr>
		<tr>
			<td><label>Month:</label></td>
			<td><select name="month">
				<option value="">-Select a month-</option>
				<s:iterator value="monthList" status="part">
					<s:if test="%{bookList.get(0).getMonth() == monthList.get(#part.index)}">
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
				<s:textfield name="note" value="%{bookList.get(0).getNote()}"/>
			</td>
		</tr>
	</table>
		<div>
		<s:submit value="Update"/>
		<s:submit value="Cancel Update" method="cancel"/>
		</div>		
	</s:form>
</div>
<%@include file="footer.jsp" %>