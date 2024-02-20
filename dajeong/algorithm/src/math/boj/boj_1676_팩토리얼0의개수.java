package math.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1676_팩토리얼0의개수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // N! 소인수분해를 통해 2, 5의 쌍의 갯수 구하기
        int cnt2 = 0;
        int cnt5 = 0;

        for (int i = 1; i <= N; i++) {
            int x = i;
            while (x % 2 == 0) {
                x /= 2;
                cnt2++;
            }

            while (x % 5 == 0) {
                x /= 5;
                cnt5++;
            }

        }

        // 최소 지수 출력
        int cnt = Math.min(cnt2, cnt5);
        System.out.println(cnt);
    }

}
