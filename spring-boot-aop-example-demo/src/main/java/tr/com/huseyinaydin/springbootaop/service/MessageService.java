package tr.com.huseyinaydin.springbootaop.service;

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
public class MessageService {

    public String mesajVer(String param){
    	System.out.println("Metod e-mail gönderdi parametre: "+param);
        return param;
    }
}