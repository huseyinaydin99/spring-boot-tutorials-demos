package tr.com.huseyinaydin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

@Controller
public class OAuth2Controller {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/login/oauth2/code/{provider}")
    public String callback(@PathVariable String provider, Authentication authentication,
                           @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                           HttpSession session) {
        // handle the token exchange
        session.setAttribute("user", authentication.getPrincipal());
        session.setAttribute("provider", provider);

        // redirect to the home page
        return "redirect:/home";
    }
}