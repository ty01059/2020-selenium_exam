package com.sbs.selenium.exam;

public class Util {
	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
