package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2660 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 회원의 수 입력
        int n = Integer.parseInt(br.readLine());

        // 그래프 만들기
        boolean[][] adj = new boolean[n+1][n+1];

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(u == -1 && v == -1) break;

            adj[u][v] = adj[v][u] = true;
        }

        // 각 회원의 점수 (정점에서 가장 많은 이동 수)
        int[] grades = new int[n+1];

        // 회장 후보의 점수
        int minGrade = Integer.MAX_VALUE;

        // 각 회원마다 최대 거리 계산
        for (int i = 1; i <= n; i++) {
            // 이동 거리를 계산하기 위한 배열
            int[] dist = new int[n+1];
            Queue<Integer> q = new ArrayDeque<>();

            dist[i] = 1;
            q.offer(i);

            while(!q.isEmpty()) {
                int cur = q.poll();
                // i번째 회원의 최대 이동거리 갱신
                grades[i] = Math.max(grades[i], dist[cur]);

                for (int next = 1; next <= n; next++) {
                    // 연결되지 않은 정점이거나 방문한 곳이면 생략
                    if(!adj[cur][next] || dist[next] != 0) continue;

                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                }
            }

            // 현재까지의 최소 점수를 갱신 -> 회장 후보의 점수
            minGrade = Math.min(minGrade, grades[i]);
        }

        // 회장 후보의 수
        int count = 0;
        // 회장 후보의 번호 저장
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            if(grades[i] == minGrade) {
                count++;
                sb.append(i).append(" ");
            }
        }

        System.out.println((minGrade - 1) + " " + count);
        System.out.println(sb);
    }
}
