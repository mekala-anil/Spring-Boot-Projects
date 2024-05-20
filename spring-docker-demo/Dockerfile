
#FROM adoptopenjdk:17-jre-hotspot
#
#WORKDIR /app
#
#COPY target/my-spring-boot-app.jar /app
#
#CMD ["java", "-jar", "my-spring-boot-app.jar"]


FROM openjdk:17
WORKDIR /app
COPY target/sb_docker_app.jar /app
ENTRYPOINT ["java","-jar","sb_docker_app.jar"]