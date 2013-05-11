function loadCities() {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			//alert("if");
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			//alert("else");
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		if (typeof xmlhttp == "undefined") {
			ContentDiv.innerHTML = "<h1>XMLHttp cannot be created!</h1>";
		} else {
			var form = document['publisher'];
			var country = form['selectedState'].value;
			var url = "cityToGet";
			url += "?code=" + country;
			//alert(url);
			xmlhttp.open("GET",url,true); 
			
			xmlhttp.onreadystatechange = function(){
				if(this.readyState == 4){
					document.getElementById("city").innerHTML = this.responseText;
				}
			};
			xmlhttp.send(null);
		}
	}

