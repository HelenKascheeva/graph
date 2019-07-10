import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class DirectedGraph<T> extends UndirectedGraph<T>{

    @Override
    void addEdge(T name1, T name2) {
        Vertex<T> v1 = new Vertex<>(name1);
        Vertex<T> v2 = new Vertex<>(name2);
        adjacentVertices.get(v1).put(v2, 0);
    }

    @Override
    void addEdge(T name1, Integer weight1, T name2, Integer weight2) {
        Vertex<T> v1 = new Vertex<>(name1);
        Vertex<T> v2 = new Vertex<>(name2);
        adjacentVertices.get(v1).put(v2, weight1);
    }

    @Override
    List<T> getPathDFT(Vertex<T> source, Vertex<T> destination) throws IllegalArgumentException {
        checkInputs(source, destination);
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        Stack<Vertex<T>> stack = new Stack<>();
        stack.push(source);
        while (!stack.isEmpty()) {
            Vertex<T> vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);

                Set<Vertex<T>> setAdjVertices = getSetAdjVertices(vertex);
                if (vertex.equals(destination)) {
                    return visited.stream().map(Vertex::getName).collect(Collectors.toList());
                }
                if (setAdjVertices.isEmpty()) {
                    visited.remove(vertex);
                }
                else {
                    setAdjVertices.stream().filter(v -> !visited.contains(v)).forEach(stack::push);
                }

            }
        }
        throw new IllegalArgumentException("no path found");
    }
}