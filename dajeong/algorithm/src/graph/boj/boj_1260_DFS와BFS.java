package graph.boj;

import java.io.*;
import java.util.*;

public class boj_1260_DFS와BFS {
    static int N,M,V;
    static ArrayList<Integer>[] adList;
    static StringBuilder bfsAns, dfsAns, dfsRans;
    static boolean[] vis;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        adList = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            adList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adList[s].add(e);
            adList[e].add(s);
        } 

        // ** 번호가 작은 것부터 방문하기 위해 정렬하기
        for (int i = 1; i <= N; i++) {
            Collections.sort(adList[i]);
        }


        bfsAns = new StringBuilder();
        dfsAns = new StringBuilder();
        dfsRans = new StringBuilder();

        vis = new boolean[N+1];
        dfs(V);
        vis = new boolean[N+1];
        dfsR(V);
        vis = new boolean[N+1];
        bfs(V);

        System.out.println(dfsAns.toString());
        System.out.println(dfsRans.toString());
        System.out.println(bfsAns.toString());
        
    }

    // 재귀 방식 dfs
    private static void dfsR(int x) {
        int cur = x;
        vis[cur] = true;
        dfsRans.append(cur).append(" ");
        for(int c : adList[cur]) {
            if (vis[c]) {
                continue;
            }
            dfsR(c);
        }
    }

    // 비재귀(stack)방식 + 재귀 방문순서 유지 dfs
    private static void dfs(int x) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(x);

        // 재귀와 같은 동작방식 유지를 위해 stack에서 꺼낼 때 방문표시
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            if (vis[cur]) { 
                continue;
            }
            vis[cur] = true;
            dfsAns.append(cur).append(" ");

            // 스택의 특성상 역순으로 방문하기
            for(int i=adList[cur].size()-1; i >= 0; i--) {
                int nxt = adList[cur].get(i);
                if(vis[nxt]) {
                    continue;
                }
                stack.push(nxt);
            }
        }

    }

    // bfs 방식
    private static void bfs(int x) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(x);
        vis[x] = true;
        bfsAns.append(x).append(" ");

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for(int c : adList[cur]) {
                if (vis[c]) {
                    continue;
                }
                queue.offer(c);
                vis[c] = true;
                bfsAns.append(c).append(" ");
            }
        }
    }
}
