<%@include file="header.jsp" %>
<div class="contentBook">
	<s:form action="bookInsert" method="post" name="bookInsert">		
		<div class="insertBook"> 
			<table class="tabelBookInsert">
				<tr>
					<td><label>Title*:</label></td>
					<td><s:if test="sessionBook.get('book') != true">
						<s:textfield name="title" value="%{title}"/>			
					</s:if>
					<s:else>
						<s:textfield name="title" value="%{sessionBook.title}"/>
					</s:else>
					</td>
					<td><s:fielderror><s:param>title</s:param></s:fielderror></td>
				</tr>
				<tr>
					<td><label>Athor(s)*:</label></td>
					<td>
						<div id="authors">
							<s:if test="sessionBook.get('book')">
		<!-- 				<p>session not empty</p>				 -->
								<div>
									<label id="authorFNLabel">First Name:</label><s:textfield id="authorFN" name="authorFN" value="%{sessionBook.get('authorList').get(0).getFirstName()}"/>
									<label id="authorLNLabel">Last Name:</label><s:textfield id="authorLN" name="authorLN" value="%{sessionBook.get('authorList').get(0).getLastName()}"/>
								</div>
								<s:iterator value="sessionBook.get('authorList')" begin="1" status="part">
									<div>
										<label id="authorFNLabel">First Name:</label><s:textfield id="%{#part.index+1}" name="authorFN" value="%{getFirstName()}"/>
										<label id="authorLNLabel">Last Name:</label><s:textfield id="authorLN%{#part.index+1}" name="authorLN" value="%{getLastName()}"/>
									</div>
								</s:iterator>
							</s:if>
							<s:else>
		<!--						<p>session empty</p> --> 
								<s:if test="authorList != null">					
									<div>
										<label id="authorFNLabel">First Name:</label><s:textfield id="authorFN" name="authorFN" value="%{authorList.get(0).getFirstName()}"/>
										<label id="authorLNLabel">Last Name:</label><s:textfield id="authorLN" name="authorLN" value="%{authorList.get(0).getLastName()}"/>
									</div>						
									<s:iterator value="authorList" begin="1" status="part">
										<div>
											<label id="authorFNLabel">First Name:</label><s:textfield id="%{#part.index+1}" name="authorFN" value="%{getFirstName()}"/>
											<label id="authorLNLabel">Last Name:</label><s:textfield id="authorLN%{#part.index+1}" name="authorLN" value="%{getLastName()}"/>
										</div>
									</s:iterator>
								</s:if>
								<s:else>
									<label id="authorFNLabel">First Name:</label><s:textfield id="authorFN" name="authorFN" value="Unknown"/>
									<label id="authorLNLabel">Last Name:</label><s:textfield id="authorLN" name="authorLN" value=""/>
								</s:else>
							</s:else>
						</div>
					</td>
					<td><s:fielderror><s:param>authors</s:param></s:fielderror></td>
				</tr>		
				<tr>
					<td></td>
					<td>
						<s:if test="sessionBook.get('book')">
							<input type="button" onclick="addInput('authors','<s:property value='sessionBook.get("size")'/>')" id="buttonAdd" name="add" value="Add author" />
						</s:if>
						<s:else>
							<input type="button" onclick="addInput('authors','<s:property value='size'/>')" id="buttonAdd" name="add" value="Add author" />
						</s:else>
					</td>
				</tr>
				<tr>
					<td><label>Publisher*:</label></td>
					<td><select name="publisher">
						<option value="0" selected="selected">-Select a Publisher-</option>
						<s:iterator value="listPublisher" var="publishers">
							<s:if test="%{publisherSelected == id_publisher}">
								<option value="<s:property value='id_publisher'/>" selected="selected">
									<s:property value="name"/>(<s:property value="city"/>,<s:property value="country"/>)
								</option>
							</s:if>
							<s:else>
								<option value="<s:property value='id_publisher'/>">
									<s:property value="name"/>(<s:property value="city"/>,<s:property value="country"/>)
								</option>
							</s:else>
						</s:iterator>
					</select>
					</td>
					<td><s:fielderror><s:param>publisher</s:param></s:fielderror></td>
				</tr>
				<tr><td></td>
				<td><s:submit value="Add Publisher" method="browse"/></td>
				</tr>
				<tr>
					<td><label>Year*:</label></td>
					<td>	<s:if test="sessionBook.get('book')">
							<s:textfield  name="year" value="%{sessionBook.get('year')}"/>
						</s:if>
						<s:else>
							<s:textfield name="year" value="%{year}"/>
						</s:else>
					</td>
					<td><s:fielderror><s:param>year</s:param></s:fielderror></td>
				</tr>
				<tr>
					<td><label>Volume:</label></td>
					<td><s:if test="sessionBook.get('book')">
						<s:textfield  name="volume" value="%{sessionBook.get('volume')}"/>
					</s:if>
					<s:else>
						<s:textfield name="volume" value="%{volume}"/>
					</s:else>
					</td>
				</tr>
				<tr>
					<td><label>Series:</label></td>
					<td><s:if test="sessionBook.get('book')">
						<s:textfield name="series" value="%{sessionBook.get('series')}"/>
					</s:if>
					<s:else>
						<s:textfield name="series" value="%{series}"/>
					</s:else>
					</td>	
				</tr>
				<tr>
					<td><label>Edition:</label></td>
					<td><s:if test="sessionBook.get('book')">
						<s:textfield name="edition" value="%{sessionBook.get('edition')}"/>
					</s:if>
					<s:else>
						<s:textfield name="edition" value="%{edition}"/>
					</s:else>
					</td>
				</tr>
				<tr>
					<td><label>Month:</label></td>
					<td><select name="month">
						<option value="">-Select a month-</option>
						<s:iterator value="monthList" status="part">
							<s:if test="sessionBook.get('book')">
								<s:if test="%{sessionBook.get('month') == monthList.get(#part.index)}">
									<option selected="selected" value="<s:property value='monthList.get(#part.index)'/>"><s:property value="monthList.get(#part.index)"/></option>
								</s:if>
								<s:else>
									<option value="<s:property value='monthList.get(#part.index)'/>"><s:property value="monthList.get(#part.index)"/></option>
								</s:else>
							</s:if>
							<s:else>
								<s:if test="%{month == monthList.get(#part.index)}">
									<option selected="selected" value="<s:property value='monthList.get(#part.index)'/>"><s:property value="monthList.get(#part.index)"/></option>
								</s:if>
								<s:else>
									<option value="<s:property value='monthList.get(#part.index)'/>"><s:property value="monthList.get(#part.index)"/></option>
								</s:else>
							</s:else>
						</s:iterator>
					</select>
					</td>
				</tr>
				<tr>
					<td><label>Note:</label></td>
					<td><s:if test="sessionBook.get('book')">
						<s:textarea name="note" value="%{sessionBook.get('note')}"/>
					</s:if>
					<s:else>
						<s:textarea name="note" value="%{note}"/>
					</s:else>
					</td>
				</tr>
			</table>
		</div>
		<div class="buttons">
			<s:submit value="Insert"/>
			<s:submit value="Cancel Insert" method="cancel"/>
		</div>
	</s:form>
</div>
<%@include file="footer.jsp" %>