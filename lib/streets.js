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
const fs = require('fs');

const streets = JSON.parse(fs.readFileSync('data/streets.json'));
console.log(streets.length + ' streets loaded from file.');

module.exports.searchByName = (name) => {
  const result = streets.filter((street) => {
    return street.name.toLowerCase().includes(name.toLowerCase());
  });
  console.log(result);
  return result;
}

module.exports.findBySchool = (schoolId) => {
  return [];
}
