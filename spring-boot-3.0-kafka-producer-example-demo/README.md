# kafka-producer-example
### Spring Boot 3.1.1 ile Apache Kafka kullanımı.

1. Start Zookeeper Server / Zookeeper Sunucusunu Başlatalım

    ```sh bin/zookeeper-server-start.sh config/zookeeper.properties```

2. Start Kafka Server - Broker / Apache Kafka Sunucusunu Başlatalım

    ```sh bin/kafka-server-start.sh config/server.properties```

3. Create topic / Topik Oluşturalım

    ```sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic NewTopic --partitions 3 --replication-factor 1```

4. List out all topic names / Tüm Apache Kafka Topiklerini Listeleyelim

    ``` sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --list ```

5. Describe topics / İlgili bir topik hakkında bilgi edinelim
  
    ``` sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic NewTopic ```

6. Produce message / Bir mesaj üretip ilgili topiğe gönderelim.

    ```sh bin/kafka-console-producer.sh --broker-list localhost:9092 --topic NewTopic```


7. Consume message / İlgili topiğe gönderilen mesajı tüketelim.

    ``` sh bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic NewTopic --from-beginning ```


## Kafka Community Edition in local ##
## Kafka Topluluk Sürümünü Kendi Bilgisayarımızda Deneyelim ##

1. Start Zookeeper Server / Zookeeper Sunucusunu Başlatalım

    ```bin/zookeeper-server-start etc/kafka/zookeeper.properties```

2. Start Kafka Server - Broker / Apache Kafka Sunucusunu Başlatalım

    ```bin/kafka-server-start etc/kafka/server.properties```

3. Create topic / Topik Oluşturalım

    ```bin/kafka-topics --bootstrap-server localhost:9092 --create --topic NewTopic1 --partitions 3 --replication-factor 1```

4. List out all topic names / Tüm Apache Kafka Topiklerini Listeleyelim

    ``` bin/kafka-topics --bootstrap-server localhost:9092 --list ```

5. Describe topics / İlgili bir topik hakkında bilgi edinelim
  
    ``` bin/kafka-topics --bootstrap-server localhost:9092 --describe --topic NewTopic1 ```

6. Produce message / Bir mesaj üretip ilgili topiğe gönderelim.

    ```bin/kafka-console-producer --broker-list localhost:9092 --topic NewTopic1```


7. Consume message / İlgili topiğe gönderilen mesajı tüketelim.

    ```bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic NewTopic1 --from-beginning ```
    
8. Send CSV File data to kafka / CSV uzantılı bir dosyayı Kafka topiğine gönderelim.

   ```bin/kafka-console-producer --broker-list localhost:9092 --topic NewTopic1 <bin/customers.csv```
   
   
