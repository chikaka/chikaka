var HTTP_GET  = "GET";
var HTTP_POST = "POST";

function initAuth(username, password){
    var header = "Basic " + $.base64.encode(username + ":" + password);
    document.cookie = "Authorization=" + header;
}

function getAuthCookie() {
   var cn = "Authorization=";
   var idx = document.cookie.indexOf(cn);

   if (idx != -1) {
       var end = document.cookie.indexOf(";", idx + 1);
       if (end == -1) end = document.cookie.length;
       return unescape(document.cookie.substring(idx + cn.length, end));
   } else {
       return "";
  }
}

function authenticatedAjax(url, metod, data, success, failure){
	$.ajax({
	    type: method,
	    url: url,
	    contentType: "application/json; charset=utf-8",
	    beforeSend: function(xhr) {
	        xhr.setRequestHeader("Authorization", getAuthCookie());
	    },
	    data: data,
	    dataType: "json",
	    success: success,
	    error: failure
	});
}

function listAllAccounts(){
	initAuth("administrator", "chikaka");
	url     = "http://localhost:8080/chikaka/rest/admin/accounts";
	data    = null;
	method  = HTTP_GET;
	success = function(response){
		accounts = "<ul>";
		for(var i = 0; i< response.length; i++){
			accounts +="<li>" + response[i].firstName + ", " + response[i].lastName + " -> " + response[i].email + "(" + response[i].userName + ")" +"</li>";
		}
		accounts +="</ul>";
		$("#container").html(accounts);
	};
	failure = function(reponse){
		alert("wa333");
	};
	authenticatedAjax(url, method, data, success, failure);
}