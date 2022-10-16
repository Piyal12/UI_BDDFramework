package im.ui.exception;

@SuppressWarnings("serial")
public class IllegalParameterException extends IllegalArgumentException {
	private String message;

	public IllegalParameterException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "IllegalParameterException{" + "message='" + message + '\'' + '}';
	}
}
