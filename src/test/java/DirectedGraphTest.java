import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;

public class DirectedGraphTest {

  @Test
  public void getPathCheck() {
      DirectedGraph<Integer> graph = createGraph();
      assertArrayEquals(graph.getPathDFT(new Vertex<>(1), new Vertex<>(9)).toArray(), Stream.of(1, 7, 9).toArray());
  }


    @Test(expected = IllegalArgumentException.class)
    public void getPathException() {
        DirectedGraph<Integer> graph = createGraph();
        graph.getPathDFT(new Vertex<>(1), new Vertex<>(4));

    }

    private DirectedGraph<Integer> createGraph() {
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        graph.addVertex(3);
        graph.addVertex(2);
        graph.addVertex(1);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);
        graph.addVertex(9);
        graph.addVertex(10);
        graph.addEdge(1, 5);
        graph.addEdge(1, 6);
        graph.addEdge(1, 7);
        graph.addEdge(1, 10);
        graph.addEdge(1, 2);
        graph.addEdge(2, 9);
        graph.addEdge(2, 3);
        graph.addEdge(2, 10);
        graph.addEdge(2, 6);
        graph.addEdge(3, 6);
        graph.addEdge(4, 5);
        graph.addEdge(4, 8);
        graph.addEdge(4, 9);
        graph.addEdge(6, 7);
        graph.addEdge(6, 8);
        graph.addEdge(7, 8);
        graph.addEdge(7, 9);
        return graph;
    }
}
