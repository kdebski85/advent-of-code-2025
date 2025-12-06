package pl.krzysztofdebski.task6;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static pl.krzysztofdebski.utils.Utils.readTwoDimensionsCharArray;

public class Task6b {

    public static void main(String[] args) throws IOException {
        long result = 0;
        //String input = "src/main/resources/task6/6-sample.input";
        String input = "src/main/resources/task6/6-task.input";
        char[][] chars = readTwoDimensionsCharArray(Path.of(input));

        List<Long> nums = new ArrayList<>();
        for (int x = chars[0].length - 1; x >= 0; x--) {
            long num = 0;
            for (int y = 0; y < chars.length; y++) {
                char c = chars[y][x];
                if (c != ' ') {
                    if (c == '*') {
                        nums.add(num);
                        num = 0;
                        long r = 1;
                        for (Long l : nums) {
                            r *= l;
                        }
                        result += r;
                    } else if (c == '+') {
                        nums.add(num);
                        num = 0;
                        long r = 0;
                        for (Long l : nums) {
                            r += l;
                        }
                        result += r;
                    } else {
                        num *= 10;
                        num += ((int) c) - 48;
                    }
                } else {
                    if (y == chars.length - 1) {
                        if (num == 0) {
                            nums = new ArrayList<>();
                        } else {
                            nums.add(num);
                            num = 0;
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }
}
