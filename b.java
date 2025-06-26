import java.util.*;

public class BellmanFord {
    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        int[] dist = new int[V];
        Arrays.fill(dist, (int)(1e8));
        dist[S] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (ArrayList<Integer> edge : edges) {
                int u = edge.get(0);
                int v = edge.get(1);
                int wt = edge.get(2);
                if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int wt = edge.get(2);
            if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                return new int[] { -1 };
            }
        }

        return dist;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

        System.out.println("Enter edges in the format: u v weight");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int wt = sc.nextInt();
            ArrayList<Integer> edge = new ArrayList<>();
            edge.add(u);
            edge.add(v);
            edge.add(wt);
            edges.add(edge);
        }

        System.out.print("Enter source vertex: ");
        int S = sc.nextInt();

        int[] dist = BellmanFord.bellman_ford(V, edges, S);

        if (dist.length == 1 && dist[0] == -1) {
            System.out.println("Negative weight cycle detected");
        } else {
            System.out.println("Shortest distances from source:");
            for (int i = 0; i < V; i++) {
                System.out.println("Vertex " + i + ": " + (dist[i] == (int)(1e8) ? "INF" : dist[i]));
            }
        }

        sc.close();
    }
}