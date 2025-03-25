package tr.com.huseyinaydin.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
public class OttSuccessHandler implements OneTimeTokenGenerationSuccessHandler {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private final OneTimeTokenGenerationSuccessHandler redirectHandler = new RedirectOneTimeTokenGenerationSuccessHandler("/ott/sent");

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken oneTimeToken) throws IOException, ServletException {
        // sihirli bağlantı (One time token)
        UriComponentsBuilder token = UriComponentsBuilder.fromHttpUrl(UrlUtils.buildFullRequestUrl(request))
                .replacePath(request.getContextPath())
                .path("/login/ott")
                .queryParam("token", oneTimeToken.getTokenValue());

        String magicLink = token.toUriString();
        System.out.println("One time token(sihirli tek kullanımlık link) : " + magicLink);

        sendOttNotification(oneTimeToken, magicLink);
        redirectHandler.handle(request, response, oneTimeToken);
    }

    private void sendOttNotification(OneTimeToken oneTimeToken, String magiclink) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("HuseyinAYDIN <" + sender + ">");
            message.setTo(getEmail().get(oneTimeToken.getUsername()));
            message.setSubject("One Time Token - Sihirli Tek Kullanımlık Link ");

            String messageBody = """
                     Merhaba %s,
                            \s
                    Uygulamaya giriş yapmak için aşağıdaki bağlantıyı kullanın unutmayın tek kullanımlıktır:
                            \s
                     %s
                            \s
                    Bu link belirlenen süre zarfında geçerlidir. Eğer isteğiniz başarısız olursa lütfen e-posta ile iletişime geçiniz!
                            \s
                    \s""".formatted(oneTimeToken.getUsername(), magiclink);

            message.setText(messageBody);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> getEmail() {
        Map<String, String> emailMap = new HashMap<>();
        emailMap.put("huseyinaydin99", "huseyinaydin99@gmail.com");
        return emailMap;
    }
}