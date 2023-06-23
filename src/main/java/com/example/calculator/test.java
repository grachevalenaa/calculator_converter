package com.example.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {

    private static final List<String> SIZES;

    static {
        SIZES = new ArrayList<>();
        String[] sizes = new String[] {"S", "M", "L", "XL", "XXL"};
        int index = 0;
        int counter = 0;

        for (int i = 158; i < 188; ++i) {
            if (counter == 6) {
                counter = 0;
                ++index;
            }
            SIZES.add(sizes[index]);
            ++counter;
        }

        System.out.println(SIZES);
        System.out.println(counter);
    }
    public static void main(String[] args) {
        /*Map<String,Integer> m = new HashMap<String, Integer>();
        m.put("s", 5);
        m.put("34", 5);
        System.out.println(m.get("s"));*/
    }
}
