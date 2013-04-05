
function checkbox_test() {
	var i;
	url = '';
	input_obj = document.getElementsByName('checkbox');
	for (i = 0; i < input_obj.length-1; i++) {
		if(input_obj[i].checked === true && input_obj[i+1].checked === true){
			//alert("if1");
			url = url + input_obj[i].value +',';
		}
		if(input_obj[i].checked === true && input_obj[i+1].checked === false){
			//alert("if2");
			url = url + input_obj[i].value;
		}
		if(input_obj[i].checked === false && input_obj[i+1].checked === true && url !==''){
			//alert("if3");
			url = url + ",";
		}
	}
	i = input_obj.length-1;
	if(input_obj[i].checked === true){
		url = url + input_obj[i].value;
	}
	//alert(url);
	return url;        
}




