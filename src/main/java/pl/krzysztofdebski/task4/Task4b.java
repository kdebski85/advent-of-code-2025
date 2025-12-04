package pl.krzysztofdebski.task4;

import pl.krzysztofdebski.utils.Coord;
import pl.krzysztofdebski.utils.Direction;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.readAllLines;
import static pl.krzysztofdebski.utils.ParsingUtils.singleDigitIntArray;
import static pl.krzysztofdebski.utils.Utils.readTwoDimensionsCharArray;

public class Task4b {

    public static void main(String[] args) throws IOException {
        long result = 0;
        long prevResult = 0;
        // String input = "src/main/resources/task4/4-sample.input";
        String input = "src/main/resources/task4/4-task.input";
        char[][] chars = readTwoDimensionsCharArray(Path.of(input));
        do {
            prevResult = result;
            for (int y = 0; y < chars.length; y++) {
                for (int x = 0; x < chars[y].length; x++) {
                    if (chars[y][x] == '@') {
                        int s = 0;
                        Coord coord = new Coord(y, x);
                        for (Direction direction : Direction.ALL_DIRECTIONS) {
                            Coord relative = coord.relative(direction);
                            Character c = relative.valueIfInBounds(chars);
                            if (c != null && '@' == c) {
                                s++;
                            }
                        }
                        if (s < 4) {
                            result++;
                            chars[y][x] = 'x';
                        }
                    }
                }
            }
        } while (result != prevResult);

        System.out.println(result);
    }
}
