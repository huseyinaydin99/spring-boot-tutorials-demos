package tr.com.huseyinaydin.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.events.Transaction;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class TransactionSerializer implements Serializer<Transaction> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Transaction data) {
        try {
            return mapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Transaction serileştirilirken hata oluştu", e);
        }
    }
}