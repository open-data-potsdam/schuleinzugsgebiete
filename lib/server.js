const express = require('express');
const nunjucks = require('nunjucks');

const port = process.env.PORT;
const app = express();

app.use(express.static('public'));
nunjucks.configure('./templates', nunjucksOpts(app));

app.get('/', (req, res) => {
  return res.render('home.html');
});

app.get('/schulen/:schoolId', (req, res) => {
  const id = req.params.schoolId;
  const school = {
    id: id,
    name: "Foo"
  };
  return res.render('school.html', {school: school});
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
