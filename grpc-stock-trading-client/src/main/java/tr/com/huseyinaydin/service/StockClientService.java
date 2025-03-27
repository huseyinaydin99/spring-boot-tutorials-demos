package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.StockRequest;
import tr.com.huseyinaydin.StockResponse;
import tr.com.huseyinaydin.StockTradingServiceGrpc;
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
    private StockTradingServiceGrpc.StockTradingServiceBlockingStub serviceBlockingStub;

    public StockResponse getStockPrice(String stockSymbol) {
        StockRequest request = StockRequest.newBuilder().setStockSymbol(stockSymbol).build();
        return serviceBlockingStub.getStockPrice(request);
    }
}