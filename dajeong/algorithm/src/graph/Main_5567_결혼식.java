package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5567_결혼식 {
    static int n,m, cnt;

    static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        adjList = new ArrayList[n+1]; // 인접리스트로 구현

        // 초기화
        for (int i = 0; i < n+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList[start].add(end);
            adjList[end].add(start); // 무향그래프
        }

        // 상근이 (시작점:1)부터 bfs 시작 -> 거리가 1부터 2까지인 정점 수 세기
        bfs(1);
        System.out.println(cnt);

    }

    private static void bfs(int vertex) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] vis = new boolean[n+1]; // 방문배열
        int[] dist = new int[n+1]; // 거리 배열
        queue.offer(vertex);
        vis[vertex] = true;

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int a : adjList[cur]) {
                if (vis[a]) continue; // 방문 체크
                // 현재 정점의 최단경로 거리가 2이상일 때 탐색하지 않게 구현
                if (dist[cur]>= 2) {
                    vis[a] = true;
                    continue;
                }
                queue.offer(a);
                vis[a] = true;
                dist[a] = dist[cur]+1;
                cnt++;

            }

        }
    }

}
