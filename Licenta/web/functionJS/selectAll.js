function selectAll(){
//	alert("dfdf");
	if(document.getElementById("checkeAll").checked==true)
	  {
	   selectCheck(true);
	  }
	  else
	  {
	    selectCheck(false);
	  }
}
function selectCheck(obj)
{
	iteme = document.getElementsByName('checkbox');
	size = iteme.length;
  for(var k=0;k<size;k++)
  {
   
      iteme[k].checked=obj;
   
  }}
