FROM openjdk:25-slim AS back-build

WORKDIR app
#COPY pom.xml /app
#RUN mvn clean install -Dmaven.test.skip=true
#
#COPY common /app/common
#COPY auth /app/auth
#COPY workflow /app/workflow
#COPY orchestration /app/orchestration
#RUN mvn package

COPY .mvn ./.mvn
COPY pom.xml mvnw .

COPY auth /app/auth
COPY common /app/common
COPY worker /app/worker
COPY workflow /app/workflow
COPY orchestration /app/orchestration
RUN ./mvnw package -Dmaven.test.skip=true -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true


FROM openjdk:25-slim AS auth
WORKDIR /app
COPY --from=back-build /app/auth/target*.jar /app/app.jar
ENV TZ=Aisa/Shanghai
RUN ln -snf /usr/shar/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
HEALTHCHECK CMD curl --fail http://localhost:8443/ping || exit 1
EXPOSE 8443
CMD ["sh", "-c", "exec java -jar app.jar"]

FROM openjdk:25-slim AS workflow
WORKDIR /app
COPY --from=back-build /app/workflow/target/*.jar /app/app.jar
ENV TZ=Aisa/Shanghai
RUN ln -snf /usr/shar/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
HEALTHCHECK CMD curl --fail http://localhost:8443/ping || exit 1
EXPOSE 8443
CMD ["sh", "-c", "exec java -jar app.jar"]

FROM openjdk:25-slim AS orchestration
WORKDIR /app
COPY --from=back-build /app/orchestration/target*.jar /app/app.jar
ENV TZ=Aisa/Shanghai
RUN ln -snf /usr/shar/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
HEALTHCHECK CMD curl --fail http://localhost:8443/ping || exit 1
EXPOSE 8443
CMD ["sh", "-c", "exec java -jar app.jar"]
