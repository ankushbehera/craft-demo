FROM maven:3.6-jdk-8
COPY target/mydata-*.jar /usr/app/craft-demo-app/mydata-app.jar
WORKDIR /usr/app/craft-demo-app
ENTRYPOINT ["java","-jar","mydata-app.jar"]