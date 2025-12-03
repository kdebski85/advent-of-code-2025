package pl.krzysztofdebski.task3;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Files.readString;
import static pl.krzysztofdebski.utils.ParsingUtils.intsArray;
import static pl.krzysztofdebski.utils.ParsingUtils.longs;
import static pl.krzysztofdebski.utils.ParsingUtils.singleDigitIntArray;

public class Task3 {

    public static void main(String[] args) throws IOException {
        long result = 0;
       // String input = "src/main/resources/task3/3-sample.input";
        String input = "src/main/resources/task3/3-task.input";
        for (String line : readAllLines(Path.of(input))) {
            int[] ints = singleDigitIntArray(line);

            int fpos = 0;
            int fmax = 0;
            for (int i = 0; i < ints.length - 1; i++) {
                if (ints[i] > fmax) {
                    fmax = ints[i];
                    fpos = i;
                }
            }
            int lmax = 0;
            for (int i = fpos + 1; i < ints.length; i++) {
                if (ints[i] > lmax) {
                    lmax = ints[i];
                }
            }
            result += lmax + fmax * 10;
        }

        System.out.println(result);
    }
}
