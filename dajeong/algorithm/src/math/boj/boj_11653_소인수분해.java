package math.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_11653_소인수분해 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 최적화 방법 (76ms)
        for (int i = 2; i * i <= N; i++) {
            if (N == 1) break;
            while (N % i == 0) {
                sb.append(i).append("\n");
                N /= i;
            }
        }
        if (N != 1) { // 주의
            sb.append(N);
        }
        System.out.println(sb);

        /*
        // 최적화 안한 버전 (116ms)
        for (int i = 2; i <= N; i++) {
            if (N == 1) break;
            while (N % i == 0) {
                sb.append(i).append("\n");
                N /= i;
            }
        }

         */
    }

}
