import org.junit.Test;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;

public class UndirectedGraphTest {

    @Test
    public void getPathCheck() {
        UndirectedGraph<Integer> graph = createGraphOfIntegers();
        System.out.println(graph.printGraph());
        assertArrayEquals(graph.getPathDFT(new Vertex<>(1), new Vertex<>(5)).toArray(), Stream.of(1, 4, 5).toArray());
    }

    @Test
    public void traverseFunctionCheck() {
        Function<Vertex<String>, String> getName = Vertex::getName;
        UndirectedGraph<String> graph = createGraphString();
        graph.breadthFirstTraversal(new Vertex<>("Bob"), getName);
        System.out.println(graph.printGraph());
    }

    @Test
    public void checkPath() {
        UndirectedGraph graph = createGraph2();
        System.out.println(graph.getPathDFT(new Vertex<>(7), new Vertex<>(10)));
    }

    @Test
    public void createGraphUsers() {
        UndirectedGraph graph = createGraph();
        System.out.println(graph.getPathDFT(new Vertex(new User("Rob", "addr5", 65)),
                new Vertex( new User("Alice", "addr2", 25))));
    }

    private UndirectedGraph<String> createGraphString() {
        UndirectedGraph<String> graph = new UndirectedGraph<>();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", 10, "Alice", 20);
        graph.addEdge("Bob", 10, "Rob", 40);
        graph.addEdge("Alice", 20, "Mark", 30);
        graph.addEdge("Rob", 40, "Mark", 25);
        graph.addEdge("Alice", 25, "Maria", 50);
        graph.addEdge("Rob", 10, "Maria", 55);
        return graph;
    }

    private UndirectedGraph<Integer> createGraphOfIntegers() {
        UndirectedGraph<Integer> graph = new UndirectedGraph<>();
        graph.addVertex(3);
        graph.addVertex(2);
        graph.addVertex(1);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(4, 5);
        graph.addEdge(3, 6);
        graph.addEdge(4, 6);
        graph.addEdge(6, 7);
        return graph;
    }


    private UndirectedGraph<Integer> createGraph2() {
        UndirectedGraph<Integer> graph = new UndirectedGraph<>();
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

    private UndirectedGraph<User> createGraph() {
        UndirectedGraph<User> graph = new UndirectedGraph<>();
        graph.addVertex(new User("Bob", "addr1", 15));
        graph.addVertex(new User("Alice", "addr2", 25));
        graph.addVertex(new User("Mark", "", 45));
        graph.addVertex(new User("Rob", "addr5", 65));
        graph.addVertex(new User("Maria", "addr2", 75));
        graph.addEdge(new User("Bob", "addr1", 15), 10, new User("Alice", "addr2", 25), 20);
        graph.addEdge(new User("Bob", "addr1", 15), 10, new User("Rob", "addr5", 65), 40);
        graph.addEdge(new User("Alice", "addr2", 25), 20, new User("Mark", "", 45), 30);
        graph.addEdge(new User("Rob", "addr5", 65), 40, new User("Mark", "", 45), 25);
        graph.addEdge(new User("Alice", "addr2", 25), 25, new User("Maria", "addr2", 75), 50);
        graph.addEdge(new User("Rob", "addr5", 65), 10, new User("Maria", "addr2", 75), 55);
        return graph;
    }
}
