package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1463 {

    static int[] d = new int[1000005];
    /*
     * 1. 테이블 정의: d[i] = i를 1로 만들기 위해 필요한 연산 사용 횟수의 최솟값
     * 2. 점화식 : min(d[i/3]+1, d[i/2]+1, d[i]-1)
     * 3. 초기값: d[i] = 0
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        d[1] = 0;
        for (int i = 2; i <= n; i++) {
            d[i] = d[i-1] + 1;
            if (i % 2 == 0) d[i] = Math.min(d[i], d[i/2]+1);
            if (i % 3 == 0) d[i] = Math.min(d[i], d[i/3]+1);
        }
        System.out.println(d[n]);
    }

}
