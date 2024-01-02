package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11055 {

    /*
     * d[i]= i번째 수를 마지막으로했을 때 가장 큰 증가 부분 수열의 합
     * 내림차순일 때 예외 발생 ex. 2 1 1 1 1 케이스 고려할 것
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int ans = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] d = new int[n + 1];
        if (n == 1) {
            ans = arr[1];
            System.out.println(ans);
            return;
        }

        d[1] = arr[1];
        for (int i = 2; i <= n; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j] && d[j] > max) { // ***
                    max = d[j];
                }
            }
            d[i] = max + arr[i];
        }
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, d[i]);

        }

        System.out.println(ans);
    }
}

