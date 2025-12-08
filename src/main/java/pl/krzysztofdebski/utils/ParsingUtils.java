package pl.krzysztofdebski.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ParsingUtils {

    public static List<Integer> ints(String s) {
        Scanner sc = new Scanner(s);
        List<Integer> result = new ArrayList<>();
        String number;
        while ((number = sc.findInLine("-?\\d+")) != null) {
            result.add(Integer.parseInt(number));
        }
        return result;
    }

    public static List<Integer> unsignedInts(String s) {
       List<Integer> result = new ArrayList<>();

       int number = 0;
       boolean set = false;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                if (set) {
                    number *= 10;
                    number += c - '0';
                } else {
                    set = true;
                    number = c - '0';
                }
            } else {
                if (set) {
                    result.add(number);
                }
                number = 0;
                set = false;
            }
        }
        if (set) {
            result.add(number);
        }
        return result;
    }

    public static int[] singleDigitIntArray(String s) {
        int length = s.length();
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = Integer.parseInt(s.substring(i, i + 1));
        }
        return ints;
    }

    public static Integer[] integerArray(String s) {
        return ints(s).toArray(Integer[]::new);
    }

    public static int[] intsArray(String s) {
        List<Integer> ints = ints(s);
        int[] result = new int[ints.size()];
        for (int i = 0; i < ints.size(); i++) {
            result[i] = ints.get(i);
        }

        return result;
    }

    public static List<Long> longs(String s) {
        List<Long> result = new ArrayList<>();

        long number = 0;
        boolean set = false;
        boolean negative = false;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                if (set) {
                    number *= 10L;
                    number += c - '0';
                } else {
                    set = true;
                    number = c - '0';
                }
            } else {
                if (set) {
                    result.add(negative ? -number : number);
                    number = 0;
                    set = false;
                }

                negative = c == '-';
            }
        }
        if (set) {
            result.add(negative ? -number : number);
        }
        return result;
    }

    public static List<Long> unsignedLongs(String s) {
        List<Long> result = new ArrayList<>();

        long number = 0;
        boolean set = false;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                if (set) {
                    number *= 10L;
                    number += c - '0';
                } else {
                    set = true;
                    number = c - '0';
                }
            } else {
                if (set) {
                    result.add(number);
                }
                number = 0;
                set = false;
            }
        }
        if (set) {
            result.add(number);
        }
        return result;
    }

    public static long[] longsArray(String s) {
        List<Long> longs = longs(s);
        long[] result = new long[longs.size()];
        for (int i = 0; i < longs.size(); i++) {
            result[i] = longs.get(i);
        }

        return result;
    }
}
