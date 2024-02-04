package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2193_이친수 {

    public static void main(String[] args) throws IOException {
        long[] dp = new long[91]; // ****  제일 끝 값 넣어보고 엣지케이스 파악하기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // dp[i] = i번째 자리에(오른쪽 끝이 n번째) 가능한 이진수의 개수
        // ** 생각의 전환) 왼쪽 끝에 넣기 힘들면, 오른쪽 끝을 기준으로 생각해보자.
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;

        for (int i = 4; i <= N; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(dp[N]);
    }

}
