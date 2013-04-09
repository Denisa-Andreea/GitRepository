<%@include file="header.jsp" %>
<div class="insertBook"> 
	<s:form action="bookInsert" method="post" name="bookInsert">		
		<div>
			<s:fielderror><s:param>title</s:param></s:fielderror>
			<label>Title*:</label>
			<s:if test="session.isEmpty()">
				<s:textfield name="title" value="%{title}"/>			
			</s:if>
			<s:else>
				<s:textfield name="title" value="%{session.title}"/>
			</s:else>
			
		</div>
		<div>
			<s:fielderror><s:param>authors</s:param></s:fielderror>
			<label>Athor(s)*:</label>
			<input type="button" onclick="addInput('authors');" id="buttonAdd" name="add" value="Add author" />
			<div id="authors">
				<s:if test="!session.isEmpty()">
<!-- 				<p>session not empty</p>				 -->
					<s:iterator value="session.get('authorList')" status="part">
						<div>
							<label id="authorFNLabel">First Name:</label><s:textfield id="authorFN" name="authorFN%{#part.index}" value="%{getFirstName()}"/>
							<label id="authorLNLabel">Last Name:</label><s:textfield id="authorLN" name="authorLN%{#part.index}" value="%{getLastName()}"/>
						</div>
					</s:iterator>
				</s:if>
				<s:else>
<!-- 						<p>session empty</p> -->
						<label id="authorFNLabel">First Name:</label><s:textfield id="authorFN" name="authorFN0" value="%{authorFN0}"/>
						<label id="authorLNLabel">Last Name:</label><s:textfield id="authorLN" name="authorLN0" value="%{authorLN0}"/>
					<s:if test="authorList != null">
						<s:iterator value="authorList"  begin="1" status="part">
							<div>
								<label id="authorFNLabel">First Name:</label><s:textfield id="authorFN" name="authorFN%{#part.index+1}" value="%{getFirstName()}"/>
								<label id="authorLNLabel">Last Name:</label><s:textfield id="authorLN" name="authorLN%{#part.index+1}" value="%{getLastName()}"/>
							</div>
						</s:iterator>
					</s:if>
				</s:else>
			</div>
		</div>		
		<div>
			<label>Publisher*:</label>
			<s:fielderror><s:param>publisher</s:param></s:fielderror>
			<select name="publisher">
				<s:if test="!session.isEmpty()">
					<option value="0" selected="selected">-Select a Publisher-</option>
				</s:if>
				<s:else>
					<option value="0">-Select a Publisher empty-</option>
				</s:else>
				<s:iterator value="listPublisher" var="publishers">
					<s:if test="%{publisherSelected == id_publisher}">
						<option value="<s:property value='id_publisher'/>" selected="selected">
							<s:property value="name"/>-<s:property value="country"/>
						</option>
					</s:if>
					<s:else>
						<option value="<s:property value='id_publisher'/>">
							<s:property value="name"/>-<s:property value="country"/>
						</option>
					</s:else>
				</s:iterator>
			</select>
		</div>
		<div>
		<s:fielderror><s:param>year</s:param></s:fielderror>
			<label>Year*:</label>
				<s:if test="!session.isEmpty()">
					<s:textfield  name="year" value="%{session.get('year')}"/>
				</s:if>
				<s:else>
					<s:textfield name="year" value="%{year}"/>
				</s:else>
		</div>
		<div>
			<label>Volume:</label>
			<s:if test="!session.isEmpty()">
				<s:textfield  name="volume" value="%{session.get('volume')}"/>
			</s:if>
			<s:else>
				<s:textfield name="volume" value="%{volume}"/>
			</s:else>
		</div>
		<div>
			<label>Series:</label>
			<s:if test="!session.isEmpty()">
				<s:textfield name="series" value="%{session.get('series')}"/>
			</s:if>
			<s:else>
				<s:textfield name="series" value="%{series}"/>
			</s:else>	
		</div>
		<div>
			<label>Edition:</label>
			<s:if test="!session.isEmpty()">
				<s:textfield name="edition" value="%{session.get('edition')}"/>
			</s:if>
			<s:else>
				<s:textfield name="edition" value="%{edition}"/>
			</s:else>
		</div>
		<div>
			<label>Month:</label>
			<select name="month">
				<option value="">-Select a month-</option>
				<s:iterator value="monthList" status="part">
					<s:if test="!session.isEmpty()">
						<s:if test="%{session.get('month') == monthList.get(#part.index)}">
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
		</div>
		<div>
			<label>Note:</label>
			<s:if test="!session.isEmpty()">
				<s:textfield name="note" value="%{session.get('note')}"/>
			</s:if>
			<s:else>
				<s:textfield name="note" value="%{note}"/>
			</s:else>
		</div>
		<div>
		<s:submit value="Insert"/>
		<s:submit value="Cancel Insert" method="cancel"/>
		<s:submit value="Add Publisher" method="browse"/>
		</div>
	</s:form>
</div>

<%@include file="footer.jsp" %>