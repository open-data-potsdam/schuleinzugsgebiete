FROM clojure:lein-alpine

WORKDIR /app

ADD . /app

RUN apk add --update nodejs

RUN npm install --unsafe-perm
RUN lein uberjar

ENTRYPOINT ["java", "-jar", "target/schuleinzugsgebiete.jar"]
