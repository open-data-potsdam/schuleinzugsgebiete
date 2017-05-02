FROM clojure:lein-alpine

RUN addgroup -S schuleinzugsgebiete && adduser -S -G schuleinzugsgebiete schuleinzugsgebiete

WORKDIR /app

ADD . /app

RUN apk add --update nodejs && rm -rf /var/cache/apk/*

RUN npm install --unsafe-perm
RUN npm run-script build
RUN lein uberjar

USER schuleinzugsgebiete

CMD ["java", "-jar", "/app/target/schuleinzugsgebiete.jar"]
