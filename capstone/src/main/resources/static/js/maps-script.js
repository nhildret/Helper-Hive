function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: {
      lat: 36.07185696781885,
      lng: -79.79195301493198,
    },
    zoom: 13,
    mapId: "dd4a1b0fa633e4e",
    mapTypeControl: false,
    fullscreenControl: false,
    streetViewControl: false,
  });

  // Name
  // Latitude, longitude
  // Image URL
  // scaledSize weidth, height
  // Organization ID that directs to organization page
  const organizationMarkers = [
    [
      // Shepherd's Center of Greensboro: 36.073416798874106, -79.79303636984241
      "Shepherd's Center of Greensboro",
      36.073416798874106,
      -79.79303636984241,
      "/images/marker_icons/queenbee_marker.PNG",
      38,
      38,
      3,
    ],
    [
      // The Green Team Helping Hands: 36.07426431307997, -79.78966365152392
      "The Green Team Helping Hands",
      36.07426431307997,
      -79.78966365152392,
      "/images/marker_icons/queenbee_marker.PNG",
      38,
      38,
      2,
    ],
    [
      // Greensboro Urban Ministry - Food Distribution Center: 36.063884680754484, -79.79415791197987
      "Greensboro Urban Ministry - Food Distribution Center",
      36.063884680754484,
      -79.79415791197987,
      "/images/marker_icons/cutebee_marker.PNG",
      38,
      38,
      1,
    ],
  ];

  for (let i = 0; i < organizationMarkers.length; i++) {
    const currMarker = organizationMarkers[i];
    const marker = new google.maps.Marker({
      position: { lat: currMarker[1], lng: currMarker[2] },
      map,
      title: currMarker[0],
      icon: {
        url: currMarker[3],
        scaledSize: new google.maps.Size(currMarker[4], currMarker[5]),
      },
      animation: google.maps.Animation.DROP,
    });

    // Create the content for the info window
    const infoWindowContent = `
      <div class="info-window">
        <h3>${currMarker[0]}</h3>
        <button class="view-organization-btn" onclick="window.location.href='/organizations/view/${currMarker[6]}'">View Organization</button>
      </div>
    `;

    const infowindow = new google.maps.InfoWindow({
      content: infoWindowContent,
    });

    marker.addListener("click", () => {
      infowindow.open(map, marker);
    });
  }
}