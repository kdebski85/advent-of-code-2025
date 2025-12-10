package pl.krzysztofdebski.task10;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.nio.file.Files.readAllLines;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static pl.krzysztofdebski.utils.ParsingUtils.unsignedInts;

public class Task10b {

    private static final Pattern SWITCH_PATTERN = Pattern.compile("\\([\\d+,]+\\)");

    public static void main(String[] args) throws IOException {
        long result = 0;
        //String input = "src/main/resources/task10/10-sample.input";
        String input = "src/main/resources/task10/10-task.input";

        for (String line : readAllLines(Path.of(input))) {
            System.out.println(line);
            List<List<Integer>> switches = getSwitches(line);

            List<Integer> voltages = unsignedInts(substringAfter(line, "{"));

            Model model = new Model();

            IntVar[] vars = new IntVar[switches.size()];

            for (int i = 0; i < switches.size(); i++) {
                List<Integer> sw = switches.get(i);
                int minVoltage = Integer.MAX_VALUE;
                for (Integer integer : sw) {
                    Integer voltage = voltages.get(integer);
                    if (voltage < minVoltage) {
                        minVoltage = voltage;
                    }
                }

                vars[i] = model.intVar("v" + i, 0, minVoltage, true);
            }

            int minSwitchCardinality = switches.stream().mapToInt(List::size).min().getAsInt();
            int sumVoltage = voltages.stream().mapToInt(Integer::valueOf).sum();

            IntVar sum = model.intVar("sum", 0, sumVoltage / minSwitchCardinality, true);
            model.sum(vars, "=", sum).post();

            for (int voltageIndex = 0; voltageIndex < voltages.size(); voltageIndex++) {
                int voltage = voltages.get(voltageIndex);

                List<IntVar> switchVars = new ArrayList<>();

                for (int switchIndex = 0; switchIndex < switches.size(); switchIndex++) {
                    if (switches.get(switchIndex).contains(voltageIndex)) {
                        switchVars.add(vars[switchIndex]);
                    }
                }

                model.sum(switchVars.toArray(IntVar[]::new), "=", voltage).post();
            }

            Solver solver = model.getSolver();
            Solution optimalSolution = solver.findOptimalSolution(sum, false);
            int val = optimalSolution.getIntVal(sum);
            System.out.println("val " + val);
            result += val;
            System.out.println("sum " + result);
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
}
