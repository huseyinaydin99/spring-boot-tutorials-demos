package tr.com.huseyinaydin.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
public class RedisCacheService {

    @Cacheable(cacheNames = "mySpecialCache")
    public String longRunningMethod() throws InterruptedException{
        Thread.sleep(5000L);
        return "method çalisti";
    }

    @CacheEvict(cacheNames = "mySpecialCache")
    public void clearCache(){
        System.out.println("cache temizlendi.");
    }
}