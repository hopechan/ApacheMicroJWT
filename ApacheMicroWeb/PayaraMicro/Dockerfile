FROM payara/micro:5.181
MAINTAINER rm13016 rm13016@ues.edu.sv
ADD ApacheMicroWeb-1.0-SNAPSHOT.war $PAYARA_PATH/deployments/ApacheMicroWeb-1.0-SNAPSHOT.war
ENTRYPOINT java -jar $PAYARA_PATH/payara-micro.jar --deploy $PAYARA_PATH/deployments/ApacheMicroWeb-1.0-SNAPSHOT.war

