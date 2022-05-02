FROM maven AS hibernate
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package -DskipTests

FROM tomcat:9.0
COPY --from=hibernate /tmp/target/hibernate*.war $CATALINA_HOME/webapps/ROOT.war