<%@ include file="headerUtilizator.jsp"%>
<div class="bodyUtilizator" id="bodyUtilizator">
	<div id="searchDiv">
		<div id="inputDiv">
			<s:textfield id="word" name="word"/>
		</div>
		<div id="selectDiv">
			<select id="category" name="category">
				<option value="NON" selected="selected">-Select a category-</option>
				<option value="books">Books</option>
				<option value="authors">Authors</option>
			</select>
		</div>
		<div id="error"></div>
		<input type="button" value="Search" onclick="searchFunction();" />
	</div>
	<div id="result"></div>
</div>
<%@ include file="footerUtilizator.jsp"%>