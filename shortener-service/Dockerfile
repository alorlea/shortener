FROM openjdk:8
MAINTAINER a.lorenteleal@gmail.com

ADD target/shortener-service-1.0-SNAPSHOT.jar shortener.jar
ADD config.yml config.yml

CMD ["java","-jar","shortener.jar","server","config.yml"]

