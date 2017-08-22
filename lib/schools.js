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
const csv = require('fast-csv');

const schools = [];

const stream = fs.createReadStream("data/schools.csv");
const options = {
  objectMode: true,
  headers: true
};

const csvStream = csv(options)
    .on('data', (data) => {
         schools.push(data);
    })
    .on('end', () => {
         console.log(schools.length + ' schools loaded from file.');
    });

stream.pipe(csvStream);

module.exports.all = () => {
  return schools;
}

module.exports.byId = (schoolId) => {
  return schools.find((school) => {
    return school.schoolId === schoolId;
  });
}
