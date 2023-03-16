package topologicalsort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1005 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 건물의 수
            int n = Integer.parseInt(st.nextToken());
            // 건물 짓는 순서의 수
            int m = Integer.parseInt(st.nextToken());

            // [i]번 건물을 지을 때 걸리는 시간
            int[] time = new int[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            // [i]번 건물을 짓기 전에 지어야 하는 건물 수
            int[] indeg = new int[n + 1];
            // [i]번 건물을 짓고 만들 수 있는 건물의 번호
            List<Integer>[] nextBuild = new ArrayList[n + 1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int prev = Integer.parseInt(st.nextToken());
                int next = Integer.parseInt(st.nextToken());

                if (nextBuild[prev] == null) {
                    nextBuild[prev] = new ArrayList<>();
                }

                nextBuild[prev].add(next);
                indeg[next]++;
            }

            // 목표 건물의 번호
            int goal = Integer.parseInt(br.readLine());

            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (indeg[i] == 0) q.offer(i);
            }

            // [i]번 건물을 짓기 전에 필요한 시간
            int[] maxDelay = new int[n + 1];

            while (!q.isEmpty()) {
                int cur = q.poll();
                // cur번 건물을 지을 때까지 걸린 시간 저장
                time[cur] += maxDelay[cur];
                if (cur == goal) break;

                // 다음에 지을 수 있는 건물이 없으면 다음 건물로
                if (nextBuild[cur] == null) continue;

                for (int i = 0; i < nextBuild[cur].size(); i++) {
                    int next = nextBuild[cur].get(i);
                    // next번 건물을 짓기 전에 필요한 시간 중 가장 오래 걸리는 것을 선택
                    // next의 바로 직전 단계 건물이 항상 정답 후보임이 보장됨 (누적되므로)
                    maxDelay[next] = Math.max(maxDelay[next], time[cur]);

                    if (--indeg[next] == 0) {
                        q.offer(next);
                    }
                }
            }

            sb.append(time[goal]).append("\n");
        }

        System.out.println(sb);
    }
}
