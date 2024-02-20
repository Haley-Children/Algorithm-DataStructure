package math.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2941_에라토스테네스의체 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] check = new boolean[N + 1];
        int cnt = 0;
        int ans = 0;

        loop:
        for (int i = 2; i <= N; i++) {
            if (check[i]) {
                continue;
            }
            for (int j = 2 * i; j <= N; j += i) {
                if (!check[j]) {
                    check[j] = true;
                    cnt++;
                    if (cnt == K) {
                        ans = j;
                        break loop;
                    }
                }
            }

        }

        System.out.println(ans);
    }
}
