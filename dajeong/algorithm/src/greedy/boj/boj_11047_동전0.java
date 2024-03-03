package greedy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 해당 문제는 dp로 풀게되면 O(N*K)로 시간초과가 나게 된다.
// 그리디로 풀이
public class boj_11047_동전0 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for (int i = N-1; i >= 0; i--) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (K >= coins[i]) {
                cnt += (K/coins[i]);
                K %= coins[i];
            }
        }

        System.out.println(cnt);
    }

}
