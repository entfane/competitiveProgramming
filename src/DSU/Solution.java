package DSU;

import IO.InputOutput;

import java.io.IOException;

public class Solution {

    /*

        2 types of requests:
            1) 0 [int] [int] - unions two sets containing first and second node
            2) 1 [int] [int] - checks if 2 nodes are from the same set

     */

    public static void main(String[] args) throws IOException {
        InputOutput io = new InputOutput();
        int[][] nodes;
        int numberOfNodes = io.nextInt();
        nodes = new int[numberOfNodes][2];      // 0 - parent; 1 - size of subtree
        for (int i = 0; i < numberOfNodes; i++) {       //presetting of nodes
            nodes[i][0] = i;        //each node is set itself
            nodes[i][1] = 1;
        }
        int numberOfRequests = io.nextInt();
        for (int i = 0; i < numberOfRequests; i++) {
            if (io.nextInt() == 0) {        // union request
                union(nodes, io.nextInt(), io.nextInt());
            } else {        // check that 2 nodes are in same set
                System.out.println(findSet(nodes, io.nextInt()) == findSet(nodes, io.nextInt()));
            }
        }
    }

    private static void union(int[][] nodes, int node1, int node2) {
        int node1Leader= findSet(nodes, node1);
        int node2Leader = findSet(nodes, node2);
        if (findSet(nodes, node1) != findSet(nodes, node2)) {       // check if nodes are from different sets
            if (nodes[node1Leader][1] < nodes[node2Leader][1]) {        // if first set is smaller than second
                nodes[node1Leader][0] = node2Leader;
                nodes[node2Leader][1] += nodes[node1Leader][1];     // increases the size of set
            } else {
                nodes[node2Leader][0] = node1Leader;
                nodes[node1Leader][1] += nodes[node2Leader][1];     // increases the size of set
            }
        }
    }

    private static int findSet(int[][] nodes, int node) {
        if (nodes[node][0] == node) {       // is parent
            return node;
        } else {
            return nodes[node][0] = findSet(nodes, nodes[node][0]);      // makes parent for current node the leader of set
        }
    }
}
