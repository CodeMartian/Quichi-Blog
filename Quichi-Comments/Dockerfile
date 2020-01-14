FROM openjdk:13.0.1-jdk-oraclelinux7
WORKDIR /usr/src/app
COPY . .
EXPOSE 8081
RUN echo "Docker image successfully built."
CMD ./gradlew bootRun