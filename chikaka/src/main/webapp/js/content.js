
 
var chikaka = chikaka || new Chikaka();
$(document).ready(function() {
	//#join tab 
	chikaka.get("rest/user/games", gamesList);
 	 
}); 


function gamesList(response){ 
	html = "<table class='table table-bordered table-striped table-hover'>";
	html =	"<thead>";
	html =		"<tr>"; 
	html =			"<th>Nbr des NPCs</th>";
	html =			"<th>Nbr des Joueur</th>";
	html =			"<th>Crédit</th>";
	html =		"</tr>";
	html =	"</thead>";
	html =	"<tbody>";
	for(var i = 0; i< response.length; i++){
		html +="<tr>";
		html += "<td>" + response[i].aiPlayersCount +"</td>";
		html += "<td>" + response[i].humanplayersCount +"</td>"; 
		html += "<td>" + response[i].initialCredit +"</td>";
		html += "</tr>";
	}
	
	html =	"</tbody>";       
	html =	"</table>";   
	$("#join").html(html);
	
}