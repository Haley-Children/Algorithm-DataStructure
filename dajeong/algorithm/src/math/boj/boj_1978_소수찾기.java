package math.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1978_소수찾기 {

    public static void main(String[] args) throws IOException {
        // 합성수일 때 가능한 가장 작은 약수는 루트 N 이하의 수이다. (증명 참고)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (isPrime(arr[i])) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static boolean isPrime(int n) {
        if (n == 1) return false;
        for (int i = 2; i * i <= n; i++) { // 등호 주의
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
