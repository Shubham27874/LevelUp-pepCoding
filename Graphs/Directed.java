package Graphs;
import java.util.*;

public class Directed {
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

    public static void constructGraph(){
        for(int i =0; i < N; i++){
            graph[i] = new ArrayList<>();  //java mai space allocate krna padta hai
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

    public static void display(){
        for(int i = 0; i < N; i++){
            System.out.print(i + "->");
            for(Edge e : graph[i]){
                System.out.print("(" + e.v + "," + e.w + ")");
            }
            System.out.println();
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

        return idx;    //will return the value of "v" for the given "u";
    }

    public static void removeEdge(int u ,int v){
        int idx1 = findEdge(u, v);
        int idx2 = findEdge(v, u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVtx(int u){
        for(int i = graph[u].size() - 1; i >= 0; i--){
            int v = graph[u].get(i).v;
            removeEdge(u, v);
        }
    }

    public static void dfs_topo(int src, boolean[] vis, ArrayList<Integer> ans){
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v])   
                dfs_topo(e.v, vis, ans);
        }

        ans.add(src);
    }

    public static void topologicalOrder_DFS(){
        boolean[] vis = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0; i < N; i++){
            if(!vis[i])
                dfs_topo(i, vis, ans);
        }

        for(int ele : ans)
            System.out.print(ele + " ");
    }

    public static void khansAlgo(){
        int[] indegree = new int[N];
        for(int i = 0; i < N; i++)
            for(Edge e : graph[i])
                indegree[e.v]++;

        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<Integer> que = new LinkedList<>();

        for (int i = 0; i < N; i++)
            if (indegree[i] == 0)
                que.addLast(i);


        int level = 0;
        while(que.size() != 0){
            int sz = que.size();
            while(sz-- > 0){
                int rvtx = que.removeFirst();

                ans.add(rvtx);

                for(Edge e : graph[rvtx]){
                    if(--indegree[e.v] == 0)
                    que.addLast(e.v);
                }
            }

            level++;
        }

        for(int ele : ans)
            System.out.print(ele + " ");
    }

    public static boolean isCyclePresent_DFStopo(int src, int[] vis, ArrayList<Integer> ans){
        vis[src] = 0;
        boolean res = false;

        for(Edge e : graph[src]){
            if(vis[e.v] == -1){ //unvisited
                res = res || isCyclePresent_DFStopo(e.v, vis, ans);
            } else if(vis[src] == 0){
                return true;  //cycle is present
            }
        }

        vis[src] = 1;
        ans.add(src);
        return res;
    }

    public static ArrayList<Integer> isCyclePresent_DFS(){
        int[] vis = new int[N];
        Arrays.fill(vis, -1);
        ArrayList<Integer> ans = new ArrayList<>();

        boolean res = false;
        for(int i = 0; i < N; i++){
            if(vis[i] == -1){
                res = res || isCyclePresent_DFStopo(i, vis, ans);
            }
        }

        if(res)
            ans.clear();

        return ans;
    }

    public static void dfs_SCC(ArrayList<Edge>[] ngraph, int src, boolean[] vis, ArrayList<Integer> ans){
        vis[src] = true;
        for(Edge e : ngraph[src]){
            if(!vis[e.v])   
                dfs_SCC(ngraph, e.v, vis, ans);
        }

        ans.add(src);
    }

    public static void kosaraju(){
        boolean[] vis = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0; i < N; i++){
            if(!vis[i])
                dfs_topo(i, vis, ans);
        }

        //Graph Inverse
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for(int i = 0; i < N; i++){
            for(Edge e : graph[i]){
                ngraph[e.v].add(new Edge(i, 1));
            }
        }

        Arrays.fill(vis, false);
        for(int i = ans.size() - 1; i >= 0; i--){
            int ele = ans.get(i);
            if(!vis[ele]){
                ArrayList<Integer> scc = new ArrayList<>();
                dfs_SCC(ngraph, ele, vis, scc);

                for(int e : scc)
                    System.out.print(e + " ");

                System.out.println();
            }
        }
    }

    

}
