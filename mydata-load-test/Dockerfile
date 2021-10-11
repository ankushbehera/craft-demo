FROM maven:3.6-jdk-8
COPY pom.xml /usr/test/craft-demo-load-test/pom.xml
COPY src/ /usr/test/craft-demo-load-test/src/
WORKDIR /usr/test/craft-demo-load-test
ENTRYPOINT ["mvn","clean","install"]