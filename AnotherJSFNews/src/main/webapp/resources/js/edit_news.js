/**
 * 
 * 	Validating date on the edit-news.jsp.
 * 
 */

$(document).ready(function(){
	$(".error").hide();
	var form = $('#save_form');
	
	$(form).submit(function(event) {
		$(".error").hide();
		
		var title = $('#save_form\\:news_title').val();
		var brief = $('#save_form\\:news_brief').val();
		var content = $('#save_form\\:news_content').val();
		var dateString=$('#save_form\\:news_date').val();	
		var mistake_counter = 0;
		
		if(typeof title === "undefined" || title.length === 0){
			showMistake($("#mistake_title_required"));
			mistake_counter++;
		}
		else if(title.length > 100){
			showMistake($("#mistake_title_wrong"));
			mistake_counter++;
		}
		
		if(typeof brief === "undefined" || brief.length === 0){
			showMistake($("#mistake_brief_required"));
			mistake_counter++;
		}
		else if(brief.length > 500){
			showMistake($("#mistake_brief_wrong"));
			mistake_counter++;
		}
		
		if(typeof content === "undefined" || content.length === 0){
			showMistake($("#mistake_content_required"));
			mistake_counter++;
		}
		else if(content.length > 2048){
			showMistake($("#mistake_content_wrong"));
			mistake_counter++;
		}		
		
		var changedDate = validateDate(dateString);
		console.log(changedDate);
		if(typeof changedDate === "undefined"){ 
			mistake_counter++; 
		}
		
		if(mistake_counter > 0){ 
			event.preventDefault();
			console.log("mistakes! " + mistake_counter);	 
			return false;
		}
		else{ 
			/*$('#save_form\\:news_date').val(changedDate);*/
			return true; 
		}
		
	});
});

function validateDate(dateString)
{
	var locale = $('#lang').text();
	console.log(locale);
	var pattern = /^(\d{2})(\/)(\d{2})(\/)(\d{4})$/;
	if(locale == "ru_RU"){	
		var dateArray = dateString.match(pattern);
		if(dateArray != null) {
			return validateDateParts(dateArray[1], dateArray[3], dateArray[5]);	
		}
		else{
			showMistake($("#mistake_format"));
		}
	}
	else if(locale == "en_US"){
		var dateArray = dateString.match(pattern);
		if(dateArray != null) {
			return validateDateParts(dateArray[3], dateArray[1], dateArray[5]);	
		}
		else{
			showMistake($("#mistake_format"));
		}
	}
	return undefined;	
}

function validateDateParts(dtDay, dtMonth, dtYear){
	if (dtMonth < 1 || dtMonth > 12) {
		showMistake($("#mistake_month"));
		return false;
	}
	else if (dtDay < 1 || dtDay> 31) {
		showMistake($("#mistake_day"));
		return false;
	}
	else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay == 31) {
		showMistake($("#mistake_31day_absence"));
		return false;
	}
	else if (dtMonth == 2){
	     var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
	     if (dtDay> 29 || (dtDay ==29 && !isleap)){
	    	 showMistake($("#mistake_leap_year"));
	    	 return false;
	     }
	}
	var changedDateValue;
	if(validateTimeLimits(new Date(dtYear, dtMonth-1, dtDay, 0, 0, 0))){
		/*changedDateValue = dtYear + '-' + (dtMonth) + '-' + dtDay;*/
		changedDateValue = dtMonth + '/' + dtDay + '/' + dtYear;
	}
	return changedDateValue;
}

function validateTimeLimits(enteredDate){
	var currentDate = new Date();
	var lowerLimitDate = new Date("January 1, 1800 00:00:00");
	if(enteredDate.getTime() > currentDate.getTime() || enteredDate.getTime() < lowerLimitDate.getTime()){
		showMistake($("#mistake_timelimit"));
		return false;
	}
	return true;
}

function showMistake(mistake){
	mistake.show();
}




