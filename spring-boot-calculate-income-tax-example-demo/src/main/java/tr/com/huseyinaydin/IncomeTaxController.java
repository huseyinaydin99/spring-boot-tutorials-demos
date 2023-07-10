package tr.com.huseyinaydin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot Examples
 * @since 1994
 **/

@Slf4j
@RestController
public class IncomeTaxController {

    private final IncomeTaxService incomeTaxService;

    public IncomeTaxController(IncomeTaxService incomeTaxService) {
        this.incomeTaxService = incomeTaxService;
    }

    @PostMapping(value = "net-income/calculation",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> incomeTax(@RequestBody IncomeTaxRequest request) {
        return new ResponseEntity<>(incomeTaxService.calculatingIncomeTax(request), HttpStatus.OK);
    }
}