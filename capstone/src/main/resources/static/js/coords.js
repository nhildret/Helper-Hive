window.onload = function (){
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(getEvents);

        //change this to AJAX call
        window.location.replace(window.location.href + "/" + position.coords.latitude + "/" + position.coords.longitude);
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
}

function getEvents(position) {
    document.getElementById("lat-lon").innerHTML =  
    "Searching for organizations near lat: " + position.coords.latitude +
    " & lon: " + position.coords.longitude;
}