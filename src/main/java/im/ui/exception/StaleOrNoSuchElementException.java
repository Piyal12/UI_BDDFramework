package im.ui.exception;

import org.openqa.selenium.WebDriverException;

@SuppressWarnings("serial")
public class StaleOrNoSuchElementException extends WebDriverException {

    private String message;

    public StaleOrNoSuchElementException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "StaleOrNoSuchElementException{" + "message='" + message + '\'' + '}';
    }
}
