package tr.com.huseyinaydin.domain.service;

import tr.com.huseyinaydin.domain.dto.FoodOrder;
import tr.com.huseyinaydin.domain.port.input.PlaceOrderUsecase;
import tr.com.huseyinaydin.domain.port.input.TrackOrderUsecase;
import tr.com.huseyinaydin.domain.port.output.OrderRepositoryPort;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

//Servis kısmı aslında Core bir nevi NTier Architechture'deki Business Layer'a denk geliyor.
//Burada iş mantığı (business logic) yer alır.
//Servisler, uygulamanın iş kurallarını ve mantığını kapsar ve genellikle veri erişim katmanıyla (repository) etkileşime girerler.
public class OrderService implements PlaceOrderUsecase, TrackOrderUsecase {

    private final OrderRepositoryPort orderRepositoryPort;

    public OrderService(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public void placeOrder(FoodOrder order) {
        order.setStatus("SİPARİŞ VERİLDİ");
        System.out.println("--ÇEKİRDEK GİRİŞ PORTU İLE ÇALIŞTIRILDI--");
        // Burada genellikle siparişi kaydetmek için bir deponun çağrılması gerekir
        orderRepositoryPort.saveOrder(order);
    }

    @Override
    public String trackOrder(String orderId) {
        System.out.println("--ÇEKİRDEK GİRİŞ PORTU İLE ÇALIŞTIRILDI--");
        return orderRepositoryPort.findById(orderId);
    }
}