function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: {
      lat: 36.07185696781885,
      lng: -79.79195301493198,
    },
    zoom: 14,
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
    [
      "The Salvation Army",
      36.06159778201105,
      -79.798963667305,
      "/images/marker_icons/cutebee_marker.PNG",
      38,
      38,
      4,
    ],
    [
      "The Servant Center, Inc.",
      36.05452027449269,
      -79.81089413357438,
      "/images/marker_icons/cutebee_marker.PNG",
      38,
      38,
      5,
    ],
    [
      "The Interactive Resource Center",
      36.0715884733369,
      -79.78420078990914,
      "/images/marker_icons/queenbee_marker.PNG",
      38,
      38,
      6,
    ],
    [
      "One Step Further,Inc.",
      36.06798087006297,
      -79.79604542422747,
      "/images/marker_icons/queenbee_marker.PNG",
      38,
      38,
      7,
    ],
    [
      "Spartan Open Pantry (UNCG Student/Staff Pantry)",
      36.06898251693565,
      -79.8114895839907,
      "/images/marker_icons/cutebee_marker.PNG",
      38,
      38,
      8,
    ],
    [
      "Children's Hope Alliance",
      36.07518507191289,
      -79.79605883457108,
      "/images/marker_icons/cutebee_marker.PNG",
      38,
      38,
      9,
    ],
    [
      "Greensboro Beautiful",
      36.070710436152545,
      -79.79125231568834,
      "/images/marker_icons/cutebee_marker.PNG",
      38,
      38,
      10,
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