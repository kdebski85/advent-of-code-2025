package pl.krzysztofdebski.task3;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.readAllLines;
import static pl.krzysztofdebski.utils.ParsingUtils.singleDigitIntArray;

public class Task3b {

    public static void main(String[] args) throws IOException {
        long result = 0;
       //  String input = "src/main/resources/task3/3-sample.input";
        String input = "src/main/resources/task3/3-task.input";
        for (String line : readAllLines(Path.of(input))) {
            int[] ints = singleDigitIntArray(line);
            int fpos = -1;
            for (int d = 0; d < 12; d++) {
                int fmax = 0;
                for (int i = fpos + 1; i < ints.length - 11 + d; i++) {
                    if (ints[i] > fmax) {
                        fmax = ints[i];
                        fpos = i;
                    }
                }
                result += ((long)fmax) * Math.pow(10, 12 - d - 1);
            }
        }

        System.out.println(result);
    }
}
