FROM openjdk:11-jdk-slim
RUN useradd --user-group --system --create-home --no-log-init spring
USER spring
ARG JAR_FILE=build/libs/*
COPY ${JAR_FILE} io-rpg.jar
CMD java -Dspring.profiles.active=prod -Djava.security.egd=file:/dev/./urandom -Dserver.port=$PORT -jar /io-rpg.jar
