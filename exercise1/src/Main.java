import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int[][] mat = {{1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 0, 0, 0, 0, 1, 0, 0, 1}};

        int[] src = {0, 0};
        int[] dest = {3, 4};

        int dist = shortestPath(mat, src, dest);

        if (dist != -1) {
            System.out.println("Shortest Path is " + dist);
        } else {
            System.out.println("Destination cannot be reached");
        }
    }

    static class Node {
        int x, y, distance;

        Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    public static int shortestPath(int[][] mat, int[] src, int[] dest) {
        int m = mat.length;
        int n = mat[0].length;
        boolean[][] visited = new boolean[m][n];

        if (mat[src[0]][src[1]] == 0 || mat[dest[0]][dest[1]] == 0)
            return -1;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(src[0], src[1], 0));
        visited[src[0]][src[1]] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.x == dest[0] && node.y == dest[1])
                return node.distance;

            for (int i = 0; i < 4; i++) {
                int x = node.x + dx[i];
                int y = node.y + dy[i];

                if (x >= 0 && x < m && y >= 0 && y < n && mat[x][y] == 1 && !visited[x][y]) {
                    queue.add(new Node(x, y, node.distance + 1));
                    visited[x][y] = true;
                }
            }
        }

        return -1;
    }
}