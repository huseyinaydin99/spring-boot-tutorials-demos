package tr.com.huseyinaydin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Slf4j
@RestController
public class RestartController {

    @Autowired
    private TestRestartApplication restartApplication;

    @PostMapping("/restartApp")
    public void restartUsingActuator() {
        log.info("Request restart application");
        restartApplication.restartApp();
    }
}