package tr.com.huseyinaydin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.dao.UserRepository;
import tr.com.huseyinaydin.model.User;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

    /*@Cacheable(cacheNames = { "userCache" }) anotasyonu, bu metot çağrısının sonucunu "userCache"
    isimli cache bölgesine kaydedip aynı parametrelerle tekrar çağrıldığında veriyi metodu yeniden
    çalıştırmadan direkt önbellekten döndürerek hem performansı uçurur 🚀
    hem de gereksiz veritabanı sorgularını tokatlar 💪🔥.
    Hem veritabanındaki kayıt hem de userCache içindeki bu metoda ait entry silinmişse,
    getUsers() bir sonraki çağrıldığında tekrar repository.findAll() ile DB’ye gider,
    güncel (silinmiş kullanıcı olmadan) listeyi çeker ve bu yeni listeyi yeniden cache’e yazar.
    Böyle olması performans kaybı değil mi?
    O tek seferlik çağrıda cache miss nedeniyle DB’ye gitmek küçük bir performans kaybıdır
    ama bu, hem verinin tutarlı kalması hem de sonraki tüm çağrıların yeniden
    cache’ten okunarak çok daha hızlı dönmesi için ödenen gayet
    makul ve tasarımın bilinçli bir bedelidir.
    */
	@Cacheable(cacheNames = { "userCache" })
	public List<User> getUsers() {
		return repository.findAll();
	}

    /*
    @Cacheable(value = "userCache", key = "#id", unless = "#result==null") anotasyonu,
    metot çağrısının sonucunu id parametresini anahtar olarak kullanıp "userCache"
    isimli önbellekte saklar, aynı id ile tekrar çağrıldığında veriyi direkt
    cache’ten uçururcasına hızlı ⚡ döndürür, ancak sonuç null ise hiç cache’e
    yazmayarak çöp veriyi daha doğmadan boğar 💪🔥.
    */
	@Cacheable(value = "userCache", key = "#id", unless = "#result==null")
	public User getUser(int id) {
		return repository.findById(id).get();
	}

    /*
    @CacheEvict(value = "userCache") anotasyonu, ilgili metot çalıştığında value = "userCache"
    ile hedeflenen userCache isimli önbellekte bu metoda ait anahtarla (key) eşleşen
    kaydı silip cache’i tazeleyerek bayat veriye ekmek
    banmaya çalışan tüm sorguları tokatlar 💪⚡🔥.
    @CacheEvict(value = "userCache") kullandığımda, Spring metot parametrelerinden otomatik
    bir anahtar üretiyor ve sadece bu anahtara karşılık gelen cache kaydını siliyor.
    Yani ben key yazmasam bile, @Cacheable için kullanılan varsayılan
    key üretim mantığı burada da devreye giriyor ve başka kullanıcıların
    verisi olduğu gibi kalıyor. Eğer tüm userCache’i komple boşaltmak
    isteseydim, bunu ayrıca ve bilinçli olarak allEntries = true
    yazarak belirtmem gerekirdi.
    */
	@CacheEvict(value = "userCache")
	public String delete(int id) {
		repository.deleteById(id);
		return "Kullanıcı silindi ID: " + id;
	}
}