FROM clojure:lein-alpine

WORKDIR /schuleinzugsgebiete

RUN addgroup -S schuleinzugsgebiete \
    && adduser -S -G schuleinzugsgebiete schuleinzugsgebiete \
    && apk add --no-cache nodejs

ADD ./package.json /schuleinzugsgebiete/package.json

ADD ./project.clj /schuleinzugsgebiete/project.clj
RUN lein deps
RUN npm install --unsafe-perm

ADD . /schuleinzugsgebiete
RUN npm run-script build-css \
    && npm run-script build-js \
    && lein uberjar

USER schuleinzugsgebiete

CMD ["java", "-jar", "/schuleinzugsgebiete/target/schuleinzugsgebiete.jar"]
