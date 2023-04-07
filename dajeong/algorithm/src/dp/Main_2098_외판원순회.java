package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2098_외판원순회 {
    static int N, minAns;
    static int[][] cost, dp;
    static final int INF = Integer.MAX_VALUE/2-1; // ** dp 최댓값 더할 경우 주의
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        minAns = INF;
        cost = new int[N][N];
        // 함수 정의) dp[i][j]: 현재 있는 도시가 i이고 이미 방문한 도시들의 집합이 j일때, 방문하지 않은 나머지 도시들을 모두 방문한 뒤 출발 도시로 돌아올 때 드는 최소 비용.
        // 즉, 방문해야하는 도시(여기에 시작지점으로 돌아오는 것 포함) 들까지 가는 최소 비용
        dp = new int[N][(1<<N)-1]; // 시작 도시,  방문 표시한 vis 상태 bit

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                cost[i][j] = x;
            }
        }
        // 한붓그리기& 시작점으로 다시 돌아오므로 시작점 고정 가능.
        System.out.println(tsp(0, 1));


    }

    // 현재 위치가 cur일 때, visit 도시들을 순회한 후 0으로 돌아오는 최소 비용
    private static int tsp(int cur, int vis) {
        if ((1<<N)-1 == vis) { // 모든 도시를 방문했다면
            if (cost[cur][0] == 0) return INF; // 돌아갈 수 없는 경우 INF 반환
            else return cost[cur][0]; // 현재 도시 -> 0번쨰(시작) 도시 이동 거리
        }

        // 이미 계산한적 있는 경우, 이전 값 반환
        if (dp[cur][vis] != -1) {
            return dp[cur][vis];
        }
        dp[cur][vis] = INF;
        for (int i = 0; i < N; i++) {
            // 현재 위치에서 i번째 도시로 갈 수 없거나 이미 방문한 경우 스킵
            if (cost[cur][i] == 0 || (vis & (1<<i))!=0) {
                continue;
            }
            dp[cur][vis] = Math.min(dp[cur][vis], cost[cur][i] + tsp(i, vis | (1<<i)));
        }
        return dp[cur][vis];

    }
}
