FROM openjdk:8
MAINTAINER a.lorenteleal@gmail.com

ADD target/frontend-webapp-1.0-SNAPSHOT.jar frontend.jar

CMD ["java","-jar","shortener.jar","server","config.yml"]