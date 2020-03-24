FROM adoptopenjdk/openjdk11:alpine
EXPOSE 8080
ADD build/libs/*.jar app.jar
CMD ["java","-jar","/app.jar"]