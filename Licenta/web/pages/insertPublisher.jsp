<%@include file="header.jsp" %>
	<div class="inserPublisher">
		<s:form name="publisher" id="publisher" action="addPublisher">
			<div>
				<s:fielderror><s:param>name</s:param></s:fielderror>
				<s:label>Publisher Name*:</s:label>
				<s:textfield name="name" value="%{name}"/>
			</div>
			<div id="country">
				<s:fielderror><s:param>country</s:param></s:fielderror>
				<s:label>Publisher Country*:</s:label>
				<select id="stateSelect"  name="selectedState" onchange="loadCities();">
					<option value="NON">-Select Country-</option>
					<s:iterator value="countryList" status="itr">	
						<s:if test="%{code.equals(country)}">
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
						<s:set var="selectedCity" value="%{selectedCity}"/>
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
			<div id="button">
				<s:if test="!sessionBook.isEmpty()">
					<s:submit value="Insert"/>
					<s:submit value="Back" method="back"/>
				</s:if>
				<s:else>
					<s:submit value="Insert" method="execute2"/>
					<s:submit value="Cancel" method="cancel"/>
				</s:else>
			</div>
		</s:form>
	</div>
<%@include file="footer.jsp"%>