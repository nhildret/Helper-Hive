



// Pledge API calls & functions
// Rework this for events instead of donations



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
        var id = entity.id;
        var name = entity.name;
        // var mission = entity.mission;
        var logo = entity.logo_url;
        

        let newCard =   '<div class="card">'
                    +       '<img class="card-img" src="'+ logo +'"/>' //org's logo
                    +       '<h2>'+ name +'</h2>'
                    +       '<button onclick="showModal(\''+ id +'\')">View Details</button>'
                    +   '</div>';
        document.getElementsByClassName("card-container")[0].innerHTML += newCard;
    }
}