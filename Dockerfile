FROM clojure:lein-alpine

RUN addgroup -S schuleinzugsgebiete && adduser -S -G schuleinzugsgebiete schuleinzugsgebiete

WORKDIR /app

ADD . /app

RUN apk add --update nodejs

RUN npm install --unsafe-perm
RUN lein uberjar

USER schuleinzugsgebiete

CMD ["java", "-jar", "/app/target/schuleinzugsgebiete.jar"]
