FROM node:alpine as build

RUN mkdir /app

WORKDIR /app

COPY . /app

RUN npm install
RUN npm run build

FROM nginx:latest

COPY ./nginx/nginx.conf ./etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/hospital-management/browser/ /usr/share/nginx/html/
EXPOSE 80


CMD ["nginx", "-g", "daemon off;"]