package pl.krzysztofdebski.task7;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static pl.krzysztofdebski.utils.Utils.readTwoDimensionsCharArray;
import static pl.krzysztofdebski.utils.Utils.sumLongs;

public class Task7b {

    public static void main(String[] args) throws IOException {
        long result;
       //  String input = "src/main/resources/task7/7-sample.input";
        String input = "src/main/resources/task7/7-task.input";

        char[][] chars = readTwoDimensionsCharArray(Path.of(input));
        Map<Integer, Long> rays = new HashMap<>();
        rays.put(chars.length/2 - 1, 1L);

        for (int y = 1; y < chars.length; y++) {
            Map<Integer, Long> newRays = new HashMap<>();
            for (Entry<Integer, Long> entry : rays.entrySet()) {
                int key = entry.getKey();
                long value = entry.getValue();
                if (chars[y][key] == '.') {
                    newRays.compute(key, (k, v) -> v == null ? value : v + value);
                } else {
                    if (key > 0) {
                        newRays.compute(key - 1, (k, v) -> v == null ? value : v + value);
                    }
                    if (key < chars.length - 1) {
                        newRays.compute(key + 1, (k, v) -> v == null ? value : v + value);
                    }
                }
            }
            rays = newRays;
        }

        result = sumLongs(rays.values());

        System.out.println(result);
    }
}
