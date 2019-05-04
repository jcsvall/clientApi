package sv.com.jcsvall.clientApi.configurations;

public class HandleMyExeptionMessage extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HandleMyExeptionMessage(String exception) {
		super(exception);
	}
	
	
}
