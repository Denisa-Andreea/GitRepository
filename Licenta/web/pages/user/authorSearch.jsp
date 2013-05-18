<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<s:if test="%{authors.size()>0}">
	<c:forEach var="authors" items="${authors}" varStatus="step">
		<div
			style="position: absolute; zoom: 1; margin: 30px 0 0 -80px; text-align: right; font-size: 18px; color: #ddd; font-style: italic;">
			#${step.count}</div>
		<div style="float: left; width: 500px; margin-top: 30px;">
			<c:out value="${authors.key.firstName}" />
			<c:out value="${authors.key.lastName}"></c:out>
			<br />
			<c:if test="${not empty  authors.value}">
				<span style="margin: 38px;">Books: </span>
				<ul class="list">
					<c:forEach var="value" varStatus="step" items="${authors.value}">
						<div
							style="position: absolute; zoom: 1; margin: 0 0 0 -45px; text-align: right; font-size: 20px; color: #ddd; font-style: italic;">
							${step.count}</div>
						<li><span>Title: </span> <c:out value="${value.title}" /><br />
							<span>Year/Month: </span> <c:if test="${!empty value.month}">
								<c:out value="${value.month}" />
							</c:if> <c:out value="${value.year}" /><br /> <c:if
								test="${!empty value.autors}">
								<span>Author(s): </span>
								<ul>
									<c:forEach var="elem" items="${value.autors}">
										<li><a
											href="javascript:displayFunction('<c:out value='${elem.firstName}'/> <c:out value='${elem.lastName}'/>')"><c:out
													value="${elem.firstName}" /> <c:out
													value="${elem.lastName}" /></a></li>
									</c:forEach>
								</ul>
							</c:if> <span>Publisher: </span> <c:out value="${value.publisher}" />-<c:out
								value="${value.country}" />, <c:out value="${value.city}" /></li>
								
					</c:forEach>
				</ul>
			</c:if>
		</div>
		<div class="iconsBox">
			<span class="icons download" title="File is aviable by direct link"></span>
		</div>
		<div
			style="zoom: 1; border-top: 1px solid #ddd; overflow: hidden; font-size: 1px; margin: 0; padding: 0; width: 100%;"></div>
			

	</c:forEach>
</s:if>
<s:else>
	Not Found!!!
</s:else>