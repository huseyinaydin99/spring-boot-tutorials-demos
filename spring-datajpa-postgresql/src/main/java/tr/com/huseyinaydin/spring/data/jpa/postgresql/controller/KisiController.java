package tr.com.huseyinaydin.spring.data.jpa.postgresql.controller;

import lombok.RequiredArgsConstructor;
import tr.com.huseyinaydin.spring.data.jpa.postgresql.dto.KisiDto;
import tr.com.huseyinaydin.spring.data.jpa.postgresql.entity.Kisi;
import tr.com.huseyinaydin.spring.data.jpa.postgresql.service.KisiService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@RestController
@RequestMapping("/kisi")
@RequiredArgsConstructor
public class KisiController {

    private final KisiService kisiService;

    @PostMapping
    public ResponseEntity<KisiDto> ekle(@RequestBody KisiDto kisiDto){
        return ResponseEntity.ok(kisiService.save(kisiDto));
    }

    @GetMapping
    public ResponseEntity<List<KisiDto>> tumunuListele(){
        return ResponseEntity.ok(kisiService.getAll());
    }
}