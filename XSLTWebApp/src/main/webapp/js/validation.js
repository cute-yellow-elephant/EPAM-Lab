/**
 * 
 * 	Validating date on the edit-news.jsp.
 * 
 */

$(document).ready(function(){
	
	$(".error").hide();
	var form = $('#add-product');
	
	$(form).submit(function(event) {
		
		$(".error").hide();
		
		var name = $('input[name="name"]').val();
		var provider = $('input[name="provider"]').val();
		var model = $('input[name="model"]').val();
		var date_of_issue = $('input[name="date-of-issue"]').val();	
		var color = $('input[name="color"]').val();
		var price = $('input[name="price"]').val();
		var not_in_stock = $('#not-in-stock').is(':checked');
		
		var mistake_counter = 0;
		
		if(typeof name === "undefined" || name.length === 0){
			showMistake($('#name_error'), "The name is required.");
			mistake_counter++;
		}
		
		if(typeof provider === "undefined" || provider.length === 0){
			showMistake($('#provider_error'), "The provider is required.");
			mistake_counter++;
		}
		
		if(typeof model === "undefined" || model.length === 0){
			showMistake($('#model_error'), "The model is required");
			mistake_counter++;
		}
		else if(!validateModel(model)){
			showMistake($('#model_error'), "The price should consist of 2 letters and 3 digits, eg. AP123.");
			mistake_counter++;
		}	
		
		if(typeof date_of_issue === "undefined" || date_of_issue.length === 0){
			showMistake($('#date_of_issue_error'), "The date of issue is required");
			mistake_counter++;
		}
		else if(!validateDate(date_of_issue)){
			mistake_counter++;
		}	
		
		if(typeof color === "undefined" || color.length === 0){
			showMistake($('#color_error'), "The color is required.");
			mistake_counter++;
		}
		
		if(!not_in_stock){
			console.log("not in stock!");
			if(typeof price === "undefined" || price.length === 0){
				showMistake($('#price_error'), "The price is required.");
				mistake_counter++;
			}
			else if(!validatePrice(price)){
				showMistake($('#price_error'), "The price should be a decimal number with 2 digits after the dot.");
				mistake_counter++;
			}
		}
		
		if(mistake_counter > 0){ 
			event.preventDefault();
			console.log("mistakes! " + mistake_counter);	 
			return false;
		}
		else{ 
			return true; 
		}
		
	});
});

function validateModel(modelString){
	var pattern = /^([a-zA-Z]{2})([0-9]{3})$/;
	var modelArray = modelString.match(pattern);
	return (modelArray != null) ? true : false;
}

function validatePrice(priceString){
	var pattern = /^[0-9]+\.[0-9]{2}$/;
	var priceArray = priceString.match(pattern);
	return (priceArray != null) ? true : false;
}

function validateDate(dateString)
{
	var pattern = /^(\d{2})(\-)(\d{2})(\-)(\d{4})$/;
	var dateArray = dateString.match(pattern);
	if(dateArray != null) {
		return validateDateParts(dateArray[1], dateArray[3], dateArray[5]);	
	}
	else{
		showMistake($('#date_of_issue_error'), "Date format is not valid. The valid date example is: 01-01-1951, where the right order is day-month-year.");
		return false;
	}
}

function validateDateParts(dtDay, dtMonth, dtYear){
	if (dtMonth < 1 || dtMonth > 12) {
		showMistake($('#date_of_issue_error'), "Valid month numbers are from 1 to 12.");
		return false;
	}
	else if (dtDay < 1 || dtDay> 31) {
		showMistake($('#date_of_issue_error'), "Valid day numbers are from 1 to 31.");
		return false;
	}
	else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay == 31) {
		showMistake($('#date_of_issue_error'), "No 31st day in the selected month.");
		return false;
	}
	else if (dtMonth == 2){
	     var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
	     if (dtDay> 29 || (dtDay ==29 && !isleap)){
	    	 showMistake($('#date_of_issue_error'), "Leap year problems.");
	    	 return false;
	     }
	}
	return validateTimeLimits(new Date(dtYear, dtMonth-1, dtDay, 0, 0, 0));
}

function validateTimeLimits(enteredDate){
	var currentDate = new Date();
	var lowerLimitDate = new Date("January 1, 1800 00:00:00");
	if(enteredDate.getTime() > currentDate.getTime() || enteredDate.getTime() < lowerLimitDate.getTime()){
		showMistake($('#date_of_issue_error'), "The date should be in the period from 01/01/1800 to the current date.");
		return false;
	}
	return true;
}

function showMistake(container, message){
	container.text(message);
	container.show();
}

jQuery(function($) {
    $('#not-in-stock').click(function() {
        var cb = $('#not-in-stock').is(':checked');
        $('input[name="price"]').prop('disabled', cb);    
        $('input[name="price"]').val('', cb);
        $('#price_error').hide(cb);
    });
});




