package im.ui.common;

public interface Browser {

	public enum TYPE {
		FIREFOX, CHROME, EDGE, SAFARI;
	}

	public enum CLOSE_TYPE {
		CLOSE, QUIT;
	}
	
	public enum EXECUTE {
		LOCAL, REMOTE;
	}

}