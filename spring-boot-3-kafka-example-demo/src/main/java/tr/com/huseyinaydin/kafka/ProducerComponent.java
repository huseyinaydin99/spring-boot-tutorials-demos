package tr.com.huseyinaydin.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tr.com.huseyinaydin.config.AppConfig;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Slf4j
@Component
@RequiredArgsConstructor
public class ProducerComponent {

    private final AppConfig appConfig;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        CompletableFuture<SendResult<String, String>> completableFuture = kafkaTemplate.send(appConfig.getTopicName(), msg);
        completableFuture.whenComplete(new BiConsumer<>() {
            @Override
            public void accept(SendResult<String, String> stringStringSendResult, Throwable throwable) {
                log.info("Sent message:" + stringStringSendResult.getProducerRecord().value() +
                        ",  offset:" + stringStringSendResult.getRecordMetadata().offset());
            }

            @Override
            public BiConsumer<SendResult<String, String>, Throwable> andThen(BiConsumer<? super SendResult<String, String>, ? super Throwable> after) {
                log.error("Unable to send message:");
                return BiConsumer.super.andThen(after);
            }
        });
        completableFuture.complete((SendResult<String, String>) new Object());
    }

    @Scheduled(fixedDelay = 5000)
    public void test(){
        sendMessage("Message - " + new Date());
        log.info("Test Message sent");
    }
}