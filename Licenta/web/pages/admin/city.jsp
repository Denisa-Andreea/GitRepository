<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<select id="city" name="city">
	<s:if test="cityList.isEmpty()"><option>-First select the Country-</option></s:if>
	<s:else>
		<option value="NON">-Select the City-</option>
		<s:iterator value="cityList" status="itr" var="element">
			<option value="<s:property value='%{element}'/>">
				<s:property value="%{element}" />
			</option>
		</s:iterator>
	</s:else>
</select>
