package pl.himc.api.utils;

import org.apache.commons.lang3.Validate;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {

    private static Random RAND = new Random();
    
    public static int getRandInt(int min, int max) throws IllegalArgumentException {
        Validate.isTrue(max >= min, "Max can't be smaller than min!");
        return RandomUtil.RAND.nextInt(max - min + 1) + min;
    }
    
    public static double getRandDouble(double min, double max) throws IllegalArgumentException {
        Validate.isTrue(max >= min, "Max can't be smaller than min!");
        return RandomUtil.RAND.nextDouble() * (max - min) + min;
    }
    
    public static boolean getChance(double chance) {
        return chance >= 100.0 || chance >= getRandDouble(0.0, 100.0);
    }
    
    public static int nextInt(int i) {
        return RandomUtil.RAND.nextInt(i);
    }

    public static boolean chance(double chance) {
        return chance >= 100 || chance >= ThreadLocalRandom.current().nextDouble() * 100;
    }
}
