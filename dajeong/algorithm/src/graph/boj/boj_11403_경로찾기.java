package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_11403_경로찾기 {
    static int N;
    static int[][] adjArr, ans;
    static boolean[] vis;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjArr = new int[N+1][N+1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                adjArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 정점마다 bfs 수행
        ans = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            vis = new boolean[N+1];
            bfs(i);
        }

        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }
    }
    private static void bfs(int x) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(x); // 자기 자신으로 들어오는 경우도 확인해야하므로 방문표시 x

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (!vis[i] && adjArr[cur][i] == 1) {
                    queue.offer(i);
                    vis[i] = true;
                    ans[x][i] = 1;
                }
            }
        }
    }
}
