FROM clojure:lein-alpine

WORKDIR /schuleinzugsgebiete

RUN addgroup -S schuleinzugsgebiete \
    && adduser -S -G schuleinzugsgebiete schuleinzugsgebiete \
    && apk add --no-cache nodejs

ADD ./package.json /schuleinzugsgebiete/package.json
RUN mkdir -p resources/schuleinzugsgebiete/public/vendor \
    && npm install --unsafe-perm \
    && npm run-script copy-deps

ADD ./project.clj /schuleinzugsgebiete/project.clj
RUN lein deps

ADD . /schuleinzugsgebiete
RUN npm run-script build-css \
    && npm run-script build-js \
    && lein uberjar

USER schuleinzugsgebiete

CMD ["java", "-jar", "/schuleinzugsgebiete/target/schuleinzugsgebiete.jar"]
