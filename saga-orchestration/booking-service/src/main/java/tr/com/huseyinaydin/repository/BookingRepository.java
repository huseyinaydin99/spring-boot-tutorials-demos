package tr.com.huseyinaydin.repository;
import tr.com.huseyinaydin.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByBookingCode(String bookingId);
}