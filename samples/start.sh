#java -jar wiremock-1.58.1-standalone.jar --verbose --port 8969
java -Xdebug -agentlib:jdwp=transport=dt_socket,address=9999,server=y,suspend=n -jar wiremock-1.58.1-standalone.jar --verbose --port 9080
