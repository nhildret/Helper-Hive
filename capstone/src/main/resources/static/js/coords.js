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
// id + '\',\'' + name + '\',\'' + mission
function showModal(id) {
    $.ajax({
        url: window.location.href + "/getById",
        method: 'GET',
        data: {
            id: id
        },
        success: function(result) {
            const entity = JSON.parse(result);

            //fill modal
            document.getElementById('org-id').innerText=entity.id;
            document.getElementById('modal-title').innerText=entity.name;
            document.getElementById('modal-description').innerText=entity.mission;

            //display modal
            document.getElementById('modal-space').style.display='block';
        }
    });
}

// function showModal(result) {

//     //fill modal
//     document.getElementById('org-id').innerText=entity.id;
//     document.getElementById('modal-title').innerText=entity.name;
//     document.getElementById('modal-description').innerText=entity.mission;

//     //display modal
//     document.getElementById('modal-space').style.display='block';
// }

// function showModal(id, name, mission) {
//     //fill modal
//     document.getElementById('org-id').innerText=id;
//     document.getElementById('modal-title').innerText=name;
//     document.getElementById('modal-description').innerText=mission;

//     //display modal
//     document.getElementById('modal-space').style.display='block';
// }

// function getEvents(position) {
//     document.getElementById("lat-lon").innerHTML =  
//     "Searching for organizations near lat: " + position.coords.latitude +
//     " & lon: " + position.coords.longitude;
// }


/* 
<h1 th:text="${orgs.get(0).name}"></h1>
<p th:text="${orgs.get(0).mission}"></p>
*/