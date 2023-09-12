/root/kafka/bin/zookeeper-server-start.sh config/zookeeper.properties
/root/kafka/bin/kafka-server-start.sh config/server.properties
/root/kafka/bin/kafka-topics.sh --create --zookeeper localhost:9092 --replication-factor 1 --partitions 1 --topic test
