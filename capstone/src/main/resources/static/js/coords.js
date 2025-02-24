function getCoords() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(getEvents);
        return position.coords.latitude + position.coords.longitude;
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
}

function getEvents(position) {
    document.getElementById("lat-lon").innerHTML =  
    "Searching for organizations near lat: " + position.coords.latitude +
    " & lon: " + position.coords.longitude;

    //call controller function that calls python method for pledgeAPI
} 