package dp;

import static java.lang.Math.max;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1. d[i][j] = 현재까지 j개의 계단을 연속해서 밟고 i번째 계단까지 올라섰을 때 점수합의 최댓값. (단, i번째 계단은 반드시 밟아야 함.)
 * 2. d[k][1] = max(d[k-2][1], d[k-2][2]) + s[k] (k번째 계단의 점수)
 *    d[k][2] = d[k-1][1] + s[k]
 * 3. d[1][1] = 1, d[2][1] = 1, d[2][2] = 1 / d[1][2] = 0
 */

public class Main_2579 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] scores = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }
        int[][] d = new int[n+1][3];
        if (n == 1) {
            System.out.println(scores[1]);
            return;
        }
        d[1][1] = scores[1]; d[2][1] = scores[2]; d[2][2] = scores[1]+scores[2]; d[1][2] = 0; // n = 1,2일 경우 강제로 접근하게 되는데 scores값이 없어서 error 발생
        if (n == 2) {
            System.out.println(max(d[2][1], d[2][2]));
            return;
        }
        for (int i = 3; i <= n; i++) { // n = 1,2일 경우 강제로 접근하게 됨
            d[i][1] = max(d[i-2][1], d[i-2][2]) + scores[i];
            d[i][2] = d[i-1][1] + scores[i];
        }
        System.out.println(max(d[n][1], d[n][2]));
    }
}
