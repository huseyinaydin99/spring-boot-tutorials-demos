package tr.org.huseyinaydin.events;

public record SeatReservedEvent(String bookingId, boolean reserved, long amount) {}