/**
 * Copyright 2017 Jan Ehrhardt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
const tileUrl = 'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}';

const tileOptions = {
  attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
  maxZoom: 18,
  retina: '@2x',
  detectRetina: true,
  id: 'mapbox.streets',
  accessToken: 'pk.eyJ1IjoiamVocmhhcmR0IiwiYSI6ImNqMXZ6M2UwbDAwMHAzMnFxYWdtenR1YmkifQ.O2P3qgUpCxbleEq2QU1uqg'
};

const mapElement = $('#district-map');

if(mapElement.length) {
  const districtMap = L.map('district-map');

  const positionData = mapElement.attr('data-position');
  if (typeof positionData !== 'undefined') {
    console.log(positionData);
    const coordinates = JSON.parse(positionData);
    districtMap.setView(coordinates, 16);
    L.marker(coordinates).addTo(districtMap);
  } else {
    districtMap.setView([52.395833, 13.061389], 13);
  }

  L.tileLayer(tileUrl, tileOptions).addTo(districtMap);
}
