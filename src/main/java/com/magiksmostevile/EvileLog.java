package com.magiksmostevile;

public class EvileLog {	
	
	boolean shouldLog = true;
	
		public static void LogText(boolean shouldLog, int loggingLevel, String loggingText) {
			if(shouldLog && loggingLevel == 1) {
				System.out.println("EVILE INFORMATION!" + loggingText);
				}
			if(shouldLog && loggingLevel == 2) {
				System.out.println("EVILE WARNING!" + loggingText);
				}
			if(shouldLog && loggingLevel == 3) {
				System.out.println("EVILE ERROR!" + loggingText);
				}
		}
}
