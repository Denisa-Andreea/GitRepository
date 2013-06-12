<%@ include file="headerUtilizator.jsp"%>
<center>
	<div class="rootBox" id="bodyUtilizator" style="text-align: center;">
		<div style="font-size: 52px; padding: 15px 15px 0px 15px;">
			<a id="logoText" style="text-decoration: none;" href="user"> <span
				class="color1">Book</span><span class="color2"> &amp; Article</span>
			</a>
		</div>
		<div class="color4"
			style="color: #90A5A8; font-weight: 400; padding: 15px 0 15px 0;">
			<i> The world's largest <strong style="font-style: italic;">
					ebook library </strong> .
			</i>
		</div>
		<div class="b-search-form">
			<div class="b-search-input g-clearfix">
				<div class="input">
					<s:textfield id="word" name="word" maxlength="200"
						x-webkit-speech=" x-webkit-speech" />
					<span class="darr png adv pc" style="display: block;"></span>
				</div>
				<div id="selectDiv" class="select">
					<select id="category" name="category">
						<option value="books">Books</option>
						<option value="authors">Authors</option>
						<option value="article">Article</option>
					</select>
				</div>
				<div class="button">
					<div class="inner">
						<input type="submit" value="search" onclick="searchFunction();">
					</div>
				</div>
			</div>
		</div>
		<div id="result"
			style="margin: 0 auto; overflow: hidden; text-align: left; width: 638px !important;">

		</div>
	</div>
</center>
<%@ include file="footerUtilizator.jsp"%>