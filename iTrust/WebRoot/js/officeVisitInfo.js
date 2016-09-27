var OfficeVisitInfo = window.OfficeVisitInfo || (function($){
	var regex = /^(?:0?[1-9]|1[0-2])\/(?:0?[1-9]|[1-2][0-9]|3[0-1])\/\d{4} (?:0?[0-9]|1[0-9]|2[0-4]):(?:[0-5][0-9]) (?:A|P)M$/i;
	var dob = $("#dateOfBirth").val();
	var $dateErrorLabel = $(".ov_error", "tr:has([id$='ovdate'])");
	var _validateDate = function(date) {
    	return regex.test(date);
    };
    var _getPatientAgeGroup = function(date) {
    	var age = (new Date(date) - dob) / 31536000000;	//Converting long to number of years
    	if (age < 3) {
    		return "baby";
    	} else if (age < 12) {
    		return "child";
    	} else {
    		return "adult";
    	}
    }
    var _displayMetrics = function() {
		$("tr:has(.healthmetric)").hide();
    	var date = $("[id$='ovdate']").val();
    	if (_validateDate(date)) {
	    	$("tr:has(.healthmetric-"+ _getPatientAgeGroup(date) +")").show();
	    	$dateErrorLabel.html("");
    	} else {
    		var errorMessage = "Date format must be M/d/yyyy hh:mm AM/PM";
    		$dateErrorLabel.html("<span class='iTrustError'>" + errorMessage + "</span>");
    	}
    }
    return {
    	displayMetrics: _displayMetrics
    }
})(jQuery);

$(function(){
	$("[id$='ovdate']").on("keyup change",OfficeVisitInfo.displayMetrics);
	OfficeVisitInfo.displayMetrics();
});