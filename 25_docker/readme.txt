команда для запуски сборки:
docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3-jdk-11 mvn clean install
