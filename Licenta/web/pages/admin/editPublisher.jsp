<%@include file="header.jsp" %>
<div class="content">
	<s:if test="sessionBook.get('login') == true">
	<s:form name="publisher" id="publisher" action="updatePublisher">
		<div>
			<div>
				<s:fielderror><s:param>name</s:param></s:fielderror>
				<s:label>Publisher Name*:</s:label>
				<s:hidden  name="id" value="%{publisher.getId_publisher()}"/>
				<s:textfield name="name" value="%{publisher.getName()}"/>
			</div>
			<div id="country">
				<s:fielderror><s:param>country</s:param></s:fielderror>
				<s:label>Publisher Country*:</s:label>
				<select id="stateSelect"  name="selectedState" onchange="loadCities();">
					<option value="NON">-Select Country-</option>
					<s:iterator value="countryList" status="itr">	
						<s:if test="%{code.equals(state)}">
							<option selected="selected" value="<s:property value='code'/>"><s:property value="name"/></option>
						</s:if>
						<s:else>
							<option value="<s:property value='code'/>"><s:property value="name"/></option>
						</s:else>
					</s:iterator>
				</select>
			</div>
			<div id="city">
				<s:fielderror><s:param>city</s:param></s:fielderror>
				<s:label>Publisher City*:</s:label>
				<select id="city" name="city">
					<s:if test="cityList.isEmpty()"><option>-First select the Country-</option></s:if>
					<s:else>
						<s:set var="selectedCity" value="%{publisher.city}"/>
						<option value="NON">-Select the City2-</option>
						<s:iterator value="cityList" status="itr" var="element">
							<s:if test="%{#element.equals(#selectedCity)}">
								<option selected="selected" value="<s:property value='%{element}'/>">
									<s:property value="%{element}" />
								</option>
							</s:if>
							<s:else>
								<option value="<s:property value='%{element}'/>">
									<s:property value="%{element}" />
								</option>
							</s:else>
						</s:iterator>
					</s:else>
				</select>
			</div>
		</div>
		<div>
			<s:submit value="Update" onclick="return confirm('Are you sure you want to edit this publisher')"/>
			<s:submit value="Cancel Update" method="cancel"/>
		</div>
	</s:form>
	</s:if>
	<s:else>Please login!!!</s:else>
</div>
<%@include file="footer.jsp" %>