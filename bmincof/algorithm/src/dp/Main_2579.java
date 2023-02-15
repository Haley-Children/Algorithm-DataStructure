package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2579 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] steps = new int[n+1];
        for (int i = 1; i <= n; i++) {
            steps[i] = Integer.parseInt(br.readLine());
        }

        if(n == 1) {
            System.out.println(steps[1]);
            return;
        }

        // 1번 연속 : i번쨰 계단을 밟았다면 i-2번째 계단에서 1번 연속 , 2번 연속 중 최대
        // 2번 연속 : i번째 계단을 밟았다면 i-1번째 계단에서 1번 연속일 때 값

        // i번째 계단을 연속으로 1번 밟은 상태에서 최대값
        int[] serial1 = new int[n+1];
        // i번째 계단을 연속으로 2번 밟은 상태에서 최대값
        int[] serial2 = new int[n+1];

        // 초기 상태
        serial1[1] = steps[1];

        for (int i = 2; i < n; i++) {
            serial2[i] = serial1[i-1] + steps[i];
            serial1[i] = Math.max(serial1[i-2],serial2[i-2]) + steps[i];
        }

        System.out.println(Math.max(Math.max(serial2[n-2], serial1[n-2]), serial1[n-1]) + steps[n]);
    }
}

