package math.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1929_소수구하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[] state = new boolean[N + 1];
        StringBuilder sb = new StringBuilder();

        // 최적화 한 버전
        for (int i = 2; i * i <= N; i++) { // M부터의 소수를 구하는 것이더라도 소수 판별을 위해 2부터 시작해야 함
            if (state[i]) {
                continue;
            }
            for (int j = i * i; j <= N; j += i) {
                state[j] = true;
            }
        }

        for (int i = M; i < state.length; i++) {
            if (!state[i] && i != 1) { // 1주의
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb);


        // 최적화 안한 버전
        /*
        for (int i = 2; i <= N; i++) { // M부터의 소수를 구하는 것이더라도 소수 판별을 위해 2부터 시작해야 함
            if (state[i]) {
                continue;
            }
            for (int j = 2 * i; j <= N; j += i) {
                state[j] = true;
            }
        }

        for (int i = M; i < state.length; i++) {
            if (!state[i] && i != 1) { // 1 주의
                sb.append(i).append("\n");
            }
        }

         */

    }

}
