FROM maven AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package -DskipTests

FROM openjdk:11
COPY --from=build /usr/src/app/target/docker-jdbc-postgres.jar /usr/nix/docker/
COPY --from=build /usr/src/app/target/lib/*.jar /usr/nix/docker/lib/
WORKDIR /usr/nix/docker
CMD ["java", "-jar", "docker-jdbc-postgres.jar"]
