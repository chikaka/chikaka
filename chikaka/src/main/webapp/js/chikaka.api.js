

var Chikaka = new function(){   
	var chk = this;
	var HTTP_GET  = "GET";
	var HTTP_POST = "POST";
	var HTTP_PUT  = "PUT";
	var HTTP_DELETE = "DELETE";
	this.BASE_URL = "http://localhost:8080/chikaka/"; 
	this.default_user = "guest";
	this.default_pwd = "guest";
	 
	
	this.get = function (url, complete) {
		this.authenticatedAjax(this.url(url), HTTP_GET, "", complete);
	} ;

	this.post = function (url, data, complete) { 
		this.authenticatedAjax(this.url(url), HTTP_POST, data, complete);
	}  ;

	this.remove = function (url, data, complete) { 
		this.authenticatedAjax(this.url(url), HTTP_DELETE, data, complete);
	}  ;

	this.put = function (url, data, complete) { 
		this.authenticatedAjax(this.url(url), HTTP_PUT, data, complete);
	} ;
	
	
	this.url = function (url) {
		return this.BASE_URL + url; 
	};
	this.alert = function(msg){
		alert(msg); 
	};
	this.initAuth =  function (username, password){ 
	    var header = "Basic " + $.base64.encode(username + ":" + password);
	    document.cookie = "Authorization=" + header;
	};

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
	};

	this.authenticatedAjax = function(url, method, data, complete){
		chk = this;
		$.ajax({
		    type: method,
		    url: url,
		    contentType: "application/json; charset=utf-8",
		    beforeSend: function(xhr) {
		        xhr.setRequestHeader("Authorization", chk.getAuthCookie());
		    },
		    data: data,
		    dataType: "json", 
		    complete: complete
		}); 
	};

	this.listAllAccounts = function (){
		initAuth("administrator", "chikaka");
		url     = "http://localhost:8080/chikaka/rest/admin/accounts";
		data    = null;
		method  = HTTP_GET;		 
		complete = function(jqXHR, textStatus){
			alert("wa333");
		};
		this.authenticatedAjax(url, method, data, complete);
	}  ;
	this.authLogout = function (){
		chk.initAuth (chk.default_user , chk.default_pwd ); 
		$(location).attr("href", "index.html"); 
	} ;
	
	if(!this.getAuthCookie()){ 
		this.initAuth (this.default_user , this.default_pwd ); 
	} ;
};
 
 
