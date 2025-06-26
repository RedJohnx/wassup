import java.util.Arrays;
import java.util.Scanner;

public class KruskalAlgo {
    static class Edge implements Comparable<Edge>{
        int u,v,weight;

        Edge(int u,int v,int weight){
            this.u=u;
            this.v=v;
            this.weight=weight;
        }
@Override
            public int compareTo(Edge other){
                return this.weight-other.weight;
            }
        }
        static int find(int[] parent,int i){
            if(parent[i]!=i)
            parent[i]=find(parent,parent[i]);
            return parent[i];
        }
        static void union(int[] parent,int[] rank,int x,int y){
            int rootX=find(parent,x);
            int rootY=find(parent,y);

            if(rank[rootX]<rank[rootY]){
                parent[rootX]=rootY;
            }
           else if(rank[rootX]>rank[rootY]){
                parent[rootY]=rootX;
            }
            else{
                parent[rootY]=rootX;
                rank[rootX]++;
            }
        }
      public static void main(String[] args) {
           Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        Edge[] edges=new Edge[E];
        System.out.println("Enter the edges and weight");
        for(int i=0;i<E;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            int w=sc.nextInt();
            edges[i]=new Edge(u,v,w);
        }
        Arrays.sort(edges);
        int[] parent=new int[V];
        int[] rank=new int[V];
        for(int i=0;i<V;i++)
        parent[i]=i;

        int mstWeight = 0;
        System.out.println("Edge \tWeight");
        
        for (Edge edge : edges) {
            int uSet = find(parent, edge.u);
            int vSet = find(parent, edge.v);

            if (uSet != vSet) {
                System.out.println(edge.u + " - " + edge.v + "\t" + edge.weight);
                mstWeight += edge.weight;
                union(parent, rank, uSet, vSet);
            }
        }
        System.out.println("Total weight of MST: " + mstWeight);
        sc.close();
        }
    }