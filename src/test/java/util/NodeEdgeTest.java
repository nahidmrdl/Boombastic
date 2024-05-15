package util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import util.Edge;
import util.Node;

public class NodeEdgeTest {

    @Test
    void testNodeCreation() {
        Node node = new Node(1, 2);
        assertEquals(1, node.x);
        assertEquals(2, node.y);
        assertEquals(Double.POSITIVE_INFINITY, node.minDistance);
        assertNull(node.predecessor);
        assertNotNull(node.edges);
    }

    @Test
    void testEdgeCreation() {
        Node node1 = new Node(1, 2);
        Node node2 = new Node(3, 4);
        double cost = 5.0;

        Edge edge = new Edge(node2, cost);
        assertEquals(node2, edge.target);
        assertEquals(cost, edge.cost);
    }

    @Test
    void testNodeEquality() {
        Node node1 = new Node(1, 2);
        Node node2 = new Node(1, 2);
        Node node3 = new Node(3, 4);

        assertEquals(node1, node2);
        assertNotEquals(node1, node3);
    }
}
