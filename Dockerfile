FROM java:8-jre-alpine
#FROM adoptopenjdk/openjdk8-openj9
#FROM omnia.modinter.com.ec:8250/fisa/java:8u181-b13

ENV WIREMOCK_HOME=/opt/wiremock

COPY build/libs/wiremock-1.58.1-standalone.jar run.sh /tmp/

RUN mkdir -p $WIREMOCK_HOME/bin && \
mv /tmp/wiremock-1.58.1-standalone.jar $WIREMOCK_HOME && \
mv /tmp/run.sh $WIREMOCK_HOME/bin

EXPOSE 9080

WORKDIR $WIREMOCK_HOME

#VOLUME ["/opt/wiremock"]

ENTRYPOINT sh bin/run.sh