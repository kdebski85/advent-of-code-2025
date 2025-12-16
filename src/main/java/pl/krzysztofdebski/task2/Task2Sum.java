package pl.krzysztofdebski.task2;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.nio.file.Files.readString;
import static pl.krzysztofdebski.utils.ParsingUtils.unsignedLongs;

public class Task2Sum {

    public static void main(String[] args) throws IOException {
        long result = 0;
        // String input = "src/main/resources/task2/2-sample.input";
        String input = "src/main/resources/task2/2-task.input";
        String line = readString(Path.of(input));
        List<Long> ranges = unsignedLongs(line);
        for (int index = 0; index < ranges.size(); index += 2) {
            long begin = ranges.get(index);
            long end = ranges.get(index + 1);

            long lowerBoundHalf = lowerBoundHalf(begin);
            long upperBoundHalf = upperBoundHalf(end);

            if (upperBoundHalf >= lowerBoundHalf) {
                result += getSum(lowerBoundHalf, upperBoundHalf);
            }
        }

        System.out.println(result);
    }

    private static long getSum(long lowerBoundHalf, long upperBoundHalf) {
        String lowerAsString = String.valueOf(lowerBoundHalf);
        String upperAsString = String.valueOf(upperBoundHalf);
        if (lowerAsString.length() != upperAsString.length()) {
            throw new IllegalArgumentException("Unsupported range from " + lowerAsString + " to " + upperAsString);
        }

        long sumEnd = sumN(upperBoundHalf);
        long sumBegin = sumN(lowerBoundHalf - 1);
        long sum = sumEnd - sumBegin;
        sum += (long) (sum * Math.pow(10, lowerAsString.length()));
        return sum;
    }

    private static long sumN(long value) {
        return value * (value + 1) / 2;
    }

    private static long lowerBoundHalf(long value) {
        String beginAsString = String.valueOf(value);
        int length = beginAsString.length();

        if (length % 2 == 0) {
            String upperString = upperHalf(beginAsString);
            int upper = parseInt(upperString);
            int lower = parseInt(lowerHalf(beginAsString));
            if (upper >= lower) { //13 11 -> 13 13
                return upper;
            } else { //12 14 -> 13 13
                return upper + 1;
            }
        } else {
            //noinspection IntegerDivisionInFloatingPointContext
            return length == 1 ? 1L : (long) Math.pow(10, (length / 2));
        }
    }

    private static long upperBoundHalf(long value) {
        String beginAsString = String.valueOf(value);
        int length = beginAsString.length();

        if (length == 1) {
            return -1;
        } else if (length % 2 == 0) {
            String upperString = upperHalf(beginAsString);
            int upper = parseInt(upperString);
            int lower = parseInt(lowerHalf(beginAsString));
            if (lower >= upper) { //12 15 -> 12 12
                return upper;
            } else { //15 12 -> 14 14
                return upper - 1;
            }
        } else {
            //noinspection IntegerDivisionInFloatingPointContext
            return (long) (Math.pow(10, length / 2) - 1);
        }
    }

    private static String upperHalf(String string) {
        return string.substring(0, string.length() / 2);
    }

    private static String lowerHalf(String string) {
        return string.substring(string.length() / 2);
    }
}
