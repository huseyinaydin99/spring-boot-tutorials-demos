package tr.com.huseyinaydin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Component
@Scope("prototype") //singleton default value
public class Message {

    private String messageBot;

    public Message(){}

    public String getMessageBot() {
        return messageBot;
    }

    public void setMessageBot(String messageBot) {
        this.messageBot = messageBot;
    }
}