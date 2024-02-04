package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 가중치가 다른 bfs이므로 가장 먼저 큐에 기록된 값이 최소값이라는 보장이 성립되지 않는다! -> bfs 다 돌려야 함
public class boj_13549_숨바꼭질3 {

    public static final int MAX = 100001;
    public static int[] arr = new int[MAX];
    public static int[] visited = new int[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
        int K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        Arrays.fill(visited, -1);

        queue.offer(N);
        visited[N] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur + 1 >= 0 && cur + 1 < MAX ) {
                if (visited[cur+1]==-1 || visited[cur]+1 < visited[cur + 1]) {
                    queue.offer(cur+1);
                    visited[cur+1] = visited[cur]+1;
                }
            }
            if (cur - 1 >= 0 && cur - 1 < MAX) {
                if (visited[cur-1]==-1 || visited[cur]+1 < visited[cur - 1]) {
                    queue.offer(cur-1);
                    visited[cur-1] = visited[cur]+1;
                }
            }
            if (cur * 2 >= 0 && cur * 2 < MAX) {
                if (visited[cur*2]==-1 || visited[cur] < visited[cur*2]) {
                    queue.offer(cur*2);
                    visited[cur*2] = visited[cur];
                }
            }
        }
        System.out.println(visited[K]);


    }

}
