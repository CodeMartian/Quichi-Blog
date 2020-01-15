FROM openjdk:13.0.1-jdk-oraclelinux7
MAINTAINER quichi_blog.com
WORKDIR /usr/src/app
COPY . .
RUN echo "Docker image built successfully"
CMD ./gradlew bootRun
EXPOSE 8080
