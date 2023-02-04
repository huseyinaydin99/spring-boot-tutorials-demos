package tr.com.huseyinaydin.service;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public class MessageProcessorImpl implements MessageProcessor {

	private SelectService myFileService;
	
	public MessageProcessorImpl(SelectService myFileService) {
		this.myFileService = myFileService;
	}

	public void setMyService(SelectService myService) {
		this.myFileService = myService;
	}

	public void processMsg(String message) {
		this.myFileService.sendMsg(message);
	}
}