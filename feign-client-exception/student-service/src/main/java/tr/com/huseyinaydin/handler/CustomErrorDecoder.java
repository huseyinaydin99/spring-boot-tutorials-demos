package tr.com.huseyinaydin.handler;

import tr.com.huseyinaydin.exception.CourseServiceException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        String message = "Bilinmeyen hata";

        try {
            if (response.body() != null) {
                message = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            message = "Yanıt metni okunurken hata oluştu! " + e.getMessage();
        }

        return switch (response.status()) {
            case 400 -> new CourseServiceException("Kurs hizmetinden Kötü istek: " + message);
            case 500 -> new CourseServiceException("Kurs hizmetinden kaynaklanan dahili sunucu hatası: " + message);
            case 503 -> new CourseServiceException("Kurs hizmeti mevcut değil: " + message);
            default -> new Exception("Bilinmeyen hata: " + message);
        };
    }
}