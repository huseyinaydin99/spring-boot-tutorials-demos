package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.StockRequest;
import tr.com.huseyinaydin.StockResponse;
import tr.com.huseyinaydin.StockTradingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
public class StockClientService {

    @GrpcClient("stockService")
    private StockTradingServiceGrpc.StockTradingServiceStub stockTradingServiceStub;

//    public StockResponse getStockPrice(String stockSymbol) {
//        StockRequest request = StockRequest.newBuilder().setStockSymbol(stockSymbol).build();
//        return serviceBlockingStub.getStockPrice(request);
//    }

    public void subscribeStockPrice(String symbol) {
        StockRequest request = StockRequest.newBuilder()
                .setStockSymbol(symbol)
                .build();
        stockTradingServiceStub.subscribeStockPrice(request, new StreamObserver<StockResponse>() {

            @Override
            public void onNext(StockResponse response) {
                System.out.println("Stok fiyatı güncellemesi: " + response.getStockSymbol() +
                        " Fiyat: " + response.getPrice() + " " +
                        " Zaman: " + response.getTimestamp());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Hata oldu gardaşıım pisi Ömer'im :D : " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Stok fiyat canlı akış güncellemesi tamamlandı !");
            }
        });
    }
}