package topologicalsort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1766 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 문제의 수
        int n = Integer.parseInt(st.nextToken());
        // 정보의 개수
        int m = Integer.parseInt(st.nextToken());

        // [i]번 문제의 indegree
        int[] indeg = new int[n+1];
        // outward 간선 저장
        List<Integer>[] order = new ArrayList[n+1];

        // 문제 푸는 순서 입력 받기 (간선)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            // 먼저 풀어야 하는 문제 번호
            int prev = Integer.parseInt(st.nextToken());
            // 나중에 풀어야 하는 문제 번호
            int next = Integer.parseInt(st.nextToken());

            if(order[prev] == null) {
                order[prev] = new ArrayList<>();
            }

            // prev 다음에 풀 문제 추가
            order[prev].add(next);
            // next 풀기 전에 풀어야 하는 문제 개수 추가
            indeg[next]++;
        }

        // 위상정렬에서 사용할 우선순위 큐 (항상 낮은 번호부터 나오도록)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if(indeg[i] == 0) pq.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur).append(" ");

            if(order[cur] == null) continue;

            for (int i = 0; i < order[cur].size(); i++) {
                int next = order[cur].get(i);

                if(--indeg[next] == 0) {
                    pq.offer(next);
                }
            }
        }

        System.out.println(sb);
    }
}
