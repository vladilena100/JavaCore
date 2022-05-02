FROM maven as webservices
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package

FROM tomcat:9.0
COPY --from=webservices /tmp/target/webservices*.war $CATALINA_HOME/webapps/ROOT.war