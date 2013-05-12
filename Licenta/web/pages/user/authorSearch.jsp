<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<s:if test="%{authors.size()>0}">
	<c:forEach var="authors" items="${authors}">
		<c:out value="${authors.key.firstName}" />
		<c:out value="${authors.key.lastName}"></c:out>
		<br />
		<span>Books: </span>
		<ol>
			<c:forEach var="value" items="${authors.value}">
				<li><span>Title: </span>
				<c:out value="${value.title}" /><br />
					 <span>Year/Month: </span> 
						<c:if test="${!empty value.month}">
							<c:out value="${value.month}" />
						</c:if>	
						<c:out value="${value.year}" /><br />
					<c:if test="${!empty value.autors}">
						<span>Author(s): </span>
							<ul>
								<c:forEach var="elem" items="${value.autors}">
									<li><a
										href="javascript:displayFunction('<c:out value='${elem.firstName}'/> <c:out value='${elem.lastName}'/>')"><c:out value="${elem.firstName}"/> <c:out value="${elem.lastName}"/></a></li>
								</c:forEach>
							</ul>
					</c:if>
						<span>Publisher: </span> <c:out value="${value.publisher}"/>-<c:out value="${value.country}"/>, <c:out value="${value.city}"/>
					</li>
				</c:forEach>
			</ol>
	</c:forEach>
</s:if>
<s:else>
	Not Found!!!
</s:else>