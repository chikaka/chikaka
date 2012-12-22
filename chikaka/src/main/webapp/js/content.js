
 
var Chikaka = Chikaka || new Chikaka();
$(document).ready(function() { 
	//#adding game tab
	
	$("#logout").click(function() { 
		Chikaka.post("rest/user/game/logout", null,  logoutComplete); 
	});
	
	$("#new-game-form").submit(function(){
		var data = $(this).toObject();  
		Chikaka.post("rest/user/game", JSON.stringify(data),  gamesCreateComplete);
		return false;
	});
	
	//#join tab 
	Chikaka.get("rest/user/game", gamesListComplete);
 	 
}); 

function logoutComplete(jqXHR, textStatus){
	console.log(jqXHR);
	console.log(textStatus); 
	switch (jqXHR.statusText){
		case "OK":  
			Chikaka.authLogout();
			break; 
		default:    
			break;
	} 
}


function gamesCreateComplete(jqXHR, textStatus){
	document.location.reload(true);
}
 

function gamesListComplete(jqXHR, textStatus){  
	switch (textStatus) {
		case "success": 
			var response = eval(jqXHR.responseText);
			html = "<table class='table table-bordered table-striped table-hover'>";
			html+=	"<thead>";
			html+=		"<tr>"; 
			html+=			"<th>Nbr des NPCs</th>";
			html+=			"<th>Nbr des Joueur</th>";
			html+=			"<th>Crédit</th>";
			html+=			"<th>Play</th>";
			html+=		"</tr>";
			html+=	"</thead>";
			html+=	"<tbody>"; 
			$.each( response, function(i, game){
				html +="<tr>";
				html += "<td>" + game.aiPlayersCount +"</td>";
				html += "<td>" + game.humanplayersCount +"</td>"; 
				html += "<td>" + game.initialCredit +"</td>";
				html +=  '<td><a class="btn" href="game.html"><i class="icon-play-circle"></i></a></td>';
				html += "</tr>"; 
			});	 
			
			html +=	"</tbody>";       
			html +=	"</table>";   
			break;

		default:
			html = "" ;
			html += '<div class="alert alert-block">';
			html +=  '<button type="button" class="close" data-dismiss="alert">&times;</button>';
			html +=    jqXHR.responseText;
			html += '</div>';
			break;
		}

	$("#join").html(html);	 
} 