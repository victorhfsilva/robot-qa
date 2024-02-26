# FROM node AS build

# WORKDIR /robot-qa-front-end

# COPY package.json /robot-qa-front-end/package.json
# COPY package-lock.json /robot-qa-front-end/package-lock.json

# RUN ["npm", "ci"]

# COPY . /robot-qa-front-end

# RUN ["npm", "run", "build"]

FROM nginx

COPY /robot-qa-front-end/dist /usr/share/nginx/html

EXPOSE 80