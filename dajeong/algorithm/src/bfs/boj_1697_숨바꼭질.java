package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1697_숨바꼭질 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수빈 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        // 범위 잘 생각하기. 멋대로 1부터 10만까지 안에서 수빈이가 돌아다닌다고 생각하면 안된다.
        int[] dist = new int[100001];
        Arrays.fill(dist, -1);

        queue.offer(N);
        dist[N] = 0;
        int ans = 0; // 동생을 찾는 최소 시간

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == K) {
                ans = dist[cur];
                break;
            }
            for (int i = 0; i < 3; i++) {
                int nx = cur;
                if (i==0) {
                    nx++;
                } else if (i==1) {
                    nx--;
                } else {
                    nx*=2;
                }
                if (nx < 0 || nx > 100000) continue;
                if (dist[nx] != -1) continue;
                queue.offer(nx);
                dist[nx] = dist[cur]+1;
            }
        }

        System.out.println(ans);
    }

}
