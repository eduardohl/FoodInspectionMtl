/* FIM utils */
var FIM = {
	map : null,
	markersArray : [],
	infoWindowArray : [],
	
	utils : {
		clearOverlays : function () {
			FIM.utils.clearMarkers();
			FIM.utils.clearInfoWindows();
		},
		clearInfoWindows : function () {
			for (var i = 0; i < FIM.infoWindowArray.length; i++) {
				FIM.infoWindowArray[i].close();
			}
			FIM.infoWindowArray.length = 0;
		},
		clearMarkers : function () {
			for (var i = 0; i < FIM.markersArray.length; i++) {
				FIM.markersArray[i].setMap(null);
			}
			FIM.markersArray.length = 0;
		},
		isValidFilterInputs : function(data) {
			if ($("#filter-form #startDate").val() == ""  
				|| $("#filter-form #endDate").val() == "" ) {
				return false;
			}
			return true;
		}
	},
	
	ajax : {
		populateMap : function() {
			FIM.utils.clearOverlays();
			data = $("#filter-form").serialize();
			
			if(!FIM.utils.isValidFilterInputs(data)){
				alert("Please select a start and end date");
			}
			
			//Get inspected places
			$.getJSON("map/ajax/inspected_places", data, function(result) {
				$.each(result, function(key, val) {
					//Add a marker for each place
					var latLng = new google.maps.LatLng(val.latitude,val.longitude);

					var marker = new google.maps.Marker({
						map : FIM.map,
						position : latLng,
						title: val.establishment
					});
					
					FIM.markersArray.push(marker);
					
					google.maps.event.addListener(marker,"click",function(){
						FIM.utils.clearInfoWindows();
						var infowindow = new google.maps.InfoWindow();
						
						infowindow.setContent(
								"<div class='establishment-label'>" + 
								    "<span><b>" + val.establishment + "</b></span><br/>" +
									"<span>Owner: " + val.owner + "</span><br/>" +
									"<span>Category: " + val.category + "</span><br/>" +
									"<span>Address: " + val.address + " " + val.city +"</span><br/>" +
									"<span>Date: " + val.infractionDate + "</span><br/>" +
									"<span>Offense: " + val.description + "</span><br/>" +
								"</div>");
						infowindow.open(FIM.map,marker);
						FIM.infoWindowArray.push(infowindow);
					});
				});
			});
		}
	}
};

/* Initialize Page */
$(document).ready(function() {
	// Datepicker fall back
	var i = document.createElement("input");
	i.setAttribute("type", "date");
	if (i.type == "text") {
		$(".date-picker").datepicker({ dateFormat: 'yy-mm-dd' });
	}

	// Bindings
	google.maps.event.addDomListener(window, "load", FIM.initializeMap());
	DOMEvents.onUpdateMapButtonClicked(FIM.ajax.populateMap);
	DOMEvents.onWhatIsThisLinkClicked(DOMEvents.showOverlay);
	DOMEvents.onOverlayCloseLinkClicked(DOMEvents.hideOverlay);
});

/* Initialize map */
FIM.initializeMap = function initialize() {
	var mapOptions = {
		//Centralize on Montreal
		center : new google.maps.LatLng(45.560945, -73.680000),
		zoom : 10,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	FIM.map = new google.maps.Map(document.getElementById("map-canvas"),
			mapOptions);
};

/* Segregated DOM events */
var DOMEvents = {
	onUpdateMapButtonClicked : function(callback) {
		$("#filter #update-map-button").on("click", callback);
	},
	onWhatIsThisLinkClicked: function(callback) {
		$("#footer #what-is-this").on("click", callback);
	},
	onOverlayCloseLinkClicked: function(callback) {
		$("#overlay #overlay-close").on("click", callback);
	},
	showOverlay : function() {
		$("#overlay").show();
	},
	hideOverlay : function() {
		$("#overlay").hide();
	} 
	
};

