

function Chikaka (){   
	var HTTP_GET  = "GET";
	var HTTP_POST = "POST";
	var HTTP_PUT  = "PUT";
	var HTTP_DELETE = "DELETE";
	this.BASE_URL = "http://localhost:8080/chikaka/"; 
	this.default_user = "guest";
	this.default_pwd = "guest";
	
	
	this.get = function (url, success, failure) { 
		initAuth("emir", "wa333");
		this.AuthenticatedAjax(this.url(url), HTTP_GET, "", success, failure);
	} 

	this.post = function (url, data, success, failure) { 
		this.AuthenticatedAjax(this.url(url), HTTP_POST, data, success, failure);
	}  

	this.remove = function (url, data, success, failure) { 
		this.AuthenticatedAjax(this.url(url), HTTP_DELETE, data, success, failure);
	}  

	this.put = function (url, data, success, failure) { 
		this.AuthenticatedAjax(this.url(url), HTTP_PUT, data, success, failure);
	} 
	
	
	this.url = function (url) {
		return this.BASE_URL + url; 
	} 
	this.alert = function(msg){
		alert(msg); 
	}
	this.initAuth =  function (username, password){ 
	    var header = "Basic " + $.base64.encode(username + ":" + password);
	    document.cookie = "Authorization=" + header;
	}

	this.getAuthCookie = function () {
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

	this.authenticatedAjax = function(url, metod, data, success, failure){
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

	this.listAllAccounts = function (){
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
};
 
