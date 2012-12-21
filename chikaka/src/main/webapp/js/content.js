
 
var Chikaka = Chikaka || new Chikaka();
$(document).ready(function() {

	Chikaka.initAuth("emir", "wa333");
	//#adding game tab
	
	$("#new-game-form").submit(function(){
		var data = $(this).toObject();  
		Chikaka.post("rest/user/games", JSON.stringify(data),  gamesCreateComplete);
		return false;
	});
	
	//#join tab 
	Chikaka.get("rest/user/games", gamesListComplete);
 	 
}); 

 
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