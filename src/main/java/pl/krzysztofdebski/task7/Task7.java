package pl.krzysztofdebski.task7;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static pl.krzysztofdebski.utils.Utils.readTwoDimensionsCharArray;

public class Task7 {

    public static void main(String[] args) throws IOException {
        long result = 0;
       // String input = "src/main/resources/task7/7-sample.input";
        String input = "src/main/resources/task7/7-task.input";

        char[][] chars = readTwoDimensionsCharArray(Path.of(input));
        Set<Integer> rays = new HashSet<>();
        rays.add(chars.length/2 - 1);

        for (int y = 1; y < chars.length; y++) {
            Set<Integer> newRays = new HashSet<>();
            for (Integer ray : rays) {
                if (chars[y][ray] == '.') {
                    newRays.add(ray);
                } else {
                    if (ray > 0) {
                        newRays.add(ray - 1);
                    }
                    if (ray < chars.length - 1) {
                        newRays.add(ray + 1);
                    }
                    result++;
                }
            }
            rays = newRays;
        }

        System.out.println(result);
    }
}
