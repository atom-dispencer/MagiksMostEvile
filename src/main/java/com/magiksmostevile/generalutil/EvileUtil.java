package com.magiksmostevile.generalutil;

import java.util.concurrent.ThreadLocalRandom;

public class EvileUtil {
    public static int min;
    public static int max;

    public static int getRandomNum(int min, int max) {
	return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
