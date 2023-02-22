package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1260 {
    static StringBuilder sb = new StringBuilder();
    static int[][] graph;
    static int[] dvis;
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()) + 1;
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        dvis = new int[n];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from][to] = graph[to][from] = 1;
        }

        dfs(start);
        sb.append('\n');
        bfs(start);

        System.out.println(sb);
    }

    static void bfs(int start) {
        int[] vis = new int[n];
        Queue<Integer> q = new LinkedList<>();

        vis[start] = 1;
        q.offer(start);

        while(!q.isEmpty()) {
            int from = q.poll();
            sb.append(from).append(' ');
            for (int to = 1; to < n; to++) {
                if(graph[from][to] == 1 && vis[to] != 1) {
                    vis[to] = 1;
                    q.offer(to);
                }
            }
        }
        sb.append('\n');
    }

    static void dfs(int from) {

        sb.append(from).append(' ');
        dvis[from] = 1;

        for (int to = 1; to < n; to++) {
            if(graph[from][to] == 1 && dvis[to] != 1) {
                dfs(to);
            }
        }
    }

}

