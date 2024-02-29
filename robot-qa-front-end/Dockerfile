FROM node AS build

WORKDIR /robot-qa-front-end

COPY package.json .
COPY package-lock.json .

RUN ["npm", "ci"]

COPY . .

RUN ["npm", "run", "build"]

FROM nginx

COPY --from=build /robot-qa-front-end/dist /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d

EXPOSE 80