package tr.com.huseyinaydin.springbootdockerize.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@RestController
@RequestMapping("/message")
public class MessageApi {

    @GetMapping
    public String mesajVer(){
        return "Docker Image'ınn içinden merhabalar sayın sehirciler :D";
    }
}