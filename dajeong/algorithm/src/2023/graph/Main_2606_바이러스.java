package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2606_바이러스 {

    static int N, M, cnt;
    static boolean[] vis;
    static int[][] adjMatrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 노드 수
        M = Integer.parseInt(br.readLine()); // 간선 수
        adjMatrix = new int[N+1][N+1];
        vis = new boolean[N+1];
        StringTokenizer st;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end= Integer.parseInt(st.nextToken());
            adjMatrix[start][end] = adjMatrix[end][start] = 1;
        }
        vis[0] = true;
        bfs(1);
        System.out.println(cnt);
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        vis[start] = true;

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (adjMatrix[cur][i] == 1 && !vis[i]) {
                    vis[i] = true;
                    queue.offer(i);
                    cnt++;
                }
            }

        }
    }

}
