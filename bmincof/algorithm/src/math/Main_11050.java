package math;

import java.util.Scanner;

public class Main_11050 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        // 더 작은 k 선택
        k = n - k > k ? k : n - k;

        int above = 1;
        int below = 1;
        for(int i = 1; i <= k; i++) {
            above *= (n - i + 1);
            below *= i;
        }

        System.out.println(above / below);
    }
}
