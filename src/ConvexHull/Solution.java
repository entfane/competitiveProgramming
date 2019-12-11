package ConvexHull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int numberOfVertex = scanner.nextInt();
        Node[] vertex = getInput(numberOfVertex);
        final Node polarPoint = findPolarPoint(vertex);
        vertex = sortVertexByPolarPoint(vertex, polarPoint);

        for (int i = 0; i < numberOfVertex; i++) {

            System.out.println(vertex[i].getX() + " " + vertex[i].getY());

        }

    }

    private static Node[] sortVertexByPolarPoint(Node[] vertex, Node polarPoint) {

        Node[] copyOfVertex = vertex;

        Comparator<Node> comparator = new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                return -crossProduct(polarPoint, o1, polarPoint, o2);
            }

        };

        Arrays.sort(copyOfVertex, comparator);
        return copyOfVertex;


    }

    private static Node findPolarPoint(Node[] vertex) {

        Node tempPolarPoint = vertex[0];
        boolean tempPointIsLowerThanTempPolarPoint;
        boolean tempPointIsSameHeightAsTempPolarPoint;
        boolean tempPointIsLeftToTempPoint;
        boolean isValidPolarPoint;

        for (int i = 1; i < vertex.length; i++) {

            tempPointIsLowerThanTempPolarPoint = vertex[i].getY() < tempPolarPoint.getY();
            tempPointIsSameHeightAsTempPolarPoint = vertex[i].getY() == tempPolarPoint.getY();
            tempPointIsLeftToTempPoint = vertex[i].getX() < tempPolarPoint.getX();
            isValidPolarPoint = tempPointIsLowerThanTempPolarPoint || (tempPointIsSameHeightAsTempPolarPoint && tempPointIsLeftToTempPoint);

            if (isValidPolarPoint) {

                tempPolarPoint = vertex[i];

            }

        }

        return tempPolarPoint;

    }

    private static Node[] getInput(int numberOfVertex) {

        Node[] vertex = new Node[numberOfVertex];

        for (int i = 0; i < numberOfVertex; i++) {

            vertex[i] = new Node();
            vertex[i].setX(scanner.nextInt());
            vertex[i].setY(scanner.nextInt());

        }

        return vertex;

    }

    private static int crossProduct(Node p1, Node p2, Node p3, Node p4) {

        int vector1_2X = p2.getX() - p1.getX();
        int vector1_2Y = p2.getY() - p1.getY();
        int vector3_4X = p4.getX() - p3.getX();
        int vector3_4Y = p4.getY() - p3.getY();
        int result = vector1_2X * vector3_4Y - vector1_2Y * vector3_4X;
        return result;

    }

}

