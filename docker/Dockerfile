# Build stage
FROM maven:3.9-eclipse-temurin-8 AS builder

ENV TZ=Europe/Athens

WORKDIR /build

COPY .git ./.git

COPY pom.xml .
# RUN mvn dependency:go-offline -B

COPY src ./src

# replace local development specific values with docker build specific values
RUN sed -i 's|^[[:space:]]*default_folder[[:space:]]*=.*$|default_folder=/app/uploads|' ./src/main/resources/config/config.properties
RUN sed -i 's|^[[:space:]]*priorities_xml[[:space:]]*=.*$|priorities_xml=/usr/local/tomcat/webapps/RDFV/WEB-INF/classes/config/properties.xml|' ./src/main/resources/config/config.properties


RUN mvn clean package -DskipTests


# Runtime stage
FROM tomcat:9-jdk8

ENV TZ=Europe/Athens

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=builder /build/target/*.war /tmp/RDFV.war

RUN mkdir /usr/local/tomcat/webapps/RDFV \
    && cd /usr/local/tomcat/webapps/RDFV \
    && jar -xf /tmp/RDFV.war \
    && rm /tmp/RDFV.war


# create a copy of default-config files that will be used in case config folder is mapped to a volume.
# each file that does not exist will be copied to the mapped volume from this default-config copy folder via entrypoint.sh
RUN mkdir -p /app/default-config/

COPY --from=builder /build/src/main/resources/config/* /app/default-config/


COPY docker/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 8080


CMD ["/entrypoint.sh"]
