package im.ui.exception;

@SuppressWarnings("serial")
public class WebDriverException extends RuntimeException {

    private String message;
    private String closeType;

    public WebDriverException(String message, String closeType) {
        this.message = message;
        this.closeType = closeType;
    }

    @Override
    public String toString() {
        if (closeType != null)
            return "SessionCloseException{" + "message='" + message + '\'' + ", closeType='" + closeType + '\'' + '}';
        return "SessionCloseException{" + "message='" + message + '\'' + '}';
    }
}
