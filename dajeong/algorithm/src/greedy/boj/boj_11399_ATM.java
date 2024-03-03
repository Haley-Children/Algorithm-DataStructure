package greedy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_11399_ATM {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int[] dp = new int[N];
        dp[0] = arr[0];
        for (int i = 1; i < N; i++) {
            dp[i] = dp[i-1] + arr[i];
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += dp[i];
        }

        System.out.println(sum);
    }

}
