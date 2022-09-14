FROM openjdk:8-jdk-alpine as compile-stage

COPY ./src /src
WORKDIR /src
RUN mkdir /out
RUN javac -d /out io/jlouie/mastermind/Main.java


FROM openjdk:8-jre-alpine

COPY --from=compile-stage /out /app
WORKDIR /app

ENTRYPOINT [ "java", "io.jlouie.mastermind.Main" ]
