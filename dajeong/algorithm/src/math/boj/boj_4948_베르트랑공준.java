package math.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_4948_베르트랑공준 {

    static final int N = 123456;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 에라토스테네스의 체로 소수 구하기
        boolean[] state = new boolean[2 * N + 1];
        Arrays.fill(state, true);
        state[0] = false;
        state[1] = false;
        for (int i = 2; i * i <= 2 * N; i++) {
            if (!state[i]) {
                continue;
            }
            for (int j = i * i; j <= 2 * N; j+=i) {
                if (state[j]) {
                    state[j] = false;
                }
            }
        }

        //System.out.println(Arrays.toString(state));

        // 누적합
        int[] sum = new int[2 * N + 1];
        for (int i = 2; i <= 2 * N; i++) {
            if (state[i]) {
                sum[i] = sum[i-1] + 1;
            } else {
                sum[i] = sum[i-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        while (true) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                break;
            }
            sb.append(sum[2 * num] - sum[num]).append("\n");
        }

        System.out.println(sb);
    }

}
