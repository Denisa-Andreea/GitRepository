<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<s:if test="%{book.size()>0}">
	<s:iterator status="ipart" value="book">
		<div id="displayIndex">
			#
			<s:property value="%{#ipart.index+1}" />
		</div>
		<div style="float: left; width: 500px;margin-top: 30px;">
			<span>Title:</span> <s:property value="title" />
			<ul>
				<li><span>Year/Month: </span>  <s:if
						test="%{!month.isEmpty()}">
						<s:property value="month" />
					</s:if><s:property value="year" /></li>
				<li><span>Publisher: </span> <s:property value="publisher" />,
					<s:property value="country" /> <s:property value="city" /></li>
				<li><span>Author(s): </span> <s:iterator value="autors"
						status="iterator">
						<s:if test="%{#iterator.index == autors.size()-1}">
							<a href="javascript:displayFunction()"><s:property
									value="firstName" /> <s:property value="lastName" /></a>
						</s:if>
						<s:else>
							<a
								href="javascript:displayFunction('<s:property value='firstName'/> <s:property value='lastName'/>')"><s:property
									value="firstName" /> <s:property value="lastName" /> </a>,
					</s:else>
					</s:iterator></li>
				<s:if test="%{volume != 0}">
					<li><span>Volume: </span> <s:property value="volume" /></li>
				</s:if>
				<s:if test="%{!series.isEmpty()}">
					<li><span>Series: </span> <s:property value="series" /></li>
				</s:if>
				<s:if test="%{!edition.isEmpty()}">
					<li><span>Edition: </span> <s:property value="edition" /></li>
				</s:if>
				<s:if test="%{!note.isEmpty()}">
					<li><span>Note: </span> <s:property value="note" /></li>
				</s:if>
			</ul>
		</div>
		<div class="iconsBox">
			<span class="icons download" title="File is aviable by direct link"></span>
		</div>
		<div
			style="zoom: 1; border-top: 1px solid #ddd; overflow: hidden; font-size: 1px; margin: 0; padding: 0; width: 100%;"></div>
	</s:iterator>

</s:if>
<s:else>
	<span>No result found!!!!</span>
</s:else>