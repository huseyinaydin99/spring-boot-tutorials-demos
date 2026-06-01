package tr.com.huseyinaydin.serdes;

import tr.com.huseyinaydin.events.Transaction;
import org.apache.kafka.common.serialization.Serdes;

public class TransactionSerde extends Serdes.WrapperSerde<Transaction> {

    public TransactionSerde() {
        super(new TransactionSerializer(), new TransactionDeserializer());
    }
}