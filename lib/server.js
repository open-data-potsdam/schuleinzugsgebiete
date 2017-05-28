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
const express = require('express');
const nunjucks = require('nunjucks');
const streets = require('./streets.js');
const schools = require('./schools.js');

const port = process.env.PORT;
const app = express();

app.use(express.static('public'));
nunjucks.configure('./templates', nunjucksOpts(app));

app.get('/', (req, res) => {
  const data = {
    schools: schools.all()
  };

  return res.render('home.html', data);
});

app.get('/strassen', (req, res) => {
  const name = req.query.name;
  const foundStreets = streets.searchByName(name);

  return res.render('streets.html', {streets: foundStreets, name: name});
});

app.get('/schulen/:schoolId', (req, res) => {
  const id = req.params.schoolId;
  const data = {
    school: schools.byId(id),
    streets: streets.findBySchool(id)
  }

  return res.render('school.html', data);
});

app.listen(port, '0.0.0.0', () => {
  console.log('Schuleinzugsgebiete app is listening on port ' + port);
});

function nunjucksOpts(app) {
  const opts = {
    autoescape: true,
    express: app
  };

  if (app.get('env') !== 'production') {
    opts.noCache = true;
  }

  return opts;
}
