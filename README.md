# schuleinzugsgebiete
School districts in Potsdam.

## Prerequisites
You will need [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and the [Leiningen](https://leiningen.org) build tool.

## Usage
Get the source code

``` shell
git clone https://github.com/open-data-potsdam/schuleinzugsgebiete.git
cd schuleinzugsgebiete
```

Build the page

``` shell
lein build
```

The generated HTML can be found under `site`. Copy the content of the directory to your web server.

## Development
Start the Clojure REPL

``` shell
lein repl
```

From the REPL start the development server

``` clojure
(go)
```

After changing Clojure code reset the REPL

``` clojure
(reset)
```

Open the page from a browser under [localhost:8080](http://localhost:8080).
