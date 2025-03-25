package tr.com.huseyinaydin.service;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot.
 * @since 1994
 */

@Service
public class SchedulerService {

    @Scheduled(fixedRate = 2000) // Her 2 saniyede bir çalıştırılır
    @SchedulerLock(name = "uniqueTaskName", lockAtMostFor = "5m", lockAtLeastFor = "2s")
    public void executeTask() {
        //rapor üretimi
        //bildirim gönderilmesi
        System.out.println("görevi çalıştırma tarihi ve saati: " + new Date());
    }
}