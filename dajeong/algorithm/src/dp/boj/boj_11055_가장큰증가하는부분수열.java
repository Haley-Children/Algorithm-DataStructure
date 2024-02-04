package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_11055_가장큰증가하는부분수열 {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i] = i번째 수열까지 마지막으로 도달했을 때 부분수열 합의 최댓값
        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        dp[0] = arr[0];

        // dp 채우기
        for (int i = 1; i < N; i++) {
            dp[i] = arr[i]; // ****
            for (int j = 0; j < i; j++) {
                if (arr[j] >= arr[i] || dp[j] == -1) {
                    continue;
                }
                dp[i] = Math.max(dp[j] + arr[i], dp[i]);
            }
        }

        // 최댓값 출력
        int max = -1;
        for (int i = 0; i < N; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        System.out.println(max);

    }

}
