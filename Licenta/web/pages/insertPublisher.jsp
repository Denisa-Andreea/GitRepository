<%@include file="header.jsp" %>
	<div>
		<s:form name="publisher" id="publisher" action="addPublisher">
			<div>
				<s:fielderror><s:param>name</s:param></s:fielderror>
				<s:label>Publisher Name*:</s:label>
				<s:textfield name="name" value="%{name}"/>
			</div>
			<div>
				<s:fielderror><s:param>Country</s:param></s:fielderror>
				<s:label>Publisher Country*:</s:label>
				<select id="stateSelect"  name="selectedState" onchange="loadCities(document.publisher.selectedState.options[document.publisher.selectedState.selectedIndex].value);">
					<option value="NON">-Select Country-</option>
					<s:iterator value="countryList" status="itr">
						<option value="<s:property value='code'/>"><s:property value="name"/></option>
					</s:iterator>
				</select>
			</div>
			<div>
				<s:fielderror><s:param>City</s:param></s:fielderror>
				<s:label>Publisher City:</s:label>
				<select id="city" name="city" >
					<option value="">-Please select the Country first-</option>
					<s:iterator value="cityList" status="itr">
						<option value="%{#itr.index}"/>
					</s:iterator>
				</select>
			</div>
			<s:if test="!sessionBook.isEmpty()">
				<s:submit value="Insert"/>
				<s:submit value="Back" method="back"/>
			</s:if>
			<s:else>
				<s:submit value="Insert" method="execute2"/>
				<s:submit value="Cancel" method="cancel"/>
			</s:else>
		</s:form>
	</div>
<%@include file="footer.jsp"%>