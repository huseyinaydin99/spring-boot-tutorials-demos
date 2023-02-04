package tr.com.huseyinaydin.service;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public class SmsService implements SelectService{

	public void sendMsg(String message) {
		System.out.println("Sms Service : " + message);
	}

}