<%@include file="header.jsp" %>
	<div class="bookBody">
		<p>Tabel of books</p>
		<table class="tabel">
			<tr class="headrow"><th>Title</th><th>Author(s)</th><th>Publisher</th><th>Address</th><th>Year</th><th>Volume</th><th>Series</th><th>Edition</th><th>Month</th><th>Note</th><th></th></tr>
			<s:iterator value="listBook" var="bookAuthor">
				<tr><td class="mediumField"><s:property value="title"/></td>
					<td class="bigField"><s:property value="autors"/></td>					
					<td class="smallField"><s:property value="publisher"/></td>
					<td class="mediumField"><s:property value="address"/></td>
					<td class="smallField"><s:property value="year"/></td>
					<td class="smallField"><s:property value="volume"/></td>
					<td class="smallField"><s:property value="series"/></td>
					<td class="smallField"><s:property value="edition"/></td>
					<td class="smallField"><s:property value="month"/></td>
					<td class="bigField"><s:property value="note"/></td>
					<td class="edit"><a href="editBook?id=<s:property value='idBook'/>"><img src="stile/pictures/edit.gif" alt="edit"/></a></td>
				</tr>
			</s:iterator>
		</table>		
		<div class="buttons">
			<a href="fetchPublisher">Insert</a>
			<a href="index.jsp">Back</a>
		</div>
	</div>	
<%@include file="footer.jsp" %>
