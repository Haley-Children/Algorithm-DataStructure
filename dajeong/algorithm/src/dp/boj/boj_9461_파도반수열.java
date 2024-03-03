package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9461_파도반수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long[] memo = new long[101]; // i번째 파도반수열 값 **long!!!
        memo[1] = 1;
        memo[2] = 1;
        memo[3] = 1;
        memo[4] = 2;
        for (int i = 5; i < memo.length; i++) {
            memo[i] = memo[i-2] + memo[i-3];
        }

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(memo[N]+"\n");
        }

        System.out.println(sb);
    }

}
