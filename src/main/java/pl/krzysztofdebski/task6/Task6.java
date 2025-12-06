package pl.krzysztofdebski.task6;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readAllLines;
import static pl.krzysztofdebski.utils.ParsingUtils.ints;
import static pl.krzysztofdebski.utils.ParsingUtils.unsignedInts;
import static pl.krzysztofdebski.utils.Utils.sumLongs;

public class Task6 {

    public static void main(String[] args) throws IOException {
        long result = 0;
     //   String input = "src/main/resources/task6/6-sample.input";
        String input = "src/main/resources/task6/6-task.input";
        List<String> lines = readAllLines(Path.of(input));
        String last = lines.getLast();
        String[] signs = last.split(" +");

        long[] partialResults = new long[signs.length];
        for (int i = 0; i < partialResults.length; i++) {
            String first = lines.getFirst();
            List<Integer> ints = unsignedInts(first);
            partialResults[i] = ints.get(i);
        }

        for (int i = 1; i < lines.size() - 1; i++) {
            String line = lines.get(i);
            List<Integer> ints = unsignedInts(line);
            for (int j = 0; j < ints.size(); j++) {
                int num = ints.get(j);
                if (signs[j].equals("+")) {
                    partialResults[j] += num;
                } else {
                    partialResults[j] *= num;
                }
            }
        }

        result = sumLongs(partialResults);

        System.out.println(result);
    }
}
