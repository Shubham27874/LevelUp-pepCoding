package Graphs;
import java.util.*;

public class l002 {
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

    public static int hamiltonianCycleandPath(int src, int osrc, boolean[] vis, String psf, int totalNoedges){
        if(totalNoedges == N - 1) {
            int idx = findEdge(src, osrc);
            if(idx != -1)
                System.out.print("Cycle" + psf + src + " ");
            else
                System.out.print("Path" + psf + src + " ");

            System.out.println();
            return 1;
        }

        vis[src] = true;
        int count = 0;
        for(Edge e : graph[src]){
            if(!vis[e.v])
                count += hamiltonianCycleandPath(e.v, osrc, vis, psf + src, totalNoedges + 1);
        }

        vis[src] = false;
        return count;
    }

    public static void hamiltonianCycleandPath(int src){
        boolean[] vis = new boolean[N];
        hamiltonianCycleandPath(src, src, vis, " ", 0);
    }   

    public static void dfs(int src, boolean[] vis, ArrayList<Integer> ans){ 
        
        vis[src] = true;

        for(Edge e : graph[src]){
            if(!vis[e.v])
                dfs(e.v, vis, ans);
        }

        vis[src] = false;
        ans.add(src);
    }

    public static int gcc(){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[N];
        int components = 0;

        for(int i = 0; i < N; i++){
            if(!vis[i]){
                dfs(i, vis, ans);
                res.add(ans);
                components++;
            }
        }

        return components;
    }   

    //LeetCode 200 - NumberOfIslands 
    public void dfsIsland(int i, int j, int n, int m, boolean[] vis, char[][] grid, int[][] dir){
        grid[i][j] = '0';

        for(int d = 0; d < dir.length; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1'){
                dfsIsland(r, c, n, m, vis, grid, dir);
            }
        }
    }

    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0)
            return 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[] vis = new boolean[Math.max(n, m)];
        int count = 0;
        
        int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == '1'){
                    count++;
                    dfsIsland(i, j, n, m, vis, grid, dir);
                }
            }
        }

        return count;
    }

    //LeetCode 695 - Max Area of Island
    public int dfsArea(int i, int j, int n, int m, boolean[] vis, int[][] grid, int[][] dir){
        grid[i][j] = 0;
        int area = 1;
        for(int d = 0; d < dir.length; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1){
                area += dfsArea(r, c, n, m, vis, grid, dir);
            }
        }

        return area;
    }

    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0)
            return 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[] vis = new boolean[Math.max(n, m)];
        //int count = 0;
        
        int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        int Area = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    Area = Math.max(Area, dfsArea(i, j, n, m, vis, grid, dir));
                }
            }
        }

        return Area;
    }
    
    //LeetCode 463 - Island Perimeter
    public int islandPerimeter(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0)
            return 0;

        int nbrs = 0;
        int count = 0;
        
        int n = grid.length;
        int m = grid[0].length;

        int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    count++;
                    for(int d = 0; d < dir.length; d++){
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                            nbrs++;
                    }
                }
            }
        }

        return count * 4 - nbrs;
    }

    //LeeCode 130 - Surrounded Regions
    public void surroundigsRegionsDFS(int i, int j, int n, int m, char[][] grid, int[][] dir){
        grid[i][j] = '#';
        for(int d = 0; d < dir.length; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O')
                surroundigsRegionsDFS(r, c, n, m, grid, dir);
        }
    }

    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0)
            return;

        int n = board.length;
        int m = board[0].length;

        int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

        for(int i = 0; i < n; i++){
            if(board[i][0] == 'O')
                surroundigsRegionsDFS(i, 0, n, m, board, dir);
            
            if(board[i][m - 1] == 'O')
                surroundigsRegionsDFS(i, m - 1, n, m, board, dir);
        }

        for(int i = 0; i < m; i++){
            if(board[0][i] == 'O')
                surroundigsRegionsDFS(0, i, n, m, board, dir);
            
            if(board[n - 1][i] == 'O')
                surroundigsRegionsDFS(n -1, i, n, m, board, dir);
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                
                if(board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    //Leetcode 1091
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = n;
        
        if(n < 0 || m < 0)
            return -1;

        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;

        LinkedList<Integer> que = new LinkedList<>();

        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        grid[0][0] = 1;
        que.push(0);

        int level = 1;
        while(que.size() != 0){
            int sz = que.size();
            while(sz-- > 0){
                int idx = que.removeFirst();

                int r = idx / m;
                int c = idx % m;

                if(r == n - 1 && c == m - 1){
                    return level;
                }

                for(int d = 0; d < dir.length; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 0){
                        grid[x][y] = 1;
                        que.addLast(x * m + y);
                    }
                }
            }

            level++;
        }

        return -1;
    }

    //Leetcode 785
    public boolean isBipartite_(int[][] graph, int[] vis, int src) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        int color = 0;  // 0 : red, 1 : green
        boolean isCycle = false;

        while(que.size() != 0){
            int sz = que.size();
            while(sz-- > 0){
                int rvtx = que.removeFirst();

                if(vis[rvtx] != -1){
                    isCycle = true;
                    if(vis[rvtx] != color) return false;

                    continue;
                }

                vis[rvtx] = color;
                for(int v : graph[rvtx]){
                    if(vis[v] == -1)
                        que.addLast(v);
                }
            }
            color = (color + 1) % 2;
        }

        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        // -1 : not visited, 0 : red, 1 : green
        int[] vis = new int[n];
        Arrays.fill(vis, -1);

        boolean res = true;
        for(int i = 0; i < n; i++){
            if(vis[i] == -1){
                res = res && isBipartite_(graph, vis, i);
            }
        }

        return res;
    }

    //Leetcode 994
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = {{0,1}, {0,-1}, {-1, 0}, {1,0}};
        int time = 0;
        int freshOranges = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 2)
                    que.addLast(i * m + j);
                else if(grid[i][j] == 1)
                    freshOranges++;
            }
        }

        if(freshOranges == 0)
            return 0;

        while(que.size() != 0){
            int sz = que.size();

            while(sz-- > 0){
                int idx = que.removeFirst();
                int r = idx / m;
                int c = idx % m;

                for(int d = 0; d < dir.length; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1){
                        freshOranges--;
                        grid[x][y] = 2;
                        que.addLast(x * m + y);

                        if(freshOranges == 0)
                            return time + 1;
                    }
                }
            }

            time++;
        }

        return -1;
    }

    //286
    void wallsAndGates(int[][] rooms){

        int n = rooms.length;
        int m = rooms[0].length;

        int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        LinkedList<Integer> que = new LinkedList<>();
        int countOfRooms = 0, distance = 0;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (rooms[i][j] == 0) // gates
                    que.addLast(i * m + j);
                else if (rooms[i][j] == 2147483647)
                    countOfRooms++;

        while (que.size() != 0){
            int size = que.size();
            while (size-- > 0){
                int idx = que.removeFirst();
                que.pop();
                int r = idx / m;
                int c = idx % m;

                for (int d = 0; d < 4; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    if (x >= 0 && y >= 0 && x < n && y < m && rooms[x][y] == 2147483647){
                        countOfRooms--;
                        rooms[x][y] = distance + 1;
                        que.addLast(x * m + y);

                        if (countOfRooms == 0)
                            return;
                    }
                }
            }
            distance++;
        }
    }

    //Leetcode 207
    public static ArrayList<Integer> khansAlgo(int N, ArrayList<Integer>[] graph){
        int[] indegree = new int[N];
        for(int i = 0; i < N; i++)
            for(int e : graph[i])
                indegree[e]++;

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

                for(int e : graph[rvtx]){
                    if(--indegree[e] == 0)
                    que.addLast(e);
                }
            }

            level++;
        }

        return ans;
    }

    public boolean canFinish(int N, int[][] arr) {
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int[] ar : arr){
            graph[ar[0]].add(ar[1]);
        }

        return khansAlgo(N, graph).size() == N;
    }

    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] indegree = new int[n][m];
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                for(int d = 0; d < 4; d++){
                    int x = i + dir[d][0];
                    int y = j = dir[d][1];

                    if(x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[i][j])
                        indegree[x][y]++;
                }


        LinkedList<Integer> que = new LinkedList<>();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(indegree[i][j] == 0)
                    que.addLast(i * m + j);


        int level = 0;
        while(que.size() != 0){
            int sz = que.size();
            while(sz-- > 0){
                int idx = que.removeFirst();

                int r = idx / m;
                int c = idx % m;

                for(int d = 0; d < 4; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[r][c] && --indegree[x][y] == 0)
                        que.addLast(x * m + y);

                }
            }

            level++;
        }

        return level;
    }

    //Leetcode 684
    static int[] par;

    public int findPar(int u){
        return par[u] == -1 ? u : (par[u] = findPar(par[u]));
    }

    public int[] findRedundantConnection(int[][] edges) {
        int N = edges.length + 1;
        par = new int[N];
        Arrays.fill(par, -1);
        
        for(int[] edge : edges){
            int p1 = findPar(edge[0]);
            int p2 = findPar(edge[1]);

            if(p1 != p2){
                par[p1] = p2;
            } else {
                return edge;
            }
        }

        return new int[0];
    }

    //Leetcode 1061
    public static int findPar_(int u){
        return par[u] == u ? u : (par[u] = findPar_(par[u]));
    }

    public static String smallestEquivaleString(String A, String B, String S){
        par = new int[26];
        for(int i = 0; i < 26; i++)
            par[i] = i;

        for(int i = 0; i < A.length(); i++){
            int p1 = findPar_(A.charAt(i) - 'a');
            int p2 = findPar_(B.charAt(i) - 'a');

            par[p1] = Math.min(p1, p2);
            par[p2] = Math.min(p1, p2);
        }

        StringBuilder ans = new StringBuilder();
        for(int i = 0 ; i < S.length(); i++){
            char ch = (char)(findPar_(S.charAt(i) - 'a') + 'a');
            ans.append(ch);
        }
        
        return ans.toString();
    } 

    //Leetcode 839
    public boolean isSimilar(String s1, String s2){
        int count = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i) && ++count > 2)
                return false;
        }

        return true;
    }

    public int numSimilarGroups(String[] strs) {
        int count = strs.length;
        int n = strs.length;

        for(int i = 0; i < n; i++)
            par[i] = i;

        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(isSimilar(strs[i], strs[j])){
                    int p1 = findPar_(i);
                    int p2 = findPar_(j);

                    if(p1 != p2){
                        par[p1] = p2;
                        count--;
                    }
                }
            }
        }

        return count;
    }

    //Leetcode 305
    public ArrayList<Integer> numIslands(int m, int n, int[][] positions){
        par = new int[m * n];
        Arrays.fill(par, -1);

        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[][] grid = new int[m][n];
        int count = 0;
        ArrayList<Integer> ans = new ArrayList<>();

        for(int[] point : positions){
            int i = point[0];
            int j = point[1];

            if(grid[i][j] != 1){

                grid[i][j] = 1;
                count++;

                for(int d = 0; d < 4; d++){
                    int x = i + dir[d][0];
                    int y = j + dir[d][1];

                    if(x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 1){
                        int p1 = findPar(i * m + j);
                        int p2 = findPar(x * m + y);

                        if(p1 != p2){
                            count--;
                            par[p1] = p2;
                        }
                    }
                }                
            }

            ans.add(count);
        }

        return ans;
    }

    //Leetcode 1168
    public int minCostToSupplyWater(int N, ArrayList<int[]> edges){
        par = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            par[i] = i;
        }

        int cost = 0;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                par[p1] = p2;
                cost += w;
            }
        }

        return cost;
    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes){
        ArrayList<int[]> Pipes = new ArrayList<>();
        for(int i = 0; i < n; i++){
            Pipes.add(new int[] {0, i + 1, wells[i]});
        }

        for(int[] p : pipes){
            Pipes.add(p);
        }

        Collections.sort(Pipes, (a, b) -> {
            return a[2] - b[2];
        });

        return minCostToSupplyWater(n, Pipes);
    }

    //Leetcode 200
    public int numIslands_(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n * m; i++)
            par[i] = i;

        int oncesCount = 0;
        int[][] dir = {{1, 0}, {0, 1}};

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid[i][j] == '1'){
        
                    oncesCount++;
                    for (int d = 0; d < 2; d++){
                        int x = i + dir[d][0];
                        int y = j + dir[d][1];

                        if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == '1'){
                            int p1 = findPar(i * m + j);
                            int p2 = findPar_(x * m + y);
                            if (p1 != p2){
                                par[p2] = p1;
                                oncesCount--;
                            }
                        }
                    }
                }
            }
        }

        return oncesCount;
    }

    int[] size;
    public long journeyToMoon(int n, List<List<Integer>> astronaut) {
        par = new int[n];
        size = new int[n];

        for(int i = 0; i < n; i++){
            par[i] = i;
            size[i] = 1;
        }
    
        for(List<Integer> ast : astronaut){
            int p1 = findPar_(ast.get(0));
            int p2 = findPar_(ast.get(1));

            if(p1 != p2){
                par[p1] = p2;
                size[p2] += size[p1];
            }
        }

        long sum = 0, totalPairs = 0;
        for(int i = 0; i < n; i++){
            if(par[i] == i){
                totalPairs += sum * size[i];
                sum += size[i];
            }
        }

        return totalPairs;
    }

    //Leetcode 815
    public int numBusesToDestination(int[][] routes, int src, int dest) {
        if(src == dest)
            return 0;

        int n = routes.length;
        HashMap<Integer, ArrayList<Integer>> busStandMapping = new HashMap<>();
        int busNumber = 0;
        for(int[] busStandList : routes){
            for(int busStand : busStandList){
                busStandMapping.get(busStand).add(busNumber);
            }
            busNumber++;
        }
        
        HashSet<Integer> isBusStandSeen = new HashSet<>();
        boolean[] isBusSeen = new boolean[n];

        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        isBusStandSeen.add(src);

        int level = 0;
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int busStand = que.removeFirst();

                ArrayList<Integer> allBuses = new ArrayList<>();
                allBuses = busStandMapping.get(busStand);
                for(int busNo : allBuses){
                    if(isBusSeen[busNo])
                        continue;

                    for(int bs : routes[busNo]){
                        if(isBusStandSeen.contains(bs) == isBusStandSeen.contains(isBusStandSeen.size() - 1)){
                            que.addLast(bs);
                            isBusStandSeen.add(bs);

                            if(bs == dest)
                                return level + 1;
                        }
                    }

                    isBusSeen[busNo] = true;
                }
            }
            level++;
        }

        return -1;
    }

    //Leetcode 743
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++){
            graph[i] = new ArrayList<>();
        }

        // u -> {v, w}
        for(int[] ar : times){
            graph[ar[0]].add(new int[] {ar[1], ar[2]});
        }

        int[] dis = new int[n + 1];
        Arrays.fill(dis, (int)1e9);
        boolean[] vis = new boolean[n + 1];

        //{vtx, wsf}
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        que.add(new int[] {k, 0});
        dis[k] = 0;
        int noOfEdges = 0;
        int maxValue = 0;

        while(que.size() != 0){
            int[] p = que.remove();
            int vtx = p[0], wsf = p[1];

            if(vis[vtx])
                continue;

            if(vtx != k)
                noOfEdges++;

            maxValue = Math.max(maxValue, wsf);

            vis[vtx] = true;
            for(int[] e : graph[vtx]){
                if(!vis[e[0]] && e[1] + wsf < dis[e[0]]){
                    dis[e[0]] = e[1] + wsf;
                    que.add(new int[] {e[0], e[1] + wsf});
                }
            }
        }

        if(noOfEdges != n - 1)
            return -1;

        return maxValue;
    }

    //Leetcode 1192
    //error
    int[] dis, low;
    boolean[] vis;
    int time = 0;
    List<List<Integer>> res;
    public void dfs(int src, int par, int n, List<Integer>[] graph){
        dis[src] = low[src] = time++;
        vis[src] = true;

        for(Integer nbr : graph[src]){
            if(!vis[src]){
                dfs(nbr, src, n, graph);

                if(dis[src] < low[nbr])
                    res.get(src).add(nbr);

                low[src] = Math.min(low[src], low[nbr]);

            } else if(nbr != par)
                low[src] = Math.min(low[src], dis[nbr]);
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];
        for(List<Integer> ar : connections){
            graph[ar.get(0)].add(ar.get(1));
            graph[ar.get(1)].add(ar.get(0));
        }

        dis = new int[n];
        low = new int[n];
        vis = new boolean[n];

        res = new ArrayList<>();
        for(int i =0 ; i < n; i++){
            res.add(i, new ArrayList<>());
        }

        dfs(0, -1, n, graph);
        return res;
    }

    //Leetcode 747
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] dis = new int[n];
        Arrays.fill(dis, (int) 1e9);
        dis[src] = 0;

        for (int EdgeCount = 1; EdgeCount <= K + 1; EdgeCount++) {
            int[] ndis = new int[n];
            for (int i = 0; i < n; i++)
                ndis[i] = dis[i];

            for (int[] e : flights) {
                int u = e[0], v = e[1], w = e[2];
                if (dis[u] != (int) 1e9 && dis[u] + w < ndis[v])
                    ndis[v] = dis[u] + w;
            }

            dis = ndis;
        }

        return dis[dst] != (int) 1e9 ? dis[dst] : -1;
    }   

    //Leetcode 924
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        size = new int[n];    
        par = new int[n];

        for(int i = 0; i < n; i++){
            size[i] = 1;
            par[i] = i;
        }

        Arrays.sort(initial);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j && graph[i][j] == 1){
                    int p1 = findPar_(i);
                    int p2 = findPar_(j);

                    if(p1 != p2){
                        par[p1] = p2;
                        size[p2] += size[p1];
                    }
                }
            }
        }

        int[] infectedNodeInCity = new int[n];
        for(int i : initial){
            int leader = findPar_(i);
            infectedNodeInCity[leader]++;
        }

        int ans = initial[0];
        int maxPopulatedCity = 0;
        for(int i : initial){
            int noOfNodesInfected = infectedNodeInCity[findPar_(i)];
            if(noOfNodesInfected == 1 && size[findPar_(i)] > maxPopulatedCity){
                maxPopulatedCity = size[findPar_(i)];
                ans = i;
            }
        }

        return ans;
    }

    public static void main(String[] args){
        // hamiltonianCycleandPath(0);
        String A = "parker";
        String B = "morris";
        String S = "parser";
        String res = new String();
        res = smallestEquivaleString(A, B, S);
        System.out.println(res);
    }
    
}
