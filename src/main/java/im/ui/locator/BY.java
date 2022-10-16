package im.ui.locator;

import org.openqa.selenium.By;

import im.ui.exception.IllegalParameterException;

public class BY {

	private static final String XPATH = "XPATH";
	private static final String CSS = "CSS";
	private static final String ID = "ID";
	private static final String NAME = "NAME";
	private static final String CLASSNAME = "CLASSNAME";
	private static final String LINKTEST = "LINKTEST";
	private static final String PARTIALLINKTEXT = "PARTIALLINKTEXT";
	private static final String TAGNAME = "TAGNAME";

	public static By get(String locator) {
		switch (getIdentifier(locator).toUpperCase()) {
		case XPATH:
			return By.xpath(getValue(locator));
		case CSS:
			return By.cssSelector(getValue(locator));
		case ID:
			return By.id(getValue(locator));
		case NAME:
			return By.name(getValue(locator));
		case CLASSNAME:
			return By.className(getValue(locator));
		case LINKTEST:
			return By.linkText(getValue(locator));
		case PARTIALLINKTEXT:
			return By.partialLinkText(getValue(locator));
		case TAGNAME:
			return By.tagName(getValue(locator));
		default:
			throw new IllegalParameterException("Unexpected Locator Identifier: " + locator);
		}

	}

	private static String getIdentifier(String locator) {
		return locator.substring(0, locator.indexOf("=")).trim();
	}

	private static String getValue(String locator) {
		return locator.substring(locator.indexOf("=") + 1, locator.length()).trim();
	}

}
