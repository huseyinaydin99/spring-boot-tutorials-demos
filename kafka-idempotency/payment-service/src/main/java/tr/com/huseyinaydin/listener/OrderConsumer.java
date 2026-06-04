package tr.com.huseyinaydin.listener;

import tr.com.huseyinaydin.dto.OrderDto;
import tr.com.huseyinaydin.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {

    private static final String ORDER_TOPIC = "ORDER_TOPIC";

    private final PaymentService paymentService;

    public OrderConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = ORDER_TOPIC, groupId = "payment_group")
    public void processOrder(OrderDto orderDto, Acknowledgment acknowledgment) {
        String orderId = orderDto.getOrderId();
        log.info("Sipariş olayı alındı Sipariş ID: {}", orderId);

        // requestId'nin veritabanında zaten mevcut olup olmadığını kontrol et - tekrarlanabilirlik kontrolü
        try {
            paymentService.processPayment(orderDto);
            log.info("Ödeme işleme konuldu Sipariş ID: {}", orderId);
        } catch (DataIntegrityViolationException ex){
            log.warn("Tekrarlanan ödeme girişimi tespit edildi Sipariş ID: {}. İşleme atlanıyor.", orderId);
            acknowledgment.acknowledge(); // Kafka consumer’ın ilgili mesajı başarıyla işlediğini manuel olarak broker’a bildirir, böylece offset commit edilerek aynı mesajın tekrar işlenmesi (reprocessing) engellenir ve idempotency ile birlikte duplicate işlem riskini kontrol altına alır.
            // Offset’e iletmek, Kafka’ya “bu partition’da şu ana kadarki mesajları işledim” bilgisini verip tüketim noktasını ilerletmektir; böylece consumer yeniden başlasa bile aynı mesajları tekrar okumaz, kaldığı yerden devam eder.
            return;
        }
        //uygulama burada çöküyor istisnalar kaideyi bozmaz(sagopa kajmer)
        if(true){
            throw new DataIntegrityViolationException("Simüle edilmiş işleme hatası Order ID: " + orderId);
        }

        // İşlem başarıyla tamamlandıktan sonra mesaj onayı.
        acknowledgment.acknowledge(); // Kafka consumer’ın ilgili mesajı başarıyla işlediğini manuel olarak broker’a bildirir, böylece offset commit edilerek aynı mesajın tekrar işlenmesi (reprocessing) engellenir ve idempotency ile birlikte duplicate işlem riskini kontrol altına alır.
    }
}

/*
Listener tekrar tetiklenir mi?
Evet, Kafka açısından aynı mesaj tekrar gönderilirse (aynı event yeniden publish edilirse ya da retry olursa),
consumer bunu tekrar alır ve listener yeniden tetiklenir; Kafka “aynı iş mi daha önce işlendi mi?”
bilgisini kendiliğinden bilmez, bunu idempotency veya offset mantığı ile sen yönetirsin.
*/

/*
Bunu requestId üzerinde veritabanı unique constraint + exception handling + manuel ack kombinasyonuyla yönettim;
yani aynı event tekrar gelirse DataIntegrityViolationException fırlayıp işleme girmiyor ve offset commit edilerek
mesaj tekrar işlenmeyecek şekilde tüketim noktası sabitleniyor.
*/

/*
acknowledgment.acknowledge(); Kafka’ya mesajın başarıyla işlendiğini bildirerek offset’i ilerletmek
için kullanılır; böylece aynı mesaj tekrar gelse bile consumer’ın kaldığı yer doğru şekilde korunur
ve yeniden işleme kontrol altında tutulur.
Evet, özünde şunu yapar: “bu mesajı işledim, offset’i ilerlet;
servis yeniden başlasa bile aynı mesajı tekrar verme, kaldığı yerden devam et” bilgisini Kafka’ya bildirir.
Ben bu mesajı işledim sunucu kapanıp açılırsa eğer bana aynı mesajları almadığımı zannederek
tekrar gönderme offset'i bil kaldığın yeri bil haddi bil lan.

Kullanılmazsa offset commit edilmediği için Kafka o mesajı “işlenmedi” sayar ve consumer yeniden
başladığında aynı mesajı tekrar tekrar gönderir (en az bir kez teslim garantisi nedeniyle duplicate
processing oluşur).
*/