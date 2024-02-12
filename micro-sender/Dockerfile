FROM amazoncorretto:21-alpine
EXPOSE 8081
ADD build/libs/micro-sender-0.0.1-SNAPSHOT.jar micro-sender.jar
ENTRYPOINT ["java","-jar","micro-sender.jar"]