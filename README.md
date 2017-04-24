# schuleinzugsgebiete
School districts in Potsdam.

## Prerequisites
You will need [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and the [Leiningen](https://leiningen.org) build tool as well as [Node.js](https://nodejs.org) and [NPM](https://www.npmjs.com).

## Usage
Get the source code

``` shell
git clone https://github.com/open-data-potsdam/schuleinzugsgebiete.git
cd schuleinzugsgebiete
```

Build the application

``` shell
npm install
lein uberjar
```

The whole application can be found in `target/schuleinzugsgebiete.jar`. You can start it via

``` shell
java -jar target/schuleinzugsgebiete.jar
```

## Development
Start the Clojure REPL

``` shell
lein repl
```

From the REPL start the development mode including the server

``` clojure
(dev)
```

After changing Clojure code reset the REPL

``` clojure
(reset)
```

Open the page from a browser under [localhost:3000](http://localhost:3000).
