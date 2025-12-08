package pl.krzysztofdebski.task8;

import pl.krzysztofdebski.utils.ParsingUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.nio.file.Files.readAllLines;
import static pl.krzysztofdebski.utils.ParsingUtils.unsignedInts;

public class Task8b {

    public static void main(String[] args) throws IOException {
        // String input = "src/main/resources/task8/8-sample.input";
        String input = "src/main/resources/task8/8-task.input";

        List<Coordinates> coords = new ArrayList<>();
        Map<Coordinates, Integer> groups = new HashMap<>();
        List<Distance> dist = new ArrayList<>();

        int i = 0;
        for (String line : readAllLines(Path.of(input))) {
            List<Integer> ints = unsignedInts(line);
            Coordinates c = new Coordinates(ints.get(0), ints.get(1), ints.get(2));
            coords.add(c);
            groups.put(c, i++);
        }

        int groupCount = groups.size();

        for (int j = 0; j < coords.size(); j++) {
            for (int k = j + 1; k < coords.size(); k++) {
                Coordinates coord = coords.get(j);
                Coordinates coord2 = coords.get(k);

                dist.add(new Distance(coord, coord2, Math.sqrt(Math.pow(coord.x - coord2.x, 2) + Math.pow(coord.y - coord2.y, 2) + Math.pow(coord.z - coord2.z, 2))));
            }
        }

        dist.sort(Comparator.comparing(Distance::dist));

        for (Distance d : dist) {
            int g1 = groups.get(d.c1);
            int g2 = groups.get(d.c2);
            if (g1 != g2) {
                for (Entry<Coordinates, Integer> entry : groups.entrySet()) {
                    if (entry.getValue().equals(g1)) {
                        entry.setValue(g2);
                    }
                }
                groupCount--;
                if (groupCount == 1) {
                    System.out.println(((long) d.c1.x) * d.c2.x);
                    return;
                }
            }
        }
    }

    record Coordinates(int x, int y, int z) {

    }

    record Distance(Coordinates c1, Coordinates c2, double dist) {

    }
}
