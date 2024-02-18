package math.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11050_이항계수1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int ans = 1;
        for (int i = 1; i <= N; i++) {
            ans *= i;
        }
        for (int i = 1; i <= K; i++) {
            ans /= i;
        }
        for (int i = 1; i <= N-K; i++) {
            ans /= i;
        }
        System.out.println(ans);
    }

}
