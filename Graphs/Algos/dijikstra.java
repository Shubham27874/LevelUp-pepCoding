package Graphs.Algos;
import java.util.*;

public class dijikstra {
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w){
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display(int N, ArrayList<Edge>[] graph){
        for(int i = 0; i < N; i++){
            System.out.print(i + "->");
            for(Edge e : graph[i]){
                System.out.print("(" + e.v + "," + e.w + ")");
            }
            System.out.println();
        }
    }

    public class dijikstraPair{
        int vtx = 0;
        int wsf = 0;

        // if we wangt to create graph
        // int par = 0;
        // int wt = 0;

        dijikstraPair(int vtx, int wsf){
            this.vtx = vtx;
            this.wsf = wsf;
        }
    }

    public static void dijikstra_01(int src, int N, ArrayList<Edge>[] graph){
        PriorityQueue<dijikstraPair> que = new PriorityQueue<>((a, b) ->{
            return a.wsf - b.wsf;
        });

        que.add(new dijikstraPair(src, 0));
        int noOfEdges = 0;

        boolean[] vis = new boolean[N];

        while (noOfEdges < N - 1) { // when you know graph is connected.
            dijikstraPair p = que.remove();
            if(vis[p.vtx])
                continue;  //cycle

            if(p.vtx != src)
                noOfEdges++;

            vis[p.vtx] = true;
            for(Edge e : graph[p.vtx]){
                if(!vis[e.v]){
                    que.add(new dijikstraPair(e.v, p.wsf + e.w));
                }
            }
        }    
    }

    //better
    public static void dijikstra_02(int src, int N, ArrayList<Edge>[] graph){
        PriorityQueue<dijikstraPair> que = new PriorityQueue<>((a, b) ->{
            return a.wsf - b.wsf;
        });

        que.add(new dijikstraPair(src, 0));
        int noOfEdges = 0;

        boolean[] vis = new boolean[N];
        int[] dis = new int[N];
        int[] par = new int[N];

        while (noOfEdges < N - 1) { // when you know graph is connected.
            dijikstraPair p = que.remove();
            if(vis[p.vtx])
                continue;  //cycle

            if(p.vtx != src)
                noOfEdges++;

            vis[p.vtx] = true;
            for(Edge e : graph[p.vtx]){
                if(!vis[e.v] && p.wsf + e.w < dis[e.v]){
                    dis[e.v] = e.w + p.wsf;
                    par[e.v] = p.vtx;
                    que.add(new dijikstraPair(e.v, p.wsf + e.w));
                }
            }
        }    
    }
}
