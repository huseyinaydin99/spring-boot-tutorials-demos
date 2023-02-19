# kafka-publisher
Spring Boot ile Apache Kafka publisher.

# Başlat zookeeper.start bat dosyasını:
zookeeper-server-start.bat D:\DEV-SOFTWARES\kafka_2.12-1.1.0\config\zookeeper.properties

# start kafka server
kafka-server-start.bat C:\path_yol\kafka_2.12-1.1.0\config\server.properties

# Create Topic:
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 -topic huseyinaydin

# Mesaj Üretimi
kafka-console-producer.bat --broker-list localhost:9092 --topic huseyinaydin

# Mesaj Tüketimi
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic huseyinaydin
