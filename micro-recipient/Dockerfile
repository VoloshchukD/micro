FROM amazoncorretto:21-alpine
EXPOSE 8083
ADD build/libs/micro-recipient-0.0.1-SNAPSHOT.jar micro-recipient.jar
ENTRYPOINT ["java","-jar","micro-recipient.jar"]