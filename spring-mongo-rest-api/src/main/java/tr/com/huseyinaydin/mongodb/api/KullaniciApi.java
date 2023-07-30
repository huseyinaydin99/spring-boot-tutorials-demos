package tr.com.huseyinaydin.mongodb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tr.com.huseyinaydin.mongodb.entity.Kullanici;
import tr.com.huseyinaydin.mongodb.repository.KullaniciRepository;

import javax.annotation.PostConstruct;
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
@RequestMapping("/kullanici")
public class KullaniciApi {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @PostConstruct
    public void init(){
        Kullanici kullanici = new Kullanici();
        kullanici.setAdi("Hüseyin");
        kullanici.setSoyadi("Aydın");
        kullaniciRepository.save(kullanici);
    }

    @PostMapping
    public ResponseEntity<Kullanici> ekle(@RequestBody Kullanici kullanici){
        return ResponseEntity.ok(kullaniciRepository.save(kullanici));
    }

    @GetMapping
    public ResponseEntity<List<Kullanici>> tumunuListele(){
        return ResponseEntity.ok(kullaniciRepository.findAll());
    }



}
