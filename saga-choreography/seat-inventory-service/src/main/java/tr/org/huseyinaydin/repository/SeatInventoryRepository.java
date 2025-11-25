package tr.org.huseyinaydin.repository;

import tr.org.huseyinaydin.entity.SeatInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatInventoryRepository extends JpaRepository<SeatInventory, Long> {
    List<SeatInventory> findByShowIdAndSeatNumberIn(String showId, List<String> seatNumbers);
    List<SeatInventory> findByCurrentBookingId(String bookingId);
}