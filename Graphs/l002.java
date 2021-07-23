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
