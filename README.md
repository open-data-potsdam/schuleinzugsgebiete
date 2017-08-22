# schuleinzugsgebiete
School districts in Potsdam.

## Prerequisites
You can install [Node.js](https://nodejs.org) and [NPM](https://www.npmjs.com) directly, but the recommended way is to use [Docker Community Edition](https://store.docker.com/search?offering=community&type=edition).

## Usage
Get the source code

``` shell
git clone https://github.com/open-data-potsdam/schuleinzugsgebiete.git
cd schuleinzugsgebiete
```

Build your environment

``` shell
docker-compose build --pull
```

Run the application in development mode

``` shell
docker-compose up dev watch-css watch-js
```

Open the page from a browser under [localhost:3000](http://localhost:3000). All changes to files will automatically be applied. Just refresh your browser 😌.

## Update data
The data is loaded from [data/schools.csv](data/schools.csv). You can directly change the file.
