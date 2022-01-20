FROM maven AS spring
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package -DskipTests

FROM tomcat:9.0
COPY --from=spring /tmp/target/spring*.war $CATALINA_HOME/webapps/ROOT.war
