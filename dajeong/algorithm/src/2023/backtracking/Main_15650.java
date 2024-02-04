package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// n과 m (2)
public class Main_15650 {
    static int[] arr;
    static int n,m;
    static int[] isUsed;
    private static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        isUsed = new int[n+1];
        arr = new int[m];
        func(0, 1);
    }

    // arr 배열의 k번째 수까지 정해졌을 때. for문의 시작위치도 파라미터로 넘겨주어야 한다.
    private static void func(int k, int start) {
        if (k == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(arr[i]+" ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= n; i++) {
            if (isUsed[i]==0) {
                arr[k] = i;
                isUsed[i]=1;
                func(k+1, i+1); // != (start+1) / depth와 같은 의미
                isUsed[i]=0;
            }
        }
    }
}
