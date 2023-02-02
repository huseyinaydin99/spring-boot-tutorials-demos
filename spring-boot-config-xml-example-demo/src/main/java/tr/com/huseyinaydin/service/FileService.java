package tr.com.huseyinaydin.service;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public class FileService implements SelectService{

	public void sendMsg(String message) {
		System.out.println("Service C : " + message);
	}

}