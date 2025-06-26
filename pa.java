import java.util.*;

public class PrimsAlgo {
    static class Edge{
        int vertex;
        int weight;

        Edge(int v,int w){
            vertex=v;
            weight=w;
        }
    }

   static class Node implements Comparable<Node>{
        int vertex;
        int weight;

        Node(int v,int w){
            vertex=v;
            weight=w;
        }
    @Override
      public int compareTo(Node other){
            return this.weight-other.weight;
        }
    }
   public static void primMST(List<List<Edge>> adj,int V){
    boolean[] inMST=new boolean[V];
    int[] key=new int[V];
    int[] parent=new int[V];
    PriorityQueue<Node> pq=new PriorityQueue<>();

    Arrays.fill(key,Integer.MAX_VALUE);
    Arrays.fill(parent,-1);
    key[0]=0;
    pq.offer(new Node(0,0));

    while(!pq.isEmpty()){
        int u=pq.poll().vertex;
        inMST[u]=true;

        for(Edge edge:adj.get(u)){
            int v=edge.vertex;
            int w=edge.weight;

            if(!inMST[v] && w<key[v]){
                key[v]=w;
                parent[v]=u;
                pq.offer(new Node(v,key[v]));
            }
        }
    }

    System.out.println("Edge \tWeight");
    int totalMSTWeight = 0;
    for(int i=1;i<V;i++){
        System.out.println(parent[i]+"-"+i+"\t\t"+key[i]);
        totalMSTWeight += key[i];
    }
    System.out.println("Total weight of MST: " + totalMSTWeight);
   }

   public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       System.out.println("Enter the number of vertices");
       int V=sc.nextInt();
       System.out.println("Enter the number of Edges");
       int E=sc.nextInt();
 
       List<List<Edge>> adj=new ArrayList<>();
       for(int i=0;i<V;i++){
        adj.add(new ArrayList<>());
       }
       System.out.println("Enter edges (u v weight):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            adj.get(u).add(new Edge(v,w));
            adj.get(v).add(new Edge(u,w));        
   }
   long start = System.nanoTime();
   primMST(adj,V);
   long end = System.nanoTime();
   System.out.println("Time eclapsed: "+(end - start)+ " ns");
   sc.close();

}
}