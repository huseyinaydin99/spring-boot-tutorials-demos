package tr.org.huseyinaydin.events;

public record BookingPaymentEvent(String bookingId, boolean paymentCompleted, long amount) {
}