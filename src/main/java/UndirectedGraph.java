import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UndirectedGraph<T> {
    protected Map<Vertex<T>, Map<Vertex<T>, Integer>> adjacentVertices;

    UndirectedGraph() {
        this.adjacentVertices = new HashMap<>();
    }

    void addVertex(T t) {
        adjacentVertices.putIfAbsent(new Vertex<>(t), new HashMap<>());
    }

    void removeVertex(T t) {
        Vertex<T> v = new Vertex<>(t);
        adjacentVertices.values().forEach(e -> e.remove(v));
        adjacentVertices.remove(v);
    }

    void addEdge(T name1, T name2) {
        Vertex<T> v1 = new Vertex<>(name1);
        Vertex<T> v2 = new Vertex<>(name2);
        adjacentVertices.get(v1).put(v2, 0);
        adjacentVertices.get(v2).put(v1, 0);
    }

    void addEdge(T name1, Integer weight1, T name2, Integer weight2) {
        Vertex<T> v1 = new Vertex<>(name1);
        Vertex<T> v2 = new Vertex<>(name2);
        adjacentVertices.get(v1).put(v2, weight1);
        adjacentVertices.get(v2).put(v1, weight2);
    }

    void removeEdge(T name1, T name2) {
        Vertex v1 = new Vertex<>(name1);
        Vertex v2 = new Vertex<>(name2);
        Map<Vertex<T>, Integer> lst1 = adjacentVertices.get(v1);
        Map<Vertex<T>, Integer> lst2 = adjacentVertices.get(v2);
        if (lst1 != null)
            lst1.remove(v2);
        if (lst2 != null)
            lst2.remove(v1);
    }

    List<Vertex<T>> getAdjVertices(Vertex<T> vertex) {
        return new LinkedList<>(adjacentVertices.get(vertex).keySet());
    }

    Set<Vertex<T>> getSetAdjVertices(Vertex<T> vertex) {
        return adjacentVertices.get(vertex).keySet();
    }

    String printGraph() {
        return adjacentVertices.keySet().stream().map(v -> String.valueOf(v) + adjacentVertices.get(v)).collect(Collectors.joining(" "));
    }

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
                setAdjVertices.stream().filter(v -> !visited.contains(v)).forEach(stack::push);

                if (vertex.equals(destination)) {
                    LinkedList<Vertex<T>> lst = new LinkedList<>(visited);
                    ListIterator<Vertex<T>> li = lst.listIterator(lst.size());
                    Vertex<T> tmp = lst.getLast();
                    boolean isAdjcent;
                    li.previous();
                    while (li.hasPrevious()) {
                        Vertex<T> previous = li.previous();
                        isAdjcent = true;
                        if (tmp != null) {
                            //check adjacent of last element
                            isAdjcent = getAdjVertices(tmp).stream().anyMatch(v -> v.equals(previous));
                        }
                        if (!isAdjcent) {
                            li.remove();
                        } else {
                            break;
                        }
                    }
                    return lst.stream().map(Vertex::getName).collect(Collectors.toList());
                }
            }
        }
        throw new IllegalArgumentException("no path found");
    }

    public void checkInputs(Vertex<T> source, Vertex<T> destination) {
        if (!(adjacentVertices.keySet().stream().anyMatch(v -> v.equals(source)) && adjacentVertices.keySet().stream().anyMatch(v -> v.equals(destination)))) {
            throw new IllegalArgumentException("no vertex in a graph");
        }
    }

    public List<String> breadthFirstTraversal(Vertex<T> source, Function<Vertex<T>, String> func) throws IllegalArgumentException {
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        Optional<Vertex<T>> tmp = adjacentVertices.keySet().stream().filter(v -> v.equals(source)).findFirst();
        if (!tmp.isPresent()) {
            throw new IllegalArgumentException("no vertex in a graph");
        }
        queue.add(tmp.get());
        visited.add(tmp.get());
        while (!queue.isEmpty()) {
            Vertex<T> vertex = queue.poll();
            getAdjVertices(vertex).stream().filter(v -> !visited.contains(v)).forEach(v -> {
                visited.add(v);
                queue.add(v);
            });
        }
        List<String> result = new ArrayList<>();
        for (Vertex<T> vertex : visited) {
            result.add(func.apply(vertex));
        }
        return result;
    }
}