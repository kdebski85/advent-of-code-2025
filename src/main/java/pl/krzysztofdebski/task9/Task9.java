package pl.krzysztofdebski.task9;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;
import static pl.krzysztofdebski.utils.ParsingUtils.unsignedInts;

public class Task9 {

    public static void main(String[] args) throws IOException {
        long result = 0;
       // String input = "src/main/resources/task9/9-sample.input";
        String input = "src/main/resources/task9/9-task.input";

        List<C> records = new ArrayList<>();

        for (String line : readAllLines(Path.of(input))) {
            List<Integer> ints = unsignedInts(line);
            records.add(new C(ints.get(0), ints.get(1)));
        }

        for (int i = 0; i < records.size(); i++) {
            C c1 = records.get(i);
            for (int j = i + 1; j < records.size(); j++) {
                C c2 = records.get(j);
                long diffX = Math.abs(c1.x - c2.x) + 1;
                long diffY = Math.abs(c1.y - c2.y) + 1;
                long area = diffX * diffY;
                if (area > result) {
                    result = area;
                }
            }
        }

        System.out.println(result);
    }

    private record C(int x, int y) {

    }
}
