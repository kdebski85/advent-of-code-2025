package pl.krzysztofdebski.task2;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readString;
import static pl.krzysztofdebski.utils.ParsingUtils.longs;

public class Task2 {

    public static void main(String[] args) throws IOException {
        long result = 0;
      //  String input = "src/main/resources/task2/2-sample.input";
        String input = "src/main/resources/task2/2-task.input";
        String line = readString(Path.of(input));
        List<Long> ranges = longs(line);
        for (int index = 0; index < ranges.size(); index+=2) {
            long begin = ranges.get(index);
            long end = -ranges.get(index + 1);
            for (long i = begin; i <= end; i++) {
                int length = (int) (Math.log10(i) + 1);
                if (length % 2 == 0) {
                    long first_n = (long) (i / Math.pow(10, Math.floor(Math.log10(i)) - (double)(length  / 2) + 1));
                    long l = (long) (first_n *  Math.pow(10, length / 2) + first_n);
                    if (l == i) {
                        result += i;
                    }
                }

            }
        }

        System.out.println(result);
    }
}
