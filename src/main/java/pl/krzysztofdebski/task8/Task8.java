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

public class Task8 {

    public static void main(String[] args) throws IOException {
        long result = 0;
        //String input = "src/main/resources/task8/8-sample.input";
        String input = "src/main/resources/task8/8-task.input";

        List<Coordinates> coords = new ArrayList<>();
        Map<Coordinates, Integer> groups = new HashMap<>();
        List<Distance> dist = new ArrayList<>();

        int i = 0;
        for (String line : readAllLines(Path.of(input))) {
            List<Integer> ints = ParsingUtils.unsignedInts(line);
            Coordinates c = new Coordinates(ints.get(0), ints.get(1), ints.get(2));
            coords.add(c);
            groups.put(c, i++);
        }

        for (int j = 0; j < coords.size(); j++) {
            for (int k = j+1; k < coords.size(); k++) {
                Coordinates coord = coords.get(j);
                Coordinates coord2 = coords.get(k);

                dist.add(new Distance(coord, coord2, Math.sqrt(Math.pow(coord.x - coord2.x, 2) + Math.pow(coord.y - coord2.y, 2) + Math.pow(coord.z - coord2.z, 2))));
            }
        }

        dist.sort(Comparator.comparing(Distance::dist));

        for (int j = 0; j < 1000; j++) {
            Distance d = dist.get(j);
            int g1 = groups.get(d.c1);
            int g2 = groups.get(d.c2);
            if (g1 != g2) {
                for (Entry<Coordinates, Integer> entry : groups.entrySet()) {
                    if (entry.getValue().equals(g1)) {
                        entry.setValue(g2);
                    }
                }
            }
        }

        Map<Integer, Integer> sizes = new HashMap<>();
        for (Integer value : groups.values()) {
            sizes.compute(value, (k, v) -> v == null ? 1 : v + 1);
        }

        List<Integer> list = new ArrayList<>(sizes.values());
        list.sort(Comparator.reverseOrder());

        result += list.get(0);
        result *= list.get(1);
        result *= list.get(2);
        System.out.println(result);
    }

    record Coordinates(int x, int y, int z) {

    }

    record Distance(Coordinates c1, Coordinates c2, double dist) {

    }
}
