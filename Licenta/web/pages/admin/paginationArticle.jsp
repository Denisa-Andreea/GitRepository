<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div id="pagination">
	<ul>
		<s:if test="%{page != 1}">
			<li class="next"><a
				href="fetchArticle?page=1&sort=<s:property value='sort'/>&column=<s:property value="column"/>">First</a></li>
			<li class="next"><a
				href="fetchArticle?page=<s:property value='page - 1'/>&sort=<s:property value='sort'/>&column=<s:property value="column"/>">Previous</a></li>
		</s:if>
		<s:else>
			<li class="off">First</li>
			<li class="off">Previous</li>
		</s:else>
		<c:forEach begin="1" end="${numberOfPages}" var="i">
			<c:choose>
				<c:when test="${page eq i}">
					<li class="active"><a
						href="fetchArticle?page=${i}&sort=<s:property value='sort'/>&column=<s:property value="column"/>">${i}</a></li>
				</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${(page eq 1) or (page eq numberOfPages)}">
						<c:choose>
							<c:when test="${(i-page < 5) and (i-page > -5)}">
								<li class="next"><a
									href="fetchArticle?page=${i}&sort=<s:property value='sort'/>&column=<s:property value="column"/>">${i}</a></li>
							</c:when>
						</c:choose>
					</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${(i-page < 3) and (i-page > -3)}">
							<li class="next"><a
								href="fetchArticle?page=${i}&sort=<s:property value='sort'/>&column=<s:property value="column"/>">${i}</a></li>
						</c:when>
					</c:choose>
				</c:otherwise>
				</c:choose>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		<s:if test="%{page < numberOfPages}">
			<li class="next"><a
				href="fetchArticle?page=<s:property value='page + 1'/>&sort=<s:property value='sort'/>&column=<s:property value="column"/>">Next</a></li>
			<li class="next"><a
				href="fetchArticle?page=<s:property value='numberOfPages'/>&sort=<s:property value='sort'/>&column=<s:property value="column"/>">Last</a></li>
		</s:if>
		<s:else>
			<li class="off">Next</li>
			<li class="off">Last</li>
		</s:else>
	</ul>
</div>