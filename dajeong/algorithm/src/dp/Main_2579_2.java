package dp;


import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 두 번째 풀이 (1차원 테이블)
 * 1. d[i] = i번째 계단까지 올라섰을 때 "밟지 않을 계단의 합의 최솟값", 단 i번째 계단은 반드시 밟지 않을 계단으로 선택해야 함.
 * 2. d[k] = s[k] + min(d[k-2],d[k-3]) (k번째 계단을 밟지 않을 때 k-1번째 계단을 반드시 밟아야 함)
 * 3. d[1] = 10, d[2] = 20, d[3] = 15
 */

public class Main_2579_2 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] scores = new int[n + 1];
        int[] d = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }
        int sum = Arrays.stream(scores).sum();
        if (n == 1) {
            System.out.println(sum);
            return;
        } else if (n == 2) {
            System.out.println(sum);
            return;
        }

        d[1] = scores[1];
        d[2] = scores[2];
        d[3] = scores[3];

        for (int k = 4; k <= n; k++) {
            d[k] = scores[k] + min(d[k - 2], d[k - 3]);
        }
        System.out.println(sum - min(d[n-1], d[n-2])); // n번째 계단은 마지막에 반드시 밟아야 하므로 n-1 혹은 n-2를 밟지 않았을 경우을 빼주어야 한다.
    }
}
