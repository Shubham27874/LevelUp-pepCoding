package Graphs;
import java.util.*;

public class l001 {

    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }

    }

    static int N = 7;

    @SuppressWarnings("unchecked")
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v, int w){
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }   

    public static void display(){
        for(int i = 0; i < N; i++){
            System.out.print(i + " -> ");
            for(Edge e : graph[i]){
                System.out.print("(" + e.v + "," + e.w + ")");
            }
            System.err.println();
        }
    }

    public static int findEdge(int u, int v){
        int idx = -1;

        for(int i = 0; i < graph[u].size(); i++){
            
            if(graph[u].get(i).v == v){
                idx = i;
                break;
            }
        }

        return idx;
    }

    public static void removeEdge(int u ,int v){
        int idx1 = findEdge(u, v);
        int idx2 = findEdge(v, u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);

    }

    public static void removeVtx(int u){
        for(int i = 0; i < graph[u].size() - 1; i--){
            int v = graph[u].get(i).v;
            removeEdge(u, v);
        }
    }

    public static boolean hasPath(int src, int dest, boolean[] vis){
        boolean res = false;
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                res = res || hasPath(e.v, dest, vis);
            }
        }
        return res;
    }

    public static int printAllPath(int src, int dest, boolean[] vis, String psf){
        if(src == dest){
            System.out.println(psf + dest);
            return 1;
        }

        int count = 0;
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[src]){
                count += printAllPath(e.v, dest, vis, psf + src); 
            }
        }
        vis[src] = false;
        return count;
    }

    public static class heavyPair{
        int weight = 0;
        String Path = "";

        heavyPair(int weight, String Path){
            this.weight = weight;
            this.Path = Path;
        }
    }

    public static heavyPair heavyPath(int src ,int dest, boolean[] vis){
        if(src == dest){
            heavyPair base = new heavyPair(0 , dest + "");
            return base;
        }

        heavyPair myAns = new heavyPair(-(int) 1e8, "");

        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                heavyPair recAns =  heavyPath(e.v, dest, vis);
                if(recAns.weight != -1e8 && recAns.weight + e.w > myAns.weight){
                    myAns.weight = recAns.weight + e.w;
                    myAns.Path = src + " " + recAns.Path;
                } 
            }
        }

        vis[src] = false;
        return myAns;
    }

    public static void constructGraph(){
        for(int i =0; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 40);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);

        addEdge(6, 0, 3);
    }


    public static void main(String[] args){
        constructGraph();
        //display();
        //boolean[] vis = new boolean[N];
        //System.out.println(printAllPath(0, 6, vis, ""));
    }
}
