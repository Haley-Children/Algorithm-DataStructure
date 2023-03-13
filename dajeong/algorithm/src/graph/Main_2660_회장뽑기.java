package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2660_회장뽑기 {

    static ArrayList<Integer>[] adjList;
    static boolean[] vis;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 회원의 수(정점 수)
        adjList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (start == -1 && end == -1) {
                break;
            }
            adjList[start].add(end);
            adjList[end].add(start);
        }

        // bfs 순회용 방문배열
        vis = new boolean[N + 1];
        // bfs 거리 배열
        dist = new int[N + 1];
        // 회장후보(정점)별 최대 거리 수(점수)
        int[] cnt = new int[N+1];

        // 전체 회장 후보 중 가장 작은 점수
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(vis, false);
            Arrays.fill(dist, 0);
            bfs(i, dist, vis);

            // bfs 탐색 후 최대 거리(점수) 구하기
            int max = 0;
            for (int j = 0; j < dist.length; j++) {
                max = Math.max(dist[j], max);
            }
            // 각 정점(회장 후보)별로 점수 저장
            cnt[i] = max;
            // 최소값 갱신
            min = Math.min(min, max);
        }

        int minCnt = 0;
        for (int i = 1; i < N+1; i++) {
            if (cnt[i] == min) minCnt++;
        }

        System.out.println(min+" "+minCnt);

        for (int i = 1; i < N+1; i++) {
            if (cnt[i]==min) {
                System.out.print(i+" ");
            }
        }

    }

    private static void bfs(int v, int[] dist, boolean[] vis) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(v);
        vis[v] = true;
        dist[v] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int k : adjList[cur]) {
                if (vis[k]) {
                    continue;
                }
                queue.offer(k);
                dist[k] = dist[cur] + 1;
                vis[k] = true;
            }
        }
    }
}
