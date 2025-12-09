package pl.krzysztofdebski.task2;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readString;
import static pl.krzysztofdebski.utils.ParsingUtils.unsignedLongs;

public class Task2b {

    public static void main(String[] args) throws IOException {
        long result = 0;
        // String input = "src/main/resources/task2/2-sample.input";
        String input = "src/main/resources/task2/2-task.input";
        String line = readString(Path.of(input));
        List<Long> ranges = unsignedLongs(line);
        for (int index = 0; index < ranges.size(); index += 2) {
            long begin = ranges.get(index);
            long end = ranges.get(index + 1);

            rangeCheck:
            for (long i = begin; i <= end; i++) {
                String numberAsString = String.valueOf(i);
                int length = numberAsString.length();

                substringCheck:
                for (int substringLength = 1; substringLength <= length / 2; substringLength++) {
                    if (length % substringLength == 0) {
                        String firstSubstring = numberAsString.substring(0, substringLength);
                        for (int k = 1; k < length / substringLength; k++) {
                            if (!firstSubstring.equals(numberAsString.substring(k * substringLength, k * substringLength + substringLength))) {
                                continue substringCheck;
                            }
                        }
                        result += i;
                        continue rangeCheck;
                    }
                }
            }
        }

        System.out.println(result);
    }
}
