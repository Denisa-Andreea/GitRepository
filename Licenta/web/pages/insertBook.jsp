<%@include file="header.jsp" %>
<div class="insertBook"> 
	<s:form action="bookInsert" method="post" name="bookInsert">		
		<div>
			<s:label>Title*:</s:label>
			<s:textfield name="title"/>
		</div>
		<div>
			<s:label>Athor(s)*:</s:label>
			<input type="button" onclick="addInput('authors');" id="buttonAdd" name="add" value="Add author" />
			<div id="authors">
				<div>
					<s:label value="First Name:" id="authorFNLabel"/><s:textfield id="authorFN" name="authorFN" value="%{authorFN}"/>
					<s:label value="Last Name:" id="authorLNLabel"/><s:textfield id="authorLN" name="authorLN" value="%{authorLN}"/>
				</div>
				<s:iterator value="authorList" begin="1" end="%{#authorList.size()}" status="part">
						<div>
							<s:label value="First Name:" id="authorFNLabel"/><s:textfield id="authorFN" name="authorFN%{#part.index+1}"><s:property value="authorList.get(%{#part.index+1}.getFirstName)"/></s:textfield>
							<s:label value="Last Name:" id="authorLNLabel"/><s:textfield id="authorLN" name="authorLN%{#part.index+1}"><s:property value="authorList.get(%{#part.index+1}.getFirstName)"/></s:textfield>
						</div>
				</s:iterator>
			</div>
		</div>		
		
		<div>
			<s:label>Year*:</s:label><s:textfield  name="year"/>
		</div>
		<div>
			<s:label>Volume:</s:label><s:textfield name="value"/>
		</div>
		<s:submit value="Next" method="next"/>
		<s:submit value="Cancel Insert" method="cancel"/>
	</s:form>
</div>

<%@include file="footer.jsp" %>