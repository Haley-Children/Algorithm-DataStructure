package bfs;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main_2606 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        boolean[][] isConnected = new boolean[n][n];
        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt() - 1;
            int to = sc.nextInt() - 1;

            // 양방향
            isConnected[from][to] = true;
            isConnected[to][from] = true;
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] vis = new boolean[n];

        q.offer(0);
        vis[0] = true;

        int count = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < n; i++) {
                if(isConnected[cur][i] && !vis[i]) {
                    q.offer(i);
                    vis[i] = true;
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
