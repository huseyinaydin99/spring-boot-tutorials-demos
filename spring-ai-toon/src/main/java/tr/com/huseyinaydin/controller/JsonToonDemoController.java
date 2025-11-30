package tr.com.huseyinaydin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.dto.UserProfile;
import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingType;
import im.arun.toon4j.Toon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.*;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@RestController
@RequestMapping("/api/demo")
@Slf4j
public class JsonToonDemoController {

    private final OllamaChatModel ollama;
    private final RestClient rest = RestClient.create("http://localhost:11434");
    private final ObjectMapper mapper = new ObjectMapper();

    private final Encoding encoder =
            Encodings.newDefaultEncodingRegistry().getEncoding(EncodingType.CL100K_BASE);

    public JsonToonDemoController(OllamaChatModel ollama) {
        this.ollama = ollama;
    }

    @GetMapping("/json-vs-toon")
    public Map<String, Object> compareJsonVsToon() {
        try {
            // ---------- 1. Basit POJO(Plain Old Java Object - Basit Eski Java Objesi - İş yapan metotların bulunmadığı Getter, Setter, Constructor, ToString, HashCode gibi metotların bulunduğu objedir.) ----------
            UserProfile profile = new UserProfile(
                    "Hüseyin AYDIN",
                    31,
                    "Türkiye",
                    List.of("Spring Boot", "Spring Framework", "Kafka", "Flyway Migration", "Liquibase Migration", "Microservices", "gRPC", "Kubernetes", "Docker"),
                    new UserProfile.Address("Kurdunus", "Niğde", "51200")
            );

            // ---------- 2. JSON ----------
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(profile);

            log.info("JSON Değeri: {}", json);

            // ---------- 3. TOON ----------
            String toon = Toon.encode(profile);
            log.info("TOON Değeri: {}", toon);

            // ---------- 4. LM Studio'nun tokenizer'ını kullanarak token sayımı ----------
            /*
            Token dediğimiz şey, metnin yapay zekânın anlayıp işleyebileceği formata dönüştürülmüş en küçük parçalarından her biridir.
            Tokenize edilmesi, toon içindeki metnin modelin anlayacağı en küçük parçalara (token’lara) bölünmesi demektir.
            Tokenize edilmesi sonucu oluşan token sayısı, metnin encoder tarafından bölündüğü en küçük birimler olan token’ların toplam adedini ifade eder.
             */
            int jsonTokens = encoder.encode(json).size();
            log.info("JSON Giriş Belirteçleri: {}", jsonTokens);
            int toonTokens = encoder.encode(toon).size();
            log.info("TOON : {}", toonTokens);

            // ---------- 5. OllamaChatModel kullanılarak sohbet işleme/processing ----------
            String jsonSummary = ollama.call("Summarize this profile:" + json);
            String toonSummary = ollama.call("Summarize this profile:" + toon);

            // ---------- 6. Yanıt oluştur ----------
            Map<String, Object> result = new LinkedHashMap<>();
            result.put("json_payload", json);
            result.put("toon_payload", toon);
            result.put("input_json_tokens", jsonTokens);
            result.put("input_toon_tokens", toonTokens);
            result.put("token_savings", jsonTokens - toonTokens);
            result.put("json_summary", jsonSummary);
            result.put("toon_summary", toonSummary);
            return result;
        } catch (Exception e) {
            return Map.of("error", e.getMessage());
        }
    }
}