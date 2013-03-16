<%@include file="header.jsp" %>
	<div class="bookBody">
		<p>Tabel of books</p>
		<table class="tabel">
			<tr class="headrow"><th>Title</th><th>Author(s)</th><th>Publisher</th><th>Address</th><th>Year</th><th>Volume</th><th>Series</th><th>Edition</th><th>Month</th><th>Note</th></tr>
			<s:iterator value="listBook" var="bookAuthor">
				<tr><td><s:property value="title"/>
					<td><s:property value="autors"/></td>					
					<td><s:property value="publisher"/></td>
					<td><s:property value="address"/></td>
					<td><s:property value="year"/></td>
					<td><s:property value="volume"/></td>
					<td><s:property value="series"/></td>
					<td><s:property value="edition"/></td>
					<td><s:property value="month"/></td>
					<td><s:property value="note"/></td>
				</tr>
			</s:iterator>
		</table>		
	</div>	
	<div class="buttons">
		<a href="fetchPublisher">Insert</a>
		<a href="index.jsp">Back</a>
	</div>
<%@include file="footer.jsp" %>
