import java.util.*;

public class Dijkstra {

    static class Pair implements Comparable<Pair> {
        int vertex;
        int dist;

        Pair(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        // This method ensures that the priority queue orders pairs by distance
        public int compareTo(Pair other) {
            return this.dist - other.dist;
        }
    }

    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(S, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int u = current.vertex;
            int d = current.dist;

            if (d > dist[u]) continue;

            for (ArrayList<Integer> neighbor : adj.get(u)) {
                int v = neighbor.get(0);
                int wt = neighbor.get(1);
                if (dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                    pq.offer(new Pair(v, dist[v]));
                }
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

        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        System.out.println("Enter edges in the format: u v weight");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int wt = sc.nextInt();
            ArrayList<Integer> edge1 = new ArrayList<>();
            edge1.add(v);
            edge1.add(wt);
            adj.get(u).add(edge1);
        }

        System.out.print("Enter source vertex: ");
        int S = sc.nextInt();

        int[] dist = dijkstra(V, adj, S);

        System.out.println("Shortest distances from source:");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }

        sc.close();
    }
}