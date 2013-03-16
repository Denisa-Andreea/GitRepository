<%@include file="header.jsp" %>
<div class="insertBook"> 
	<s:form action="bookInsert" method="post" name="bookInsert">		
		<div>
			<label>Title*:</label>
			<s:if test="session.isEmpty()">
				<s:textfield name="title" value="%{title}"/>			
			</s:if>
			<s:else>
				<s:textfield name="title" value="%{session.title}"/>
			</s:else>
			
		</div>
		<div>
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
			<select name="publisher">
				<option selected="selected">-Select a Publisher-</option>
				<s:iterator value="listPublisher" var="publishers">
					<option value="<s:property value="id_publisher"/>" >
						<s:property value="name"/>
					</option>
				</s:iterator>
			</select>
		</div>
		<div>
			<label>Year*:</label><s:textfield  name="year" value="0"/>
		</div>
		<div>
			<label>Volume:</label><s:textfield name="volume" value="0"/>
		</div>
		<div>
		<s:submit value="Insert"/>
		<s:submit value="Cancel Insert" method="cancel"/>
		<s:submit value="Add Publisher" method="browse"/>
		</div>
	</s:form>
	<s:form>
	
	</s:form>
</div>

<%@include file="footer.jsp" %>