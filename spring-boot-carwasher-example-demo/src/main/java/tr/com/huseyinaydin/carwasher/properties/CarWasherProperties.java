package tr.com.huseyinaydin.carwasher.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@ConfigurationProperties(prefix = "carwasher")
public class CarWasherProperties {

	private Management management = new Management();

	public class Management {
		private boolean loadTestData;

		public boolean isLoadTestData() {
			return loadTestData;
		}

		public void setLoadTestData(boolean loadTestData) {
			this.loadTestData = loadTestData;
		}
	}

	public Management getManagement() {
		return management;
	}

	public void setManagement(Management management) {
		this.management = management;
	}
}
