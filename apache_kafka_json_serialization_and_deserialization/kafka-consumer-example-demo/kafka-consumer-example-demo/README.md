# kafka-consumer-example

# Döküman

## Açık kaynak kodlu Apache Kafka terminal üzerinden kullanımı

1. Zookeeper servisini başlatalım:

    ```sh bin/zookeeper-server-start.sh config/zookeeper.properties```

2. Kafka sunucusunu / broker'ını başlatalım:

    ```sh bin/kafka-server-start.sh config/server.properties```

3. Mesajları gönderebilmemiz için bir topic oluşturalım:

    ```sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic NewTopic --partitions 3 --replication-factor 1```

4. Tüm topikleri topik ismine göre listeleyelim:

    ``` sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --list ```

5. NewTopic isimli topiğin açıklamasını görelim:
  
    ``` sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic NewTopic ```

6. Mesaj üretelim ve ilgili topiğe gönderelim: 

    ```sh bin/kafka-console-producer.sh --broker-list localhost:9092 --topic NewTopic```


7. Topiğe gönderdiğimiz mesajı alalım / tüketelim:

    ``` sh bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic NewTopic --from-beginning ```


## Apache Kafka topluluk sürümü kullanımı:

1. Zookeeper servisini başlatalım:

    ```bin/zookeeper-server-start etc/kafka/zookeeper.properties```

2. Kafka sunucusunu / broker'ını başlatalım:

    ```bin/kafka-server-start etc/kafka/server.properties```

3. Mesajları gönderebilmemiz için bir topic oluşturalım:

    ```bin/kafka-topics --bootstrap-server localhost:9092 --create --topic NewTopic1 --partitions 3 --replication-factor 1```

4. Tüm topikleri topik ismine göre listeleyelim:

    ``` bin/kafka-topics --bootstrap-server localhost:9092 --list ```

5. NewTopic isimli topiğin açıklamasını görelim:
  
    ``` bin/kafka-topics --bootstrap-server localhost:9092 --describe --topic NewTopic1 ```

6. Mesaj üretelim ve ilgili topiğe gönderelim: 

    ```bin/kafka-console-producer --broker-list localhost:9092 --topic NewTopic1```


7. Topiğe gönderdiğimiz mesajı alalım / tüketelim:

    ```bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic NewTopic1 --from-beginning ```
    
8. CSV uzantılı bir dosyayı Kafka topiğine gönderelim:  

   ```bin/kafka-console-producer --broker-list localhost:9092 --topic NewTopic1 <bin/customers.csv```
   
   
