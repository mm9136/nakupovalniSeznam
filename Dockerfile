FROM adoptopenjdk:8-jre-hotspot
RUN mkdir /opt/app
COPY ./api/target/*.jar /opt/app/api.jar
CMD ["java", "-jar", "/opt/app/api.jar"]
#FROM adoptopenjdk:8-jre-hotspot
#
#RUN mkdir /app
#
#WORKDIR /app
#
#ADD ./api/target/api-1.0.0-SNAPSHOT.jar /app
#
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar","api-1.0.0-SNAPSHOT.jar"]


