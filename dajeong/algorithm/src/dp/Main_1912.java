package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1912 {
    // 아이디어가 조금 헷갈렸다. d[n]을 최대 누적합들의 기록으로 생각

    /*
     * d[i] = i번째 수를 마지막으로 연속된 수들의 최대합(sum)
     * d[i] = max(arr[i], arr[i] + d[i-1])
     * d[1] = arr[1] (1부터 시작)
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1];
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 1) {
            System.out.println(arr[1]);
            return;
        }
        d[1] = arr[1];
        for (int i = 2; i <= n; i++) {
            d[i] = Math.max((d[i - 1] + arr[i]), arr[i]);
        }

        // 최대 누적합 찾기
        int max = -2000; //***** -1000이상이므로 0으로 두면 안된다!
        for (int i = 1; i <= n; i++) {
            if (d[i] > max) max = d[i];
        }
        System.out.println(max);

    }
}
