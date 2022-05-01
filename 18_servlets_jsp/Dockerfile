FROM maven AS servlet
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package -DskipTests

FROM tomcat:9.0
COPY --from=servlet /tmp/target/servlets*.war $CATALINA_HOME/webapps/ROOT.war
