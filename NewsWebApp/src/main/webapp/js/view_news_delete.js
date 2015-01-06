/**
 * JavaScript file for the delete form on the view-news.jsp.
 * Shows the confirmation message.
 */

$(document).ready(function(){

	var form = document.forms[0];
	$(form).submit(function(event) {
		 if(confirm($("#msg").text())){
			 return true; }
		 else{
			 event.preventDefault();
		 }
	});

});