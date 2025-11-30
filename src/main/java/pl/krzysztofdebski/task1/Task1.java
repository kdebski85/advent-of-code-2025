package pl.krzysztofdebski.task1;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.readAllLines;

public class Task1 {

    public static void main(String[] args) throws IOException {
        int result = 0;

        for (String line : readAllLines(Path.of("src/main/resources/task1/1-sample.input"))) {


        }

        System.out.println(result);
    }
}
