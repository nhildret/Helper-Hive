currentURL = window.location.origin +'/events';
let form = document.getElementsByClassName('create-event-form')[0];

async function initGeo() {
    const {Geocoder} = await google.maps.importLibrary("geocoding");
    var geocoder = new google.maps.Geocoder();
    form.addEventListener("submit", function(submit){
        submit.preventDefault();

        var formData = new FormData(form);
        var event = {};

        formData.forEach((value, key) => {
            event[key] = value;
        });

        //get coords using geolocation api
        //format location for url
        let location = event['location'];
        try {
            geocoder.geocode({address: location}).then((result) => {
                let coords = result.results[0].geometry.location;
                let x = coords.lat();
                let y = coords.lng();
                
                event['x'] = x;
                event['y'] = y;

                
                if (window.location.href.startsWith(currentURL + "/view")){ //update existing Event
                    let eventId = window.location.href.substring((currentURL+"/view").length + 1);
                    alert(eventId);
                    $.ajax({
                        url: currentURL + '/edit',
                        method: 'PUT',
                        data: { 
                            eventId: eventId,
                            causeId: event["causeId"], 
                            eventDate: event["eventDate"], 
                            imagePath: event["imagePath"], 
                            organizationId: event['organizationId'],
                            location: event["location"],
                            x: event["x"],
                            y: event["y"],
                            title: event["title"],
                            volunteerHours: event["volunteerHours"] 
                        },
                        success: window.open(currentURL +'/all', '_self', 'noopener,noreferrer') //redirect to events page
                    });
                } else { //create new Event
                    $.ajax({
                        url: currentURL + '/new',
                        method: 'POST',
                        data: { 
                            causeId: event["causeId"], 
                            eventDate: event["eventDate"], 
                            imagePath: event["imagePath"], 
                            organizationId: event['organizationId'],
                            location: event["location"],
                            x: event["x"],
                            y: event["y"],
                            title: event["title"],
                            volunteerHours: event["volunteerHours"] 
                        },
                        success: window.open(currentURL +'/all', '_self', 'noopener,noreferrer') //redirect to events page
                    });
                }
            });
        } catch (ex) {
            console.log("geocoder ran into an error:", ex.printStackTrace());
        }
    });

}