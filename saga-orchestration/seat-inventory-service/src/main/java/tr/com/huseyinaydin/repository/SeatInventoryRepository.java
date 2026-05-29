package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.SeatInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatInventoryRepository extends JpaRepository<SeatInventory, Long> {

    List<SeatInventory> findByShowIdAndSeatNumberIn(String showId, List<String> seatNumbers);

    List<SeatInventory> findByCurrentBookingId(String bookingId);
}
