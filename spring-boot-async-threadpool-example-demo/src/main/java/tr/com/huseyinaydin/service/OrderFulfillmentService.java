package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
@Slf4j
public class OrderFulfillmentService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PaymentService paymentService;

    /* Tüm işlemler sırasıyla */
    /*
      1. Envantör kullanılabilirliği
      2. Sipariş için ödeme
      3. Kullanıcı bildirimi
      3. Depodaki satış görevlilerin satabilmesi için satış görevlilerine iletilmesi
      4. Paketleme
      5. Teslimatın kargoya verilmesi
      6. Kargo aracına yükleme işlemi
      7. Ürünün gömnderilmesi
      **/

    public Order processOrder(Order order) throws InterruptedException {
        order.setTrackingId(UUID.randomUUID().toString());
        if (inventoryService.checkProductAvailability(order.getProductId())) {
            //handle exception here
            paymentService.processPayment(order);
        } else {
            throw new RuntimeException("Hata oluştu lütfen tekrar deneyiniz.");
        }
        return order;
    }

    @Async("asyncTaskExecutor")
    public void notifyUser(Order order) throws InterruptedException {
        Thread.sleep(4000L);
        log.info("Kullanıcıya bildirim gönderdili. " + Thread.currentThread().getName());
    }
    @Async("asyncTaskExecutor")
    public void assignVendor(Order order) throws InterruptedException {
        Thread.sleep(5000L);
        log.info("Depo görevlilerine iletildi. " + Thread.currentThread().getName());
    }
    @Async("asyncTaskExecutor")
    public void packaging(Order order) throws InterruptedException {
        Thread.sleep(2000L);
        log.info("Peketleme yapıldı. " + Thread.currentThread().getName());
    }
    @Async("asyncTaskExecutor")
    public void assignDeliveryPartner(Order order) throws InterruptedException {
        Thread.sleep(10000L);
        log.info("Kargoya verildi. " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void assignTrailerAndDispatch(Order order) throws InterruptedException {
        Thread.sleep(3000L);
        log.info("Sipariş kargocular tarafından kamyona yüklenip yola çıktı. " + Thread.currentThread().getName());
    }
}
