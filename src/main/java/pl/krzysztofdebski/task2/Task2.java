package pl.krzysztofdebski.task2;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readString;
import static pl.krzysztofdebski.utils.ParsingUtils.unsignedLongs;

public class Task2 {

    public static void main(String[] args) throws IOException {
        long result = 0;
        //  String input = "src/main/resources/task2/2-sample.input";
        String input = "src/main/resources/task2/2-task.input";
        String line = readString(Path.of(input));
        List<Long> ranges = unsignedLongs(line);
        for (int index = 0; index < ranges.size(); index += 2) {
            long begin = ranges.get(index);
            long end = ranges.get(index + 1);
            for (long i = begin; i <= end; i++) {
                String s = String.valueOf(i);
                if (s.length() % 2 == 0) {
                    if (s.substring(0, s.length() / 2).equals(s.substring(s.length() / 2))) {
                        result += i;
                    }
                }
            }
        }

        System.out.println(result);
    }
}
