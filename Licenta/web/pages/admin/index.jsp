<%@include file="header.jsp"%>
<div id="bodyFirstPage">
	<s:if test="sessionLogin.get('login') == true">
		<div class="imageBook">
			<a href="fetchBooks"><img src="stile/pictures/book.png"
				alt="Picture not found!!"> <br />
			<span>Book</span> </a>
		</div>

		<div class="imageArticle">
			<a href="fetchArticle"> <img src="stile/pictures/article.png"
				alt="Picture not found!!"> <br /> <span>Article</span>
			</a>
		</div>
		<div class="imageAcount">
			<a href="gotoChangePass"> <img src="stile/pictures/password.png"
				alt="Picture not found!!"> <br />
			<span>Change Password</span>
			</a>
		</div>
		<div class="imagePublisher">
			<a href="viewPublisher"><img src="stile/pictures/publisher.png"
				alt="Picture not found!!"> <br /> <span>Publisher</span> </a>
		</div>

		<div class="imageJournal">
			<a href="viewJournal"> <img src="stile/pictures/journal.png"
				alt="Picture not found!!"> <br />
			<span> Journal</span>
			</a>
		</div>
	</s:if>
	<s:else>
			Please login!!!
		</s:else>
</div>
<%@include file="footer.jsp"%>
