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

        console.log(event);

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
            });
        } catch (ex) {
            console.log("geocoder ran into an error:", ex.printStackTrace());
        }
    });

}
    
    




    // let location = event['location'].split(' ').join('%20');
    // $.ajax({
    //     url:'https://maps.googleapis.com/maps/api/geocode/json?address=' + location + '&key=AIzaSyDZjUdpI48aTp9oQbMHfHEVdG1ih8ghyo0',
    //     success: function(result) {
    //         console.log("Coords acquired.");
    //         let coords = result.results.geometry.location;
    //         let x = coords.lat;
    //         let y = coords.lng;
            
    //         event['x'] = x;
    //         event['y'] = y;
    //         $.ajax({
    //             url: currentURL + '/new',
    //             method: 'POST',
    //             data: { event: event, organizationId: event['organizationId'] },
    //             success: alert("event created")
    //         });
    //         window.open(currentURL +'/all', '_self', 'noopener,noreferrer'); //redirect to events page
    //     },
    //     error: function(err) {
    //         alert("Oof: " + err.printStackTrace());
    //     }
    // });

    //call controller newEvent function method='POST'

function newEvent(e) {
    // get coords from address
    console.log(e);

    //call rest controller newevent function
    $.ajax({
        url:currentURL + 'https://maps.googleapis.com/maps/api/geocode/json?address=',
        data:{},
        method: 'POST',
        success: function(result) {
            console.log("Event created successfully.");
            //window.open(currentURL +'/all', '_self', 'noopener,noreferrer'); //redirect to events page
        },
        error: function(err) {
            alert("Event was unable to be created. " + err.printStackTrace());
        }
    });
}