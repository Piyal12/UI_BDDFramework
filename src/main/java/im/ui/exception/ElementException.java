package im.ui.exception;

import im.ui.common.Condition;

@SuppressWarnings("serial")
public class ElementException extends RuntimeException {
	private String message;
	private String locator;
	private Condition condition;
	private Long timeOut;

	public ElementException(String message, String locator, Condition condition, Long timeOut) {
		this.message = message;
		this.locator = locator;
		this.condition = condition;
		this.timeOut = timeOut;
	}

	@Override
	public String toString() {
		return "ElementException{" + "message='" + message + '\'' + ", locator='" + locator + '\'' + ", condition="
				+ condition + ", timeOut=" + timeOut + '}';
	}

}
