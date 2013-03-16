var counter = 0;
var id = 0;
var limit = 6;

//functia principala.. contine conditiile si ce sse afisseaza in fiecare caz
function addInput(divName) {
	var authorFName = document.getElementById('authorFN').value;
	if(id>0 && !verificareFilds(id)){
		return alert("Compleate all the authors!!!");
	} else if(authorFName == 'Unknown' || authorFName.replace(/\s/g,'') == ""){
		return alert("Please insert the first author to call for another");
	}else if(counter == limit){
		return alert("You have reached the limit of adding " + limit + " inputs");
	}else{
		id=id+2;
		counter++;
		addFildsAuthors(divName,counter,id);
	}
}

//functia pentru adaugarea unui nou fild pentru autori
function addFildsAuthors(divName,counter,id){
	var newdiv = document.createElement('div');
	var newLabelFN = document.getElementById('authorFNLabel').cloneNode(true);
	var newFN = document.getElementById('authorFN').cloneNode(true);
	newFN.name="authorFN"+counter;
	newFN.value="";
	newFN.id = id-1;
	var newLabelLN = document.getElementById('authorLNLabel').cloneNode(true);
	var newLN = document.getElementById('authorLN').cloneNode(true);
	newLN.name="authorLN"+counter;	
	newLN.value = "";
	newLN.id = id;
	newdiv.appendChild(newLabelFN);
	newdiv.appendChild(newFN);
	newdiv.appendChild(newLabelLN);
	newdiv.appendChild(newLN);
	document.getElementById(divName).appendChild(newdiv);
	document.getElementById(id-1).focus();
}


//verifica daca fildurile pentru autori de la al doilea pana la ultimul contin sau nu caractere..
//daca nu contin intorc fals si nu permit adaugarea altuia pana nu sunt complecate toate cele existente	
	function verificareFilds(id){
		var index = 1;
		while(index < id){
			var field1 = document.getElementById(index).value;
			if(field1.replace(/\s/g,'') == ""){
				return false;
			}else{
				index = index +2;
			}
		}
		return true;
	}