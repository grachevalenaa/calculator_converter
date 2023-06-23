package com.example.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculations {

    private double num1 = 0;

    private double num2 = 0;

    private static final List<String> HEIGHTS;
    
    private static final List<String> NUMBER_SIZES;

    private static final List<String> SIZES = new ArrayList<>();

    static {
        SIZES.add("S");
        SIZES.add("M");
        SIZES.add("L");
        SIZES.add("XL");
        SIZES.add("XXL");

        HEIGHTS = new ArrayList<>();

        int index = 0;
        int counter = 0;

        for (int i = 158; i < 188; ++i) {
            if (counter == 6) {
                counter = 0;
                ++index;
            }
            HEIGHTS.add(SIZES.get(index));
            ++counter;
        }

        NUMBER_SIZES = new ArrayList<>();

        counter = 0;
        index = 0;
        for (int i = 44; i < 58; ++i) {
            if (counter == 3) {
                counter = 0;
                ++index;
            }
            NUMBER_SIZES.add(SIZES.get(index));
            ++counter;
        }
    }

    public Double create(String operator) throws ArithmeticException {   // CМЕНА ЗНАКА В ЛЮБОМ МЕСТЕ ?
        switch (operator) {
            case "+" -> {
                return num1 + num2;
            }
            case "-" -> {
                return num1 - num2;
            }
            case "*" -> {
                return num1 * num2;
            }
            case "/" -> {
                if (num2 == 0) {
                    throw new ArithmeticException("Деление на 0");
                }
                return num1 / num2;
            }
            case "mod" -> {
                if (num2 == 0) {
                    throw new ArithmeticException("Деление на 0");
                }
                return num1 % num2;
            }
            case "^" -> {
                return Math.pow(num1, num2);
            }
            case "√" -> {
                if (num1 < 0) {
                    throw new ArithmeticException("Корень из отрицательного числа");
                }
                return Math.sqrt(num1);
            }
        }
        return null;
    }

    public static String getStringSizeUsingHeight(int numberSize) {
        return HEIGHTS.get(numberSize - 158);
    }

    public static int[] getHeight(String size) {
        int[] heights = new int[2];
        heights[0] = 158 + 6 * SIZES.indexOf(size);
        heights[1] = 158 + 6 * (SIZES.indexOf(size) + 1) ;
        return heights;
    }

    public static int[] getNumberSizes(String size) {
        int[] numberSizes = new int[2];
        numberSizes[0] = 44 + 3 * SIZES.indexOf(size);
        numberSizes[1] = 44 + 3 * (SIZES.indexOf(size) + 1) ;
        return numberSizes;
    }

    public static String  getStringSizeUsingNumberSize(int numberSize) {
        return NUMBER_SIZES.get(numberSize - 44);
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public double getNum1() {
        return num1;
    }

    public double getNum2() {
        return num2;
    }
}
