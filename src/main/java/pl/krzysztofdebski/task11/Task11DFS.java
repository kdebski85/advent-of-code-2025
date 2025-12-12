package pl.krzysztofdebski.task11;

import org.jgrapht.event.TraversalListenerAdapter;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphBuilder;
import org.jgrapht.traverse.DepthFirstIterator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Files.readAllLines;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;

public class Task11DFS {

    public static void main(String[] args) throws IOException {
        // String input = "src/main/resources/task11/11-sample.input";
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

        Map<String, Integer> results = new HashMap<>();

        DepthFirstIterator<String, DefaultEdge> depthFirstIterator = new DepthFirstIterator<>(graph);
        depthFirstIterator.addTraversalListener(new TraversalListenerAdapter<>() {
            @Override
            public void vertexFinished(VertexTraversalEvent<String> e) {
                String vertex = e.getVertex();
                if (vertex != null) {
                    if (vertex.equals("out")) {
                        results.put("out", 1);
                    } else {
                        int sum = 0;
                        for (DefaultEdge edge : graph.outgoingEdgesOf(vertex)) {
                            String edgeTarget = graph.getEdgeTarget(edge);
                            sum += results.get(edgeTarget);
                        }
                        results.put(vertex, sum);
                    }
                }
            }
        });

        while (depthFirstIterator.hasNext()) {
            depthFirstIterator.next();
        }

        System.out.println(results.get("you"));
    }
}
