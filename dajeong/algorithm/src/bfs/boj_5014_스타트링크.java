package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_5014_스타트링크 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int F = Integer.parseInt(st.nextToken()); // 총 몇층
        int S = Integer.parseInt(st.nextToken()); // 시작 (강호의 시작점!!! 층수의 시작점이 아니다.)
        int G = Integer.parseInt(st.nextToken()); // 목표
        int U = Integer.parseInt(st.nextToken()); // up
        int D = Integer.parseInt(st.nextToken()); // down

        int[] dist = new int[2000001];
        Arrays.fill(dist, -1);
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        dist[S] = 0;
        queue.offer(S);

        int ans = -1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == G) {
                ans = dist[G];
                break;
            }
            for (int i = 0; i < 2; i++) {
                int nx;
                if (i == 0) {
                    nx = cur + U;
                } else {
                    nx = cur - D;
                }

                // 0층은 없다..! 건물은 1층부터 시작한다.
                if (nx <= 0 || nx > F || dist[nx] != -1) {
                    continue;
                }
                queue.offer(nx);
                dist[nx] = dist[cur] + 1;
            }
        }
        if (ans == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(ans);
        }


    }

}
