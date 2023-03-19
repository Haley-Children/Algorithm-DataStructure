package mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4386 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 별의 개수
        int n = Integer.parseInt(br.readLine());
        // 별의 좌표 저장
        double[][] stars = new double[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        // [i]번과 [j]번 별 사이의 거리를 저장(간선의 가중치)
        double[][] dist = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                double dx = stars[i][0] - stars[j][0];
                double dy = stars[i][1] - stars[j][1];
                dist[j][i] = dist[i][j] = Math.sqrt(dx*dx + dy*dy);
            }
        }

        // 별자리에 포함됐으면 true
        boolean[] isConstellation = new boolean[n];
        isConstellation[0] = true;

        // 간선의 길이를 비교할 우선순위 큐
        // 간선 int[] : {출발, 도착}
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(dist[o1[0]][o1[1]] == dist[o2[0]][o2[1]]) {
                    return -1;
                }
                return dist[o1[0]][o1[1]] < dist[o2[0]][o2[1]] ? -1 : 1;
            }
        });

        // 0번 별의 간선들을 모두 추가
        for (int i = 1; i < n; i++) {
            pq.offer(new int[] {0, i});
        }

        double totalLength = 0;
        int count = 0;
        while(count < n - 1) {
            // 간선의 시작, 끝
            int[] cur = pq.poll();

            int start = cur[0];
            int end = cur[1];

            for (int i = 0; i < n; i++) {
                // 이미 별자리에 포함돼있으면 스킵
                if(isConstellation[end]) continue;

                isConstellation[end] = true;
                totalLength += dist[start][end];
                count++;
                // 별자리에 새로 추가되는 별이면
                // 해당 별의 간선들을 모두 추가
                for (int j = 0; j < n; j++) {
                    if(end == j) continue;

                    pq.offer(new int[] {end, j});
                }
            }
        }

        System.out.println(totalLength);
    }
}
