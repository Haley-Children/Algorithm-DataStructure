package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_DFS와BFS_AdjMatrix { // 인접 행렬 방식
    static boolean[][] adjMatrix;
    static boolean[] vis;
    static int N,M,V;
    // 인접행렬 방식
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점 갯수
        M = Integer.parseInt(st.nextToken()); // 간선 갯수
        V = Integer.parseInt(st.nextToken()); // 탐색 시작 정점 번호

        adjMatrix = new boolean[N+1][N+1]; // 정점 1부터 시작
        vis = new boolean[N+1]; // 정점 방문 배열 (정점의 수만큼 할당)

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjMatrix[start][end] = true;
            adjMatrix[end][start] = true;
        }

        dfs(V);
        System.out.println();
        Arrays.fill(vis, false);
        bfs(V);


    }

    private static void dfs(int v) {
        vis[v] = true;
        System.out.print(v+" ");
        for (int i = 1; i <= N; i++) {
            if (adjMatrix[v][i] && !vis[i]) {
                dfs(i);
            }
        }
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(v);
        vis[v] = true;
        System.out.print(v+" ");

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 1; i <= N; i++) {
                if (adjMatrix[cur][i] && !vis[i]) {
                    queue.offer(i);
                    vis[i] = true;
                    System.out.print(i+" ");
                }
            }
        }

    }

}
