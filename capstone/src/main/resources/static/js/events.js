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
        success: function(){
            displayCards();
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
        success: displayCards()
    })
}


function displayCards(results) {
    alert(results);

    eventContainer.innerHTML = "";
    //for result in results

    // eventContainer.innerHTML+= '<div th:each="event : ${event_list}" class="event">' 
    //                             + '<!-- Event Image or Placeholder -->'
    //                             + '<div th:if="${event.imagePath == null or event.imagePath.isEmpty()}" class="image-placeholder"></div>'
    //                             + '<img th:unless="${event.imagePath == null or event.imagePath.isEmpty()}"' 
    //                             + 'th:src="@{${event.imagePath}}"' 
    //                             + 'th:alt="${event.title}"' 
    //                             + 'class="event-img">'

    //                             + '<!-- Event Title -->'
    //                             + '<h2 th:text="${event.title}"></h2>'

    //                             + '<!-- Event Details -->'
    //                             + '<div class="event-details">'
    //                             +     '<p th:text="\'Location: \' + ${event.location}"></p>'
    //                             +     '<p th:text="\'Date: \' + ${event.eventDate}"></p>'
    //                             +     '<p th:text="\'Volunteer Hours: \' + ${event.volunteerHours}"></p>'
    //                             +     '<p class="Organization" th:text="\'Organization: \' + ${event.organization.organizationName}"></p>'
    //                             + '</div>'

    //                             + '<!-- View More Button -->'
    //                             + '<a class="btn-view-more" th:href="@{/events/view/{event_id}(event_id=${event.eventId})}">View More</a>'
    //                         + '</div>';

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




