package tr.com.huseyinaydin.carwasher.scheduled;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Component
public class ScheduledJobs {

	@Scheduled(cron="0 21 22 * * ?")
	public void scheduledJob() { // <== Aha bu görev!
		System.out.println("Aha bu görevin çalıştığı tarih: "+new Date());
	}
}
