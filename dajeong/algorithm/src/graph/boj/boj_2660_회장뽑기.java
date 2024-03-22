package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_2660_회장뽑기 {
    static int N;
    static ArrayList<Integer>[] adjList, personByScore;
    static int[] dist;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 회원 수(정점)
        adjList = new ArrayList[N+1]; // 인접리스트
        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        personByScore = new ArrayList[51]; // 점수별로 회원 번호 저장
        for (int i = 0; i < 51; i++) {
            personByScore[i] = new ArrayList<>();
        }
        StringTokenizer st;

        
        while (true) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (s == -1 && e == -1) {
                break;
            } 
            adjList[s].add(e);
            adjList[e].add(s);
            

        }
        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < personByScore.length; i++) {
            int ppCnt = personByScore[i].size();
            ArrayList<Integer> ppList = personByScore[i];
            if (ppCnt > 0) {
                sb.append(i).append(" ").append(ppCnt).append("\n");
                Collections.sort(ppList);
                for (int j = 0; j < ppList.size(); j++) {
                    sb.append(ppList.get(j)).append(" ");
                }
                break;

            }
        }

        System.out.println(sb.toString());
    }
    private static void bfs(int x) {
        dist = new int[N+1];
        Arrays.fill(dist, -1);
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(x);
        dist[x]= 0;

        while (!queue.isEmpty()) {
            // qsize로 돌리면 마지막에 qsize가 1이 됨. 따라서 d++가 추가되어 +1한 값이 됨
            int cur = queue.poll();
            int d = dist[cur]+1;
            for(int c : adjList[cur]) {
                if (dist[c] != -1) {
                    continue;
                }
                queue.offer(c);
                dist[c] = d;
            }
        }
        // bfs 끝난 후 최대 거리 찾기 
        int maxD = Arrays.stream(dist).max().getAsInt();
        personByScore[maxD].add(x);
    }
}
