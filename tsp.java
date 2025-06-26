import java.util.*;

public class tsp {
    static int[][] dist;
    static int[][] dp;
    static int[][] parent;
    static int n;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        n = sc.nextInt();

        dist = new int[n][n];
        System.out.println("Enter the distance matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                dist[i][j] = sc.nextInt();

        int size = 1 << n;
        dp = new int[n][size];
        parent = new int[n][size];

        for (int[] row : dp) Arrays.fill(row, INF);
        for (int[] row : parent) Arrays.fill(row, -1);
        dp[0][1] = 0;

        for (int mask = 1; mask < size; mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) != 0) {
                    for (int v = 0; v < n; v++) {
                        if ((mask & (1 << v)) == 0) {
                            int nextMask = mask | (1 << v);
                            int newCost = dp[u][mask] + dist[u][v];
                            if (newCost < dp[v][nextMask]) {
                                dp[v][nextMask] = newCost;
                                parent[v][nextMask] = u;
                            }
                        }
                    }
                }
            }
        }

        int minCost = INF;
        int lastCity = -1;
        for (int i = 1; i < n; i++) {
            int cost = dp[i][size - 1] + dist[i][0];
            if (cost < minCost) {
                minCost = cost;
                lastCity = i;
            }
        }

        System.out.println("Minimum cost: " + minCost);

        List<Integer> path = new ArrayList<>();
        int mask = size - 1;
        int current = lastCity;
        while (current != -1) {
            path.add(current);
            int temp = parent[current][mask];
            mask = mask ^ (1 << current);
            current = temp;
        }

        Collections.reverse(path);
        System.out.print("Path: ");
        for (int city : path) {
            System.out.print(city + " ");
        }
        System.out.println("0");
    }
}