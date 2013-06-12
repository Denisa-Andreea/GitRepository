<%@include file="header.jsp"%>
<div id="content">
	<s:if test="sessionBook.get('login') == true">
		<div id="formularSpan">
			<span>Insert Publisher</span>
		</div>
		<center>
			<div id="formular">
				<s:form name="publisher" id="publisher" action="addPublisher">
					<table id="formularContent">
						<tr>
							<td><label>Publisher Name*:</label></td>
							<td><s:textfield id="name" name="name" value="%{name}" /></td>
							<td><s:fielderror>
									<s:param>name</s:param>
								</s:fielderror></td>
						</tr>
						<tr>
							<td><label>Publisher Country*:</label></td>
							<td>
								<div id="country">
									<select id="stateSelect" name="selectedState"
										onchange="loadCities();">
										<option value="NON">-Select Country-</option>
										<s:iterator value="countryList" status="itr">
											<s:if test="%{code.equals(country)}">
												<option selected="selected"
													value="<s:property value='code'/>">
													<s:property value="name" />
												</option>
											</s:if>
											<s:else>
												<option value="<s:property value='code'/>">
													<s:property value="name" />
												</option>
											</s:else>
										</s:iterator>
									</select>
								</div>
							</td>
							<td><s:fielderror>
									<s:param>country</s:param>
								</s:fielderror></td>
						</tr>
						<tr>
							<td><label>Publisher City*:</label></td>
							<td>
								<div id="city">
									<select id="city" name="city">
										<s:if test="cityList.isEmpty()">
											<option>-First select the Country-</option>
										</s:if>
										<s:else>
											<s:set var="selectedCity" value="%{selectedCity}" />
											<option value="NON">-Select the City-</option>
											<s:iterator value="cityList" status="itr" var="element">
												<s:if test="%{#element.equals(#selectedCity)}">
													<option selected="selected"
														value="<s:property value='%{element}'/>">
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
							</td>
							<td><s:fielderror>
									<s:param>city</s:param>
								</s:fielderror></td>
						</tr>
						<tr>
							<td></td>
							<td><div id="buttonForm">
									<s:if test="sessionBook.get('book') == true">
										<s:submit value="Insert" />
										<s:submit value="Back" method="back" />
									</s:if>
									<s:else>
										<s:submit value="Insert" method="execute2" />
										<s:submit value="Cancel" method="cancel" />
									</s:else>
								</div></td>
						</tr>
					</table>
				</s:form>
			</div>
		</center>
	</s:if>
	<s:else>Please login!!!</s:else>
</div>
<%@include file="footer.jsp"%>