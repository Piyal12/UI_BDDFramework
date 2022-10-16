package im.ui.exception;

@SuppressWarnings("serial")
public class AppiumServerUsageException extends RuntimeException {

	public String message;

	public AppiumServerUsageException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "AppiumServerUsageException [message=" + message + "]";
	}
}
