# kafka-spring-boot-app
practice app for learning kafka and spring boot

### Run zookeeper
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties

### Run kafka
kafka-server-start /usr/local/etc/kafka/server.properties

### Run App 
mvn spring-boot:run

### Web Requests for testing
- http://localhost:9000/kafka/publish/message?message=test
- http://localhost:9000/kafka/publish/greet?message=hello&name=VB
