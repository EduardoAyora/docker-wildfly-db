FROM jboss/wildfly:20.0.1.Final
COPY standalone-microprofile-jaeger.xml /opt/jboss/wildfly/standalone/configuration/
COPY maven/helloworld-mp.war /opt/jboss/wildfly/standalone/deployments/
EXPOSE 8080
EXPOSE 9990
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-microprofile-jaeger.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
