package pl.krzysztofdebski.task10;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.nio.file.Files.readAllLines;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static pl.krzysztofdebski.utils.ParsingUtils.unsignedInts;

public class Task10 {

    private static final Pattern SWITCH_PATTERN = Pattern.compile("\\([\\d+,]+\\)");

    public static void main(String[] args) throws IOException {
        long result = 0;
        // String input = "src/main/resources/task10/10-sample.input";
        String input = "src/main/resources/task10/10-task.input";

        for (String line : readAllLines(Path.of(input))) {
            String p = StringUtils.substringBefore(line, "]").substring(1);
            boolean[] conf = new boolean[p.length()];
            for (int i = 0; i < p.length(); i++) {
                conf[i] = p.charAt(i) == '#';
            }

            List<List<Integer>> switches = getSwitches(line);

            result += check(0, 0, new boolean[p.length()], conf, switches);
        }

        System.out.println(result);
    }

    private static List<List<Integer>> getSwitches(String line) {
        String s = substringAfter(line, "]");
        Scanner scanner = new Scanner(new StringReader(s));
        List<List<Integer>> switches = new ArrayList<>();
        String sw;
        do {
            sw = scanner.findInLine(SWITCH_PATTERN);
            if (sw != null) {
                List<Integer> ints = unsignedInts(sw);
                switches.add(ints);
            }
        } while (sw != null);
        return switches;
    }

    private static int check(int switchIndex, int soFar, boolean[] curr, boolean[] conf, List<List<Integer>> switches) {
        if (switchIndex == switches.size()) {
            if (Arrays.equals(curr, conf)) {
                return soFar;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        List<Integer> sw = switches.get(switchIndex);
        for (Integer integer : sw) {
            curr[integer] ^= true;
        }
        int result1 = check(switchIndex + 1, soFar + 1, curr, conf, switches);
        for (Integer integer : sw) {
            curr[integer] ^= true;
        }

        int result2 = check(switchIndex + 1, soFar, curr, conf, switches);

        return Math.min(result1, result2);
    }
}
