package pl.krzysztofdebski.task2;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

import static java.nio.file.Files.readString;
import static pl.krzysztofdebski.utils.ParsingUtils.unsignedLongs;

public class Task2Regexp {

    public static final Pattern PATTERN = Pattern.compile("^(\\d+?)\\1+$");

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
                if (PATTERN.matcher(Long.toString(i)).matches()) {
                    result += i;
                }
            }
        }

        System.out.println(result);
    }
}
