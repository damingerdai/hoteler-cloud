FROM maven:3.9.8-amazoncorretto-21-debian AS back-build

WORKDIR app
#COPY pom.xml /app
#RUN mvn clean install -Dmaven.test.skip=true
#
#COPY common /app/common
#COPY auth /app/auth
#COPY workflow /app/workflow
#COPY orchestration /app/orchestration
#RUN mvn package

COPY . /app/

RUN mvn clean install package

FROM openjdk:21-slim as auth
WORKDIR /app
COPY --from=back-build /app/auth/target*.jar /app/app.jar
ENV TZ=Aisa/Shanghai
RUN ln -snf /usr/shar/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
HEALTHCHECK CMD curl --fail http://localhost:8443/ping || exit 1
EXPOSE 8443
CMD ["sh", "-c", "exec java -jar app.jar"]

FROM openjdk:21-slim as workflow
WORKDIR /app
COPY --from=back-build /app/workflow/target/*.jar /app/app.jar
ENV TZ=Aisa/Shanghai
RUN ln -snf /usr/shar/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
HEALTHCHECK CMD curl --fail http://localhost:8443/ping || exit 1
EXPOSE 8443
CMD ["sh", "-c", "exec java -jar app.jar"]

FROM openjdk:21-slim as orchestration
WORKDIR /app
COPY --from=back-build /app/orchestration/target*.jar /app/app.jar
ENV TZ=Aisa/Shanghai
RUN ln -snf /usr/shar/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
HEALTHCHECK CMD curl --fail http://localhost:8443/ping || exit 1
EXPOSE 8443
CMD ["sh", "-c", "exec java -jar app.jar"]
