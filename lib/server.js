const express = require('express');

const port = process.env.PORT;
const app = express();

app.get('/', (req, res) => {
  res.send('Hello Jan!');
});

app.listen(port, '0.0.0.0', () => {
  console.log('Schuleinzugsgebiete app is listening on port ' + port);
});
