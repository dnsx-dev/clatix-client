package com.clatix.utils;

import java.util.Random;

public class MathUtils {
    private static final Random RANDOM = new Random();

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public static double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }

    public static float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }

    public static int lerp(int a, int b, double t) {
        return (int)(a + (b - a) * t);
    }

    public static double random(double min, double max) {
        return min + (max - min) * RANDOM.nextDouble();
    }

    public static int random(int min, int max) {
        return min + RANDOM.nextInt(max - min + 1);
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(int number) {
        return !isEven(number);
    }

    public static double square(double value) {
        return value * value;
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(square(x2 - x1) + square(y2 - y1));
    }

    public static double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(square(x2 - x1) + square(y2 - y1) + square(z2 - z1));
    }
}