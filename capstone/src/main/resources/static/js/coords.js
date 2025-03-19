window.onload = function (){
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(getEntities);
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
}

// ajax call to get orgs
function getEntities(position) {
    $.ajax({
        url: window.location.href + "/get",
        method: 'GET',
        data: {
            lat: position.coords.latitude,
            lon: position.coords.longitude,
            pagenum: 1
        },
        success: function(result) {
            displayCards(result);
        }
    });
}

// use results to fill cards info
function displayCards(results) {
    for (var result of results) {
        const entity = JSON.parse(result);

        let newCard =   '<div class="card">'
                    +       '<img class="card-img" src="'+ entity.logo_url +'"/>' //org's logo
                    +       '<h2 onclick="document.getElementById(\'modal-space\').style.display=\'block\'">'+ entity.name +'</h2>'
                    +   '</div>';
        document.getElementsByClassName("card-container")[0].innerHTML += newCard;
    }
}

// function getEvents(position) {
//     document.getElementById("lat-lon").innerHTML =  
//     "Searching for organizations near lat: " + position.coords.latitude +
//     " & lon: " + position.coords.longitude;
// }