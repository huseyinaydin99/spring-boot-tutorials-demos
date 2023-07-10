package tr.com.huseyinaydin.encrypt;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

public class ProtectDataSerializer extends JsonSerializer {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        String pii = value.toString().replaceAll("\\w(?=\\w{4})", "x");
        gen.writeString(pii);
    }
}
