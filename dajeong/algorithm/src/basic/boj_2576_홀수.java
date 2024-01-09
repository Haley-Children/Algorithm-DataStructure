package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2576_홀수 {

    public static void main(String[] args) throws IOException {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 7; i++) {
            int t = Integer.parseInt(br.readLine());
            if (t % 2 == 1) {
                sum += t;
                if (min > t) {
                    min = t;
                }
            }
        }

        if (sum == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sum);
            System.out.println(min);
        }
    }

}
