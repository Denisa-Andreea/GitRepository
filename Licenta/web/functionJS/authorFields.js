// 	var counter = 1;
// 	var limit = 6;
// 	function addInput(divName) {
// 		if (counter == limit) {
// 			alert("You have reached the limit of adding " + counter + " inputs");
// 		} else {
// 			var newdiv = document.createElement('div');
// 			alert("else");
// 			newdiv.innerHTML = "<label>Nume:</label> <input name='authorFN"+counter+"'/><label>Prenume:</label><input name='authorLN"+counter+"'/><br/>";
// 			document.getElementById(divName).appendChild(newdiv).innerHTML;
// 			counter++;
// 		}
// 	}
 	var counter = 1;
 	var id = 1;
	var limit = 6;
//functia pentru adaugarea unui nou fild pentru autori
	function addFildsAuthors(counter, divName){
		var newdiv = document.createElement('div');
		var newLabelFN = document.getElementById('authorFNLabel').cloneNode(true);
		var newFN = document.getElementById('authorFN').cloneNode(true);
		newFN.name="authorFN"+counter;
		newFN.value="";
		newFN.id = id;
		var newLabelLN = document.getElementById('authorLNLabel').cloneNode(true);
		var newLN = document.getElementById('authorLN').cloneNode(true);
		newLN.name="authorLN"+counter;	
		newLN.value = "";
		newLN.id = id+1;
		newdiv.appendChild(newLabelFN);
		newdiv.appendChild(newFN);
		newdiv.appendChild(newLabelLN);
		newdiv.appendChild(newLN);
		document.getElementById(divName).appendChild(newdiv);
		document.getElementById(counter-1).focus();
		id = id+2;
	}
//funtie pentru verificarea fildurilor inainte de adaugarea altuia..si limitarea acestora la 6
	function addInput(divName) {
		var authorFName = document.getElementById('authorFN').value;
		if(id > 1 && !verificareFilds(id)){
			return alert("Compleate all the authors!!!");
		} else if(authorFName == 'Unknown' || authorFName.replace(/\s/g,'') == ""){
			return alert("Please insert the first author to call for another");
		}else if(counter == limit){
			return alert("You have reached the limit of adding " + limit/2 + " inputs");
		}else{
			addFildsAuthors(counter, divName);
			counter++;
		}
	}
//verifica daca fildurile pentru autori de la al doilea pana la ultimul contin sau nu caractere..
//daca nu contin intorc fals si nu permit adaugarea altuia pana nu sunt complecate toate cele existente	
	function verificareFilds(counter){
		var index = 0;
		while(index < counter){
			var field1 = document.getElementById(index).value;
			if(field1.replace(/\s/g,'') == ""){
				return false;
			}else{
				index = index +2;
			}
		}
		return true;
	}