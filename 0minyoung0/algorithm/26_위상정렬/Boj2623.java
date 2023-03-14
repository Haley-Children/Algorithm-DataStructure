import java.io.*;
import java.util.*;

public class Boj2623 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 가수의 수
        int n = Integer.parseInt(st.nextToken());
        // 보조PD의 수
        int m = Integer.parseInt(st.nextToken());

        // 그래프를 인접 배열로 표현
        boolean[][] graph = new boolean[n+1][n+1];

        // 보조PD가 정한 순서 입력받기
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int tempCnt = Integer.parseInt(st.nextToken());
            int s1 = Integer.parseInt(st.nextToken());
            for (int j=0; j<tempCnt-1; j++) {
                int s2 = Integer.parseInt(st.nextToken());
                graph[s1][s2] = true;
                s1 = s2;
            }
        }

        // indegree 배열 채우기
        int[] indegree = new int[n+1];
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (graph[j][i]) indegree[i]++;
            }
        }

        // indegree 배열의 값이 0인 노드를 큐에 넣기
        boolean impossible = false;
        boolean[] vis = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        for (int i=1; i<=n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                vis[i] = true;
            }
        }

        // 큐가 빌때까지 BFS 돌면서 sb에 저장
        int ansCnt = 0;
        BFS: while(!q.isEmpty()) {
            int cur = q.poll();
            ansCnt++;
            sb.append(cur).append("\n");
            for (int i=1; i<=n; i++) {
                if (!graph[cur][i]) continue;
                if (vis[i]) {
                    impossible = true;
                    break BFS;
                }
                if (--indegree[i] == 0) {
	                q.offer(i);
	                vis[i] = true;
                }
            }
        }

        // 답 출력
        if (ansCnt != n || impossible) {
            System.out.println(0);
            return;
        }
        System.out.println(sb);
    }
}