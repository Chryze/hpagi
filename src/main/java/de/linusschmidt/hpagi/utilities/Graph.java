package de.linusschmidt.hpagi.utilities;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    public class Node {

        private double state;

        private List<Node> neighbours;

        public Node(double state) {
            this.state = state;

            this.neighbours = new ArrayList<>();
        }

        public void addNeighbour(Node node) {
            this.neighbours.add(node);
        }

        public double getState() {
            return state;
        }
    }

    private List<Node> nodes;

    public Graph() {

    }

    public void addNode(Node node) {

    }

    public List<Node> getNodes() {
        return nodes;
    }
}
