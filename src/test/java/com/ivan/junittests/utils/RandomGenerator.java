package com.ivan.junittests.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigInteger;
import java.util.Random;

public final class RandomGenerator {

    private RandomGenerator() {
        throw new IllegalStateException("Utility class");
    }

    private static Random r = new Random();

    public static String getRandomText() {
        int length = 50;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public static BigInteger getRandomId() {
        return BigInteger.valueOf(RandomUtils.nextInt(0, 18446744));
    }
}
