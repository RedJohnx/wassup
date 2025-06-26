import java.util.*;
public class TopoSort {
        public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = sc.nextInt();

        List<List<Integer>> adj=new ArrayList<>();
        int[] inDegree=new int[V];

        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        
        System.out.println("Enter the edges of the graph:");
        for(int i=0;i<E;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            adj.get(u).add(v);
            inDegree[v]++;
        }
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<V;i++){
            if(inDegree[i]==0){
                queue.offer(i);
            }
        }

        List<Integer> TopoOrder=new ArrayList<>();
        while(!queue.isEmpty()){
            int node=queue.poll();
            TopoOrder.add(node);

            for(int neighbour:adj.get(node)){
                inDegree[neighbour]--;
                if(inDegree[neighbour]==0){
                    queue.offer(neighbour);
                }
            }
        }
        if (TopoOrder.size() != V) {
            System.out.println("The graph has a cycle. Topological sort not possible.");
        } else {
            System.out.println("Topological Sort:");
            for (int node : TopoOrder) {
                System.out.print(node + " ");
            }
        }
        sc.close();
    }    
}