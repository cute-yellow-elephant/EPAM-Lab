/**
 * JavaScript file for the form on the newslist.jsp
 * Checks, if any checkbox inputs have been deleted; shows confirmation message.
 */

$(document).ready(function(){

	  $("form").submit(function(event) {
		var mistake = $( "#mistake" );
  		mistake.hide();
	  	if($("input[name='selectedNews']:checked").length > 0){
	  		if(confirm($("#msg").text())){ return true; }
	  	}
	  	else{
		  	$( "#mistake" ).show()
	  	}
		event.preventDefault();
	  });

});