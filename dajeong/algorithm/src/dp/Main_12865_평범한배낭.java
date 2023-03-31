package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12865_평범한배낭 {
    // d[i][w] = i번째까지 물건을 배낭에 넣기를 고려했을 때 얻을 수 있는 가치의 최댓값 (넣을 수 있는 무게 w)
    // d[i][w] = 물건을 담을 수 있는 무게가 w일 때, max(마지막에 i번째 물건을 넣었을 때의 가치의 합, i번째 물건을 넣지 않았을 때의 가치의 합)
    // d[i][w] = max(d[i-1][W-W(i)]+v(i), d[i-1][W])

    static int N,K;
    static int[][] WV;
    static int[][] d;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 물품의 수
        K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
        WV = new int[N+1][2]; // 각 물건(1-indexed)의 무게와 가치

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            WV[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())}; // 무게와 가치
        }

        d = new int[N+1][K+1]; // 무게 K를 초과하지 않는 선에서 N개의 물건 넣었을 때 최대 가치의 합

        for (int i = 1; i <= N; i++) {
            for (int k = K; k >= 1; k--) {
                d[i][k] = d[i-1][k]; // 먼저 할당을 해야 그 전의 최솟값이 dp에 이어져서 저장이 된다.
                if (k>=WV[i][0]) d[i][k] = Math.max(d[i-1][k-WV[i][0]]+WV[i][1], d[i][k]);

            }
        }

        System.out.println(d[N][K]);

    }
}
