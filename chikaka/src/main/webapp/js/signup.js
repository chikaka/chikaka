
 
var Chikaka = Chikaka || {};
$(document).ready(function() { 
	$("#signup-form").submit(function(){
		var data = $(this).toObject();    
		Chikaka.post("rest/guest/signup", JSON.stringify(data),  signUpComplete)
		return false;
	}); 
 	 
}); 


function signUpComplete(jqXHR, textStatus){
	console.log(jqXHR);
	console.log(textStatus);
	//document.location.reload(true);
}

 