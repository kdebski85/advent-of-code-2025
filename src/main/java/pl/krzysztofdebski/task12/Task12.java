package pl.krzysztofdebski.task12;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readAllLines;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;
import static pl.krzysztofdebski.utils.ParsingUtils.unsignedInts;
import static pl.krzysztofdebski.utils.Utils.partitionByNewLines;

public class Task12 {

    public static void main(String[] args) throws IOException {
        long result = 0;

        String input = "src/main/resources/task12/12-task.input";

        List<String> regions = partitionByNewLines(readAllLines(Path.of(input))).getLast();

        for (String region : regions) {
            String[] areaDimensions = substringBefore(region, ":").split("x");
            int area = Integer.parseInt(areaDimensions[0]) * Integer.parseInt(areaDimensions[1]);

            List<Integer> shapes = unsignedInts(substringAfter(region, ":"));
            int total = shapes.get(0) * 7 + shapes.get(1) * 6 + shapes.get(2) * 7 + shapes.get(3) * 5 + shapes.get(4) * 7 + shapes.get(5) * 7;

            if (area >= total) {
                result ++;
            }
        }

        System.out.println(result);
    }
}
