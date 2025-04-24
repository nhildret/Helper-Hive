currentURL = window.location.origin +'/events';
const eventContainer = document.getElementsByClassName("event-container")[0];
const filterBtn = document.getElementById("filterBtn");
var comingUp;
var causes;

function passWithCoords(){
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(queryWithCoords);
    } else {
        queryWithoutCoords(comingUp, causes)
        console.log("Geolocation is not supported by this browser.");
    }
}
function queryWithCoords(position) {
    //get coords
    var x = position.coords.latitude;
    var y = position.coords.longitude;

    //call DB
    $.ajax({
        url: currentURL + "/withCoords",
        data: {comingUp, causes, x, y},
        success: function(result){
            displayCards(result);
        },
        error: function() {
            alert("oh no");
        }
    });
}
function queryWithoutCoords() {
    $.ajax({
        url: currentURL + "/withoutCoords",
        data: {comingUp, causes},
        success: function(result){
            displayCards(result);
        },
        error: function() {
            alert("oh no");
        }
    })
}

function displayCards(result) {
    var events = [];

    for (let i = 0; i < result.length; i++) {
        events[i] = JSON.parse(result[i]);
    }

    //clear and repopulate event container
    eventContainer.innerHTML = "";
    for (let i = 0; i < events.length; i ++) {
        let eventHTML = "";
        eventHTML+= '<div class="event">';
        if (events[i].imagePath == null || events[i].imagePath == "") {
            eventHTML += '<div class="image-placeholder"></div>';
        } else {
            eventHTML += '<img src="' + events[i].imagePath + '"' 
                                        + 'alt="' + events[i].title + '"' 
                                        + 'class="event-img">';
        }
        eventHTML +=              '<h2>'+events[i].title+'</h2>'
                                + '<!-- Event Details -->'
                                + '<div class="event-details">'
                                +     '<p>Location: ' + events[i].location + '</p>'
                                +     '<p>Date: ' + events[i].eventDate.toString().substring(0,10) + '</p>'
                                +     '<p>Volunteer Hours: ' + events[i].volunteerHours + '</p>'
                                +     '<p class="Organization">Organization: ' + events[i].organization.organizationName + '</p>'
                                + '</div>'

                                + '<!-- View More Button -->'
                                + '<a class="btn-view-more" href="/events/view/' + events[i].eventId + '">View More</a>'
                            + '</div>';
        eventContainer.innerHTML += eventHTML;
    }

}


window.onload = function() {    
    
    filterBtn.addEventListener("click", filter);
    
    function filter() {
        let nearMe = document.getElementById("nearMe").checked;
        comingUp = document.getElementById("comingUp").checked;
        //causes
        let cause = document.getElementsByClassName("filterCheckbox");
        causes = [];
        let j = 0;
        for (let i = 0; i < cause.length; i++) {
            if (cause[i].checked) {
                causes[j] = cause[i].id;
                j++;
            }
        }
        if (causes.length < 1) {
            causes = "";
        }
        if (nearMe) {
            passWithCoords(comingUp, causes);
        } else {
            queryWithoutCoords(comingUp, causes);
        }
    }
}




