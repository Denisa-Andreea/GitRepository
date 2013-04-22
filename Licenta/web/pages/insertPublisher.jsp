<%@include file="header.jsp" %>
<div class="content">
	<s:if test="sessionBook.get('login') == true">
	<s:form name="publisher" id="publisher" action="addPublisher">
		<div class="insertPublisher">
			<div id="name">
				<s:label>Publisher Name*:</s:label>
				<span class="input">
					<s:fielderror><s:param>name</s:param></s:fielderror>
					<s:textfield name="name" value="%{name}"/>
				</span>
			</div>
			<div id="country">
				<s:label>Publisher Country*:</s:label>
				<span class="input">
				<s:fielderror><s:param>country</s:param></s:fielderror>
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
				</span>
			</div>
			<div id="city">
				<s:label>Publisher City*:</s:label>
				<span class="input">
				<s:fielderror><s:param>city</s:param></s:fielderror>
				<select id="city" name="city">
					<s:if test="cityList.isEmpty()"><option>-First select the Country-</option></s:if>
					<s:else>
						<s:set var="selectedCity" value="%{selectedCity}"/>
						<option value="NON">-Select the City-</option>
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
				</span>
			</div>
		</div>
		<div>
			<s:if test="sessionBook.get('book') == true">
				<s:submit value="Insert"/>
				<s:submit value="Back" method="back"/>
			</s:if>
			<s:else>
				<s:submit value="Insert" method="execute2"/>
				<s:submit value="Cancel" method="cancel"/>
			</s:else>
		</div>
	</s:form>
	</s:if>
	<s:else>Please login!!!</s:else>
</div>
<%@include file="footer.jsp"%>