package graph.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_11724_연결요소의개수 {
    static int N,M;
    static ArrayList<Integer>[] adList;
    static boolean[] vis;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        M = Integer.parseInt(st.nextToken()); // 간선의 개수

        // 인접리스트 초기화 주의!
        adList = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            adList[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adList[s].add(e);
            adList[e].add(s); // 무방향그래프
        }

        int cnt = 0;

        vis = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            if (!vis[i]) {
                // bfs(i); 
                // dfs(i); 
                dfsRecur(i);
                cnt++;
            }
        }

        // 정답 출력
        System.out.println(cnt);

    }

    // dfs 재귀 방식
    private static void dfsRecur(int x) {
        int cur = x;
        vis[cur] = true;
        for (int c : adList[cur]) {
            if (vis[c]) {
                continue;
            }
            dfsRecur(c);
        }
    }

    // dfs 비재귀 방식 (스택 이용) + 방문 순서는 재귀랑 일치하게 구현
    private static void dfs(int x) { // stack에 넣을 때가 아닌, 꺼낼 때 방문표시! & 조회시 vis 확인 필요
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(x); 

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            if (vis[cur]) {
                continue;
            }
            vis[cur] = true;
            for(int c: adList[cur]) {
                if (vis[c]) {
                    continue;
                }
                stack.push(c);
            }
        }
    }

    // bfs 방식
    private static void bfs(int x) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(x);
        vis[x] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int c : adList[cur]) {
                if (vis[c]) {
                    continue;
                }
                queue.offer(c);
                vis[c] = true;
            }
        }
    }
}
