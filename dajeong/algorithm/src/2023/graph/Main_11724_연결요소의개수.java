package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_11724_연결요소의개수 {

    static int N, M, cnt;
    static ArrayList<Integer>[] adjList;
    static boolean[] vis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 갯수
        M = Integer.parseInt(st.nextToken()); // 간선의 갯수
        adjList = new ArrayList[N+1];
        vis = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList[start].add(end);
            adjList[end].add(start); // 무방향 그래프
        }

        for (int i = 1; i < N+1; i++) {
            if (!vis[i]) {
                dfs(i);
                cnt++;
            }
        }
        System.out.println(cnt);

    }

    private static void dfs(int start) {
        vis[start] = true;
        for(int a : adjList[start]) {
            if (vis[a]) continue;
            dfs(a);
        }
    }

}
