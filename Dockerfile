FROM node:6-alpine

WORKDIR /schuleinzugsgebiete

RUN addgroup -S schuleinzugsgebiete \
    && adduser -S -G schuleinzugsgebiete schuleinzugsgebiete

ADD ./package.json /schuleinzugsgebiete/package.json
RUN npm install

ADD . /schuleinzugsgebiete
RUN npm run-script build-css \
    && npm run-script build-js

USER schuleinzugsgebiete

CMD ["npm", "start"]
