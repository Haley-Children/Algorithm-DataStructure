package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 처음부터 dp 문제라는 것을 알지 못했다면, dp로 접근해야한다는 것을 알기 힘든 문제!

/*
 * 1. d[i] = 2 * i 크기의 직사각형을 채우는 방법의 수
 * 2. d[k] = d[k-1] + d[k-2]
 * 3. d[1] = 1, d[2] = 2
 */
public class Main_11726 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] d = new int[n + 1];

        if (n == 1) {
            System.out.println(1);
            return;
        } else if (n == 2) {
            System.out.println(2);
            return;
        }
        d[1] = 1;
        d[2] = 2;

        for (int i = 3; i <= n; i++) {
            d[i] = (d[i - 1] + d[i - 2]) % 10007;
        }
        System.out.println(d[n]);
    }
}
