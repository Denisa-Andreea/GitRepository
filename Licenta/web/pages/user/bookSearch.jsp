<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<s:if test="%{book.size()>0}">
	<s:iterator value="book">
		<span>Title: <s:property value="title" /></span>
		<ul>
			<li><span>Year/Month: </span> <s:property value="year" /> <s:if
					test="month != ''">
					<s:property value="month" />
				</s:if></li>
			<li><span>Publisher: </span> <s:property value="publisher" />,
				<s:property value="country" /> <s:property value="city" /></li>
			<li><span>Author(s): </span> <s:iterator value="autors"
					status="iterator">
					<s:if test="%{#iterator.index == autors.size()-1}">
						<a href="javascript:displayFunction()"><s:property
								value="firstName" /> </a>
					</s:if>
					<s:else>
						<a
							href="javascript:displayFunction('<s:property value='firstName'/> <s:property value='lastName'/>')"><s:property
								value="firstName" /> <s:property value="lastName" /> 
						 </a>,
					</s:else>
				</s:iterator></li>
			<s:if test="%{volume != 0}">
				<li><span>Volume: </span> <s:property value="volume" /></li>
			</s:if>
			<s:if test="%{series!=''}">
				<li><span>Series: </span> <s:property value="series" /></li>
			</s:if>
			<s:if test="%{edition!=''}">
				<li><span>Edition: </span> <s:property value="edition" /></li>
			</s:if>
			<s:if test="%{note != ''}">
				<li><span>Note: </span> <s:property value="note" /></li>
			</s:if>
		</ul>
	</s:iterator>
</s:if>
<s:else>
	<span>No result found!!!!</span>
</s:else>