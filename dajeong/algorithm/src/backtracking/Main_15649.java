package backtracking;

import java.util.Scanner;

// n과 m (1)
public class Main_15649 {
    static int n,m; // 4 2
    static int[] arr; // 0 0 0 0
    static int[] isUsed; // 0 1 2 3 4

    // arr 배열의 k번째 수까지 정해진 상태에서 나머지 수 구하기 -> 배열은 0부터 시작. 1번째 수는 index = 2
    private static void func(int k) {
        if (k == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(arr[i]+" ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (isUsed[i]==0) {
                arr[k] = i;
                isUsed[i] = 1;
                func(k+1);
                isUsed[i] = 0;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[m];
        isUsed = new int[n+1];
        func(0);
    }
}
