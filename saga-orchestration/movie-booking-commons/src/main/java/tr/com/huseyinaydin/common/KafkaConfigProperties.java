package tr.com.huseyinaydin.common;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

public class KafkaConfigProperties {

    public static final String PAYMENT_EVENTS_TOPIC = "payment-events";
    public static final String PAYMENT_EVENTS_CMD_TOPIC = "payment-events-commands";
    public static final String PAYMENT_EVENT_GROUP = "payment-event-group";


    public static final String SEAT_RESERVED_TOPIC = "seat-reserved-topic";
    public static final String SEAT_RESERVED_CMD_TOPIC = "seat-reserved-commands";
    public static final String SEAT_EVENT_GROUP = "seat-event-group";

    public static final String MOVIE_BOOKING_EVENTS_TOPIC = "movie-booking-events";
    public static final String MOVIE_BOOKING_GROUP = "movie-booking-group";

    public static final String ORCHESTRATOR_CONSUMER_GROUP = "orchestrator-event-group";
}