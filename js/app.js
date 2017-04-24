const tileUrl = 'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}';

const tileOptions = {
  attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
  maxZoom: 18,
  retina: '@2x',
  detectRetina: true,
  id: 'mapbox.streets',
  accessToken: 'pk.eyJ1IjoiamVocmhhcmR0IiwiYSI6ImNqMXZ6M2UwbDAwMHAzMnFxYWdtenR1YmkifQ.O2P3qgUpCxbleEq2QU1uqg'
};

if($('#district-map').length) {
  const districtMap = L.map('district-map').setView([52.395833, 13.061389], 13);
  L.tileLayer(tileUrl, tileOptions).addTo(districtMap);
}
