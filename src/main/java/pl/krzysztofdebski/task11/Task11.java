package pl.krzysztofdebski.task11;

import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphBuilder;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.readAllLines;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;

public class Task11 {

    public static void main(String[] args) throws IOException {
       //  String input = "src/main/resources/task11/11-sample.input";
        String input = "src/main/resources/task11/11-task.input";

        GraphBuilder<String, DefaultEdge, ? extends DefaultDirectedGraph<String, DefaultEdge>> builder
            = DefaultDirectedGraph.createBuilder(DefaultEdge.class);

        for (String line : readAllLines(Path.of(input))) {
            String start = substringBefore(line, ":");

            for (String end : substringAfter(line, ": ").split(" ")) {
                builder.addEdge(start, end, new DefaultEdge());
            }
        }

        DefaultDirectedGraph<String, DefaultEdge> graph = builder.build();

        AllDirectedPaths<String, DefaultEdge> allDirectedPaths = new AllDirectedPaths<>(graph);
        long result = allDirectedPaths.getAllPaths("you", "out", false, 600).size();

        System.out.println(result);
    }
}
