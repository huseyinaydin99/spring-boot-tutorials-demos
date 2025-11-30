package tr.com.huseyinaydin.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

@Configuration
public class CacheConfig {

    @Bean
    public Config hazelcastConfig() {
        return new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName("userCache")
                                .setTimeToLiveSeconds(2000)
                                .setEvictionConfig(
                                        new EvictionConfig()
                                                .setSize(200) // limit
                                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                                                .setEvictionPolicy(EvictionPolicy.LRU)
                                )
                );
    }

    /*
     Genel anlamı: “Tahliye”, “çıkarma”, “kovma”.
     Cache / Hazelcast / bellek yönetimi bağlamında:
     “Eviction” = “(ön)bellekten atma” / “(ön)bellekten çıkarma” politikası.
     Yani belirli kurallara göre, artık tutulmayacak verilerin önbellekten silinmesi / atılması işlemi.
    */

    // LRU (Least Recently Used) politikası, bellek ya da önbellek dolduğunda
    // en uzun süredir kullanılmayan veriyi ilk olarak sistemden atan stratejidir.
}