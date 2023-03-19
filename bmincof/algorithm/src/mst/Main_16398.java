package mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16398 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 행성의 개수
        int n = Integer.parseInt(br.readLine());

        // 플로우 관리 비용
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(cost[o1[0]][o1[1]] == cost[o2[0]][o2[1]]) return -1;
                else return cost[o1[0]][o1[1]] < cost[o2[0]][o2[1]] ? -1 : 1;
            }
        });

        for (int i = 1; i < n; i++) {
            pq.offer(new int[] {0, i});
        }
        boolean[] isTree = new boolean[n];
        isTree[0] = true;

        int count = 0;
        long total = 0;
        while(count < n-1) {
            int[] curEdge = pq.poll();
            if(isTree[curEdge[1]]) continue;

            int start = curEdge[0];
            int end = curEdge[1];

            // 새로 잇는 곳이면 트리에 포함
            isTree[end] = true;
            count++;
            total += cost[start][end];

            // 새로 추가한 곳에 연결된 간선들 추가
            for (int i = 0; i < n; i++) {
                // 자기 자신이거나 이미 트리에 포함됐으면 스킵
                if(isTree[i]) continue;

                pq.offer(new int[] {end, i});
            }
        }

        System.out.println(total);
    }
}
