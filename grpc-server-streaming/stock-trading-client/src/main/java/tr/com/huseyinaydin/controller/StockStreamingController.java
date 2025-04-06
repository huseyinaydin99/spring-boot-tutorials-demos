package tr.com.huseyinaydin.controller;

import com.google.protobuf.util.JsonFormat;
import tr.com.huseyinaydin.StockRequest;
import tr.com.huseyinaydin.StockResponse;
import tr.com.huseyinaydin.StockTradingServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import net.devh.boot.grpc.client.inject.GrpcClient;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@RestController
@RequestMapping("/stocks")
public class StockStreamingController {

    @GrpcClient("stockService")
    private StockTradingServiceGrpc.StockTradingServiceStub stockServiceStub;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    @GetMapping(value = "/subscribe/{symbol}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeStockPrice(@PathVariable String symbol) {
        SseEmitter emitter = new SseEmitter();
        executor.execute(() -> {
            StockRequest request = StockRequest.newBuilder().setStockSymbol(symbol).build();

            stockServiceStub.subscribeStockPrice(request, new StreamObserver<>() {
                @Override
                public void onNext(StockResponse response) {
                    try {
                        String jsonResponse = JsonFormat.printer().print(response);
                        emitter.send(jsonResponse);
                    } catch (IOException e) {
                        emitter.completeWithError(e);
                    }
                }

                @Override
                public void onError(Throwable t) {
                    emitter.completeWithError(t);
                }

                @Override
                public void onCompleted() {
                    emitter.complete();
                }
            });
        });
        return emitter;
    }
}