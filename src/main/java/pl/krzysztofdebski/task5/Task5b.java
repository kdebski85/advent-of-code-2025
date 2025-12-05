package pl.krzysztofdebski.task5;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readAllLines;
import static pl.krzysztofdebski.utils.ParsingUtils.longs;
import static pl.krzysztofdebski.utils.ParsingUtils.unsignedLongs;
import static pl.krzysztofdebski.utils.Utils.partitionByNewLines;

public class Task5b {

    public static void main(String[] args) throws IOException {
        long result = 0;
      //  String input = "src/main/resources/task5/5-sample.input";
        String input = "src/main/resources/task5/5-task.input";
        List<List<String>> lists = partitionByNewLines(readAllLines(Path.of(input)));

        RangeSet<Long> set = TreeRangeSet.create();
        for (String s : lists.get(0)) {
            List<Long> longs = unsignedLongs(s);
            set.add(Range.closed(longs.get(0), longs.get(1)));
        }

        for (Range<Long> range : set.asRanges()) {
            result += range.upperEndpoint() - range.lowerEndpoint() + 1;
        }

        System.out.println(result);
    }
}
