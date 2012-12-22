
 
var Chikaka = Chikaka || {};
var data = {};
$(document).ready(function() { 
	$("#signin-form").submit(function(){
		data = $(this).toObject();   
		Chikaka.post("rest/guest/login", JSON.stringify(data),  signInComplete);
		return false;
	});
});  

function signInComplete(jqXHR, textStatus){  
	switch (jqXHR.statusText){
		case "OK":
			Chikaka.initAuth(data.userName, data.password);
			$(location).attr("href", "join.html");
			break; 
		default:   
			html = '<div class="alert alert-error alert-block">';
			html+=	'<button type="button" class="close" data-dismiss="alert">&times;</button>';
			html+=  jqXHR.responseText;
			html+= '</div>';
			$(".alert-block").remove();
			$("#signin-form").prepend(html);
			$(".control-group").addClass("error"); 
			break;
	} 
}

 