package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 트리의 특징을 적용하지 않고 푼 풀이 (백트래킹-부분합)

public class Main_1240_노드사이의거리 {

    static int N, M, sum;
    static int[][] dist;
    static ArrayList<Integer>[] adjList;
    static boolean[] vis;
    static boolean flag;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken()); // 정점 수
        M = Integer.parseInt(st.nextToken()); // 출력 수
        dist = new int[N + 1][N + 1]; // 1-indexed
        vis = new boolean[N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) { // 총 간선 수: N-1
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            adjList[v1].add(v2);
            adjList[v2].add(v1);
            dist[v1][v2] = dist[v2][v1] = d;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());

            sum = 0;
            Arrays.fill(vis, false);
            flag = false;
            dfs(t1, t2, 0);
            System.out.println(sum);
        }
    }

    private static void dfs(int x, int end, int tmpSum) {
        if (x == end) {
            if (!flag) sum = tmpSum;
            flag = true;
            return;
        }

        vis[x] = true;
        for (int a : adjList[x]) {
            if (vis[a]) continue;
            dfs(a, end, tmpSum + dist[x][a]);
        }
        vis[x] = false;

    }


}
