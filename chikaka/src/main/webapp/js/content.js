
 
var chikaka = chikaka || new Chikaka();
$(document).ready(function() {

	chikaka.initAuth("emir", "wa333");
	//#adding game tab
	
	$("#new-game-form").submit(function(){
		var data = $(this).toObject(); 
		console.log(JSON.stringify(data));
		chikaka.post("rest/user/games", JSON.stringify(data),  gamesCreateSucceded , gamesCreateFailed)
		return false;
	});
	
	//#join tab 
	chikaka.get("rest/user/games", gamesList, defaultFailure);
 	 
}); 


function gamesCreateSucceded(response){  
	console.log(response);
}
function gamesCreateFailed(response){  
	console.log(response);
}


function gamesList(response){ 
	html = "<table class='table table-bordered table-striped table-hover'>";
	html+=	"<thead>";
	html+=		"<tr>"; 
	html+=			"<th>Nbr des NPCs</th>";
	html+=			"<th>Nbr des Joueur</th>";
	html+=			"<th>Crédit</th>";
	html+=		"</tr>";
	html+=	"</thead>";
	html+=	"<tbody>";
	
	$.each( response, function(i, game){
		html +="<tr>";
		html += "<td>" + game.aiPlayersCount +"</td>";
		html += "<td>" + game.humanplayersCount +"</td>"; 
		html += "<td>" + game.initialCredit +"</td>";
		html += "</tr>"; 
	});	 
	
	html +=	"</tbody>";       
	html +=	"</table>";   
	$("#join").html(html);	 
}
function defaultFailure(rjqXHR, textStatus, errorThrown){ 
	html = "" ;
	html += '<div class="alert alert-block">';
	html +=  '<button type="button" class="close" data-dismiss="alert">&times;</button>';
	html +=    rjqXHR.responseText;
	html += '</div>';
	$("#join").html(html); 
}