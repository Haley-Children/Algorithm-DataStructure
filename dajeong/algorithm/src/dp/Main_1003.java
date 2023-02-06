package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1003 {
    // 시간 제한이 0.25초이고 최대 입력값이 40이므로 재귀로 풀 수 없다. -> dp
    // oneCnt[n] = oneCnt[n-1] + oneCnt[n-2]
    // zeroCnt[n] = zeroCnt[n-1] + zeroCnt[n-2]
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] oneCnt = new int[n+1];
            int[] zeroCnt = new int[n+1];
            if (n == 1) {
                sb.append(0).append(" ").append(1).append("\n");
                continue;
            } else if (n == 2) {
                sb.append(1).append(" ").append(1).append("\n");
                continue;
            } else if (n == 0) { // ***
                sb.append(1).append(" ").append(0).append("\n");
                continue;
            }

            oneCnt[1] = 1;
            zeroCnt[1] = 0;
            oneCnt[2] = 1;
            zeroCnt[2] = 1;
            for (int j = 3; j <= n; j++) {
                oneCnt[j] = oneCnt[j-1] + oneCnt[j-2];
                zeroCnt[j] = zeroCnt[j-1] + zeroCnt[j-2];
            }
            sb.append(zeroCnt[n]).append(" ").append(oneCnt[n]).append("\n");
        }
        System.out.println(sb);
    }
}
