package tr.com.huseyinaydin.respository;

import tr.com.huseyinaydin.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}