
 
var Chikaka = Chikaka || {};
$(document).ready(function() { 
	$("#signin-form").submit(function(){
		var data = $(this).toObject();  
		Chikaka.initAuth (data.userName, data.userPassword);
		Chikaka.post("rest/user/game/login", null,  signInComplete);
		return false;
	});
}); 


function signInComplete(jqXHR, textStatus){ 
	switch (jqXHR.statusText) {
		case "OK":
			$(location).attr("href", "join.html");
			break; 
		default:  
			$(".control-group").addClass("error"); 
			break;
	} 
}

 