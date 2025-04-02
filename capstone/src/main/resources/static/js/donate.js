const api_key = 'pk_live_8e240e290c82f229069e4b7e67f2b278';
const cause = 'animals'
window.onload = function (){
    $.ajax({
        url: 'https://partners.every.org/v0.2/browse/'+ cause +'?take=20&apiKey=' + api_key,
        method: 'GET',
        success: function(result) {
            displayCards(result);
        }
    });
}

function displayCards(results) {
    for (var org of results.nonprofits) {
        let newCard =   '<div class="card">'
                    +       '<img class="card-img" src="'+ org.coverImageUrl +'"/>' //org's logo
                    +       '<h2>'+ org.name +'</h2>'
                    +        '<p>Tags: ';
        for (var i = 0; i < org.tags.length-1; i++) {
            newCard +=          org.tags[i]+', '
        }
        newCard     +=      org.tags[org.tags.length-1] + '</p>'
                    +       '<button onclick="showModal(\''+ org.slug +'\')">View Details</button>'
                    +   '</div>';

        document.getElementsByClassName("card-container")[0].innerHTML += newCard;
    }
}

function showModal(id) {
    $.ajax({
        url: 'https://partners.every.org/v0.2/nonprofit/' + id + '?apiKey=' + api_key,
        method: 'GET',
        data: {
            id: id
        },
        success: function(result) {
            var org = result.data.nonprofit;
            var tags = result.data.nonprofitTags;

            //fill modal
            document.getElementById('org-id').innerText=org.ein;
            document.getElementById('modal-title').innerText=org.name;
            document.getElementById('modal-description').innerText=org.description;
            document.getElementById('modal-url').href = org.websiteUrl;
            var causes = document.getElementById('modal-causes');
            causes.innerHTML = "";
            for (var i = 0; i < tags.length; i++) {
                var cause = document.createElement("li");
                cause.classList.add(tags[i].tagName);
                cause.innerText = tags[i].title;
                causes.appendChild(cause);
            }

            //display modal
            document.getElementById('modal-space').style.display='block';
        }
    });
}