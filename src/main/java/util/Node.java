package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    public int x;
    public int y;
    public List<Edge> edges = new ArrayList<>();
    public double minDistance = Double.POSITIVE_INFINITY;
    public Node predecessor = null;  // To trace the path

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return x == node.x && y == node.y;
    }
}
