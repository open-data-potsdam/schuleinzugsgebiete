FROM node:8-alpine

WORKDIR /schuleinzugsgebiete

RUN addgroup -S schuleinzugsgebiete \
    && adduser -S -G schuleinzugsgebiete schuleinzugsgebiete

ADD ./package.json /schuleinzugsgebiete/package.json
RUN npm install

ADD . /schuleinzugsgebiete
RUN npm run-script build-css \
    && npm run-script build-js \
    && npm run-script copy-vendor-deps

USER schuleinzugsgebiete

ENV NODE_ENV=production

CMD ["npm", "start"]
