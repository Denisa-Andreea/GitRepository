var limit = 6;
var intrari = 0; 
var counter;
//functia principala.. contine conditiile si ce sse afisseaza in fiecare caz
function addInput(divName,size) {
	//alert(size+' '+intrari);
	if(intrari == 0){
		 counter = size-1; 
		 intrari = intrari +2;
	}else if(intrari == 1){
		 counter = 0;
	}
	var id = counter;		
	var authorFName = document.getElementById('authorFN').value;
	if(id>0 && !verificareFilds(id)){
		return alert("Compleate all the authors!!!");
	} else if(authorFName == 'Unknown' || authorFName.replace(/\s/g,'') == ""){
		return alert("Please insert the first author to call for another");
	}else if(counter == limit-1){
		return alert("You have reached the limit of adding " + limit + " inputs");
	}else{
		id++;		
		counter++;
		addFildsAuthors(divName,counter,id);
	}
}

//functia pentru adaugarea unui nou fild pentru autori
function addFildsAuthors(divName,counter,id){
	var newdiv = document.createElement('div');
	var newLabelFN = document.getElementById('authorFNLabel').cloneNode(true);
	var newFN = document.getElementById('authorFN').cloneNode(true);

	newFN.value="";
	newFN.id = id;
	var newLabelLN = document.getElementById('authorLNLabel').cloneNode(true);
	var newLN = document.getElementById('authorLN').cloneNode(true);

	newLN.value = "";
	newLN.id = "authorLN"+id;
	newdiv.appendChild(newLabelFN);
	newdiv.appendChild(newFN);
	newdiv.appendChild(newLabelLN);
	newdiv.appendChild(newLN);
	document.getElementById(divName).appendChild(newdiv);
	document.getElementById(id).focus();
}


//verifica daca fildurile pentru autori de la al doilea pana la ultimul contin sau nu caractere..
//daca nu contin intorc fals si nu permit adaugarea altuia pana nu sunt complecate toate cele existente	
	function verificareFilds(id){
		var index = 1;
		while(index <= id){
			var field1 = document.getElementById(index).value;
			if(field1.replace(/\s/g,'') == ""){
				return false;
			}else{
				index++;
			}
		}
		return true;
	}