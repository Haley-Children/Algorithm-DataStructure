package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_9655_돌게임 {

    static String[] memo;
    static final String NONE = "_";
    static final String SK = "SK";
    static final String CY = "CY";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        memo = new String[N+1];
        Arrays.fill(memo, NONE);

        System.out.println(dp(N));
    }

    private static String dp(int n) {
        if (!memo[n].equals(NONE)) {
            return memo[n];
        } else if (n == 1) {
            return memo[1] = SK;
        } else if (n > 3){
            memo[n] = dp(n-3).equals(SK) ? CY : SK;
        } else if (n > 1) {
            memo[n] = dp(n-1).equals(SK) ? CY : SK;
        }
        return memo[n];
    }

}
