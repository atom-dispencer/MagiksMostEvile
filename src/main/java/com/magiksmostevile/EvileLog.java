package com.magiksmostevile;

public class EvileLog {

    private boolean shouldLog;
    private static boolean Logger_On = true;

    public static void main(String args[]) {
	XXXLoggerTest(true, EnumLogLevel.ERROR, "logger test");

	logText(true, EnumLogLevel.INFO, "INFO test");
	logText(true, EnumLogLevel.WARN, "WARN test");
	logText(true, EnumLogLevel.ERROR, "ERROR test");

    }

    private static boolean isLoggerOn() {
	return Logger_On;
    }

    public static void logText(boolean shouldLog, EnumLogLevel ell, String logText) {
	if (shouldLog && isLoggerOn()) {
	    System.out.println(ell.levelPreText.toString() + logText);
	}
    }

    public static void XXXLoggerTest(boolean shouldLog, EnumLogLevel ell, String logText) {
	if (shouldLog && isLoggerOn()) {
	    System.out.println(ell + logText);
	}
    }

    public enum EnumLogLevel {
	INFO("EVILE INFORMATION! : "), WARN("EVILE WARNING! : "), ERROR("EVILE EXCEPTION! THAT'S AN ERROR! : ");

	private String levelPreText;

	EnumLogLevel(String levelPreText) {
	    this.levelPreText = levelPreText;
	}

	protected String getLevelPreText() {
	    return levelPreText;
	}

    }

}
