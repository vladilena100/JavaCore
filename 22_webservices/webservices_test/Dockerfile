FROM maven:3.8-openjdk-11 as webservices_test
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
CMD ["mvn", "site"]