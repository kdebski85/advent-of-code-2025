package pl.krzysztofdebski.task1;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.readAllLines;

public class Task1b {

    public static void main(String[] args) throws IOException {
        int pos = 50;
        int result = 0;
        String input = "src/main/resources/task1/1-task.input";
        // String input = "src/main/resources/task1/1-task.input";

        for (String line : readAllLines(Path.of(input))) {
            int r = Integer.parseInt(line.substring(1));
            if (line.startsWith("L")) {
                result += r / 100;
                if (pos - r % 100 < 0 && pos > 0) {
                    result++;
                }
                if (r % 100 == pos) {
                    result++;
                }
                pos = (pos - r + 1000) % 100;
            } else {
                result += (pos + r) / 100;
                pos = (pos + r) % 100;
            }
        }

        System.out.println(result);
    }
}
