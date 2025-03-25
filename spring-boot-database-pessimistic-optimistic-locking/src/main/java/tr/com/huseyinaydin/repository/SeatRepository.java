package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public interface SeatRepository extends JpaRepository<Seat, Long> {

    //@Lock(LockModeType.PESSIMISTIC_WRITE) dipnotu, veritabanı işlemi sırasında belirli bir kaynağa kötümser yazma kilidi koyar. Bu, diğer işlemlerin aynı kaynağa erişimini engelleyerek sadece bir işlem için yazma izin verir, böylece veri tutarlılığını sağlar.
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Seat s WHERE s.id= :seatId")
    Seat findByIdAndLock(Long seatId);
}