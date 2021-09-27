FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app

RUN apk add --no-cache curl tar bash procps

# Downloading and installing Maven
# 1- Define a constant with the version of maven you want to install
ARG MAVEN_VERSION=3.6.3         

# 2- Define a constant with the working directory
ARG USER_HOME_DIR="/root"

ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

# 5- Create the directories, download maven, validate the download, install it, remove downloaded file and set links
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && echo "Downlaoding maven" \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  \
  && echo "Unziping maven" \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  \
  && echo "Cleaning and setting links" \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# 6- Define environmental variables required by Maven, like Maven_Home directory and where the maven repo is located
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
RUN mvn -N io.takari:maven:wrapper

FROM openjdk:8-jdk-alpine
WORKDIR /workspace/app
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests

EXPOSE 5000

#ADD --chown=root:root target/*.jar /workspace/app/
#RUN chmod 777 *.jar

#RUN mkdir -p target/ cp target/*.jar .
CMD ["java", "-jar", "target/pizzaservice-0.0.1-SNAPSHOT.jar"]
