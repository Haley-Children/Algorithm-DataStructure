package math.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_6064_카잉달력 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int ans = run(M, N, x, y);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int run(int m, int n, int x, int y) {
        if (x == m) {
            x = 0;
        }
        if (y == n) {
            y = 0;
        }
        int l = lcm(m, n);
        // 기존 O(LCM(m,n)) (== 최대 O(M*N)) 풀이로 짜면 시간초과
        // x, x+m, x+2m, ... 중 i%n==y를 만족하는 것을 찾으므로 시간복잡도: O(LCM(m,n)) / M (== 최대 N)
        for (int i = x; i <= l; i += m) {
            // *** x = M, y = N일 때, LCM(M,N) 대신 0을 반환하는 것을 막기 위해 i==0 조건 추가
            if (i == 0) continue;
            if (i % n == y) {
                return i;
            }
        }
        return -1;
    }

    private static int lcm(int m, int n) {
        return m / gcd(m, n) * n;
    }

    private static int gcd(int m, int n) {
        if (n == 0) {
            return m;
        }
        return gcd(n, m % n);
    }

}
