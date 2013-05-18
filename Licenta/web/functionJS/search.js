function searchFunction() {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		// alert("if");
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		// alert("else");
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (typeof xmlhttp == "undefined") {
		ContentDiv.innerHTML = "<h1>XMLHttp cannot be created!</h1>";
	} else {
		var word = document.getElementById("word").value;
		var category = document.getElementById('category').value;
		if(category == "NON"){
			return document.getElementById("error").innerHTML = "<span>Please select a category!</span>";
		}
		var url = "search";
		url += "?word=" + word + "&category=" + category;
		// alert(url);
		xmlhttp.open("GET", url, true);

		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4) {
				document.getElementById("result").innerHTML = this.responseText;
			}
		};
		xmlhttp.send(null);
	}
}

function displayFunction(wordToUse) {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		// alert("if");
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		// alert("else");
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (typeof xmlhttp == "undefined") {
		ContentDiv.innerHTML = "<h1>XMLHttp cannot be created!</h1>";
	} else {
		var url = "search";
		url += "?word=" + wordToUse + "&category=" + "displayOneAuthor";
		xmlhttp.open("GET", url, true);

		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4) {
				document.getElementById("result").innerHTML = this.responseText;
			}
		};
		xmlhttp.send(null);
	}
}

function advanceSearchFunction() {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		// alert("if");
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		// alert("else");
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (typeof xmlhttp == "undefined") {
		ContentDiv.innerHTML = "<h1>XMLHttp cannot be created!</h1>";
	} else {
		var author = document.getElementById("author").value;
		var title = document.getElementById("title").value;
		var yearDown = document.getElementById("yearDown").value;
		var yearUp = document.getElementById("yearUp").value;
		var category ="";
		var radioBut = document.getElementsByName("radioButton");
		for(var i = 0;i < radioBut.length; i++){
			if (radioBut[i].checked) {
		        category = radioBut[i].value;
		    }
		}		
		var url = "advanceSearch";
		url += "?author=" + author +"&title="+title+"&yearDown="+yearDown+"&yearUp="+yearUp+ "&category=" + category;
		xmlhttp.open("GET", url, true);

		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4) {
				document.getElementById("result").innerHTML = this.responseText;
			}
		};
		xmlhttp.send(null);
		}
}
