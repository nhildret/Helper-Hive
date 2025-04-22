const api_key = 'pk_live_8e240e290c82f229069e4b7e67f2b278';
const webhook = 'b2e68cd35c62cdfd105643be';
const page_url = "https%3A%2F%2fhelper-hive.onrender.com%2fdonate";
const cardContainer = document.getElementsByClassName("card-container")[0];
const filterBtn = document.getElementById("filterBtn");
var causes = document.getElementsByClassName("filterCheckbox");
var orgs = new Map();

filterBtn.addEventListener("click", async function (){
    orgs.clear();
    orgs = await getOrgs();
    console.log(orgs);
    displayCards();
});

async function getOrgs() {
    let newOrgs = new Map();
    for (let i = 0; i < causes.length; i++) {
        if (causes[i].checked) {
            await $.ajax({
                url: 'https://partners.every.org/v0.2/browse/'+ causes[i].id +'?take=20&apiKey=' + api_key,
        method: 'GET',
        success: function(result) {
                    for (var org of result.nonprofits) {
                        newOrgs.set(org.slug, org);
                    }
        }
    });
}
    }
    return newOrgs;
}

async function displayCards() {
    cardContainer.innerHTML = "";
    orgs.forEach((org, key) => {
        let newCard =   '<div class="card">'
                    +       '<img class="card-img" src="'+ org.coverImageUrl +'"/>' //org's logo
                    +       '<h2>'+ org.name +'</h2>'
                    +        '<p>Tags: ';
        for (var i = 0; i < org.tags.length-1; i++) {
            newCard +=          org.tags[i]+', '
        }
        newCard     +=      org.tags[org.tags.length-1] + '</p>'
                    +       '<button onclick="showModal(\''+ key +'\')">View Details</button>'
                    +   '</div>';

        cardContainer.innerHTML += newCard;
    });
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
            document.getElementById('donate').addEventListener('click', function() {
                window.open("https://www.every.org/" + org.primarySlug 
                                + "?success_url=" + page_url
                                + "&exit_url=" + page_url
                                + "&webhook_token=" + webhook
                                + "#donate"
                            , '_blank', 'noopener,noreferrer');
            });

            //display modal
            document.getElementById('modal-space').style.display='block';
        }
    });
}