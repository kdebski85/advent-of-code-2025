package pl.krzysztofdebski.task9;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;
import static pl.krzysztofdebski.utils.ParsingUtils.unsignedInts;

public class Task9b {

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
            main:
            for (int j = i + 1; j < records.size(); j++) {
                C c2 = records.get(j);
                long diffX = Math.abs(c1.x - c2.x) + 1;
                long diffY = Math.abs(c1.y - c2.y) + 1;
                long area = diffX * diffY;
                if (area > result) {
                    int maxy = Math.max(c1.y, c2.y);
                    int miny = Math.min(c1.y, c2.y);
                    int maxx = Math.max(c1.x, c2.x);
                    int minx = Math.min(c1.x, c2.x);
                    for (int k = 0; k < records.size(); k++) {
                        C k1 = records.get(k);
                        C k2 = records.get(k == records.size() - 1 ? 0 : k + 1);
                        if (k1.x == k2.x && k1.x > minx && k1.x < maxx) {
                            int minky = Math.min(k1.y, k2.y);
                            int maxky = Math.max(k1.y, k2.y);
                            for (int y = minky + 1; y < maxky; y++) {
                                if (y > miny && y < maxy) {
                                    continue main;
                                }
                            }
                        } else if (k1.y > miny && k1.y < maxy) {
                            int minkx = Math.min(k1.x, k2.x);
                            int maxkx = Math.max(k1.x, k2.x);
                            for (int x = minkx + 1; x < maxkx; x++) {
                                if (x > minx && x < maxx) {
                                    continue main;
                                }
                            }
                        }
                    }

                    result = area;
                }
            }
        }

        System.out.println(result);
    }

    private record C(int x, int y) {

    }
}
