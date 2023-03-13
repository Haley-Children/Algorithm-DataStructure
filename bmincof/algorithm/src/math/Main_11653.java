package math;

import java.util.Scanner;

public class Main_11653 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();
        if(n == 1) {
            return;
        }

        boolean[] isNotPrime = new boolean[3300];

        for(int i = 2; i < 3300; i++) {
            if(isNotPrime[i]) continue;

            for(int j = i*2; j < 3300; j+=i) {
                isNotPrime[j] = true;
            }
        }

        for(int i = 2; i < 3300; i++) {
            if(isNotPrime[i]) continue;

            while (n % i == 0) {
                sb.append(i).append("\n");
                n /= i;
            }
        }
        if(n > 3300) {
            sb.append(n).append("\n");
        }
        System.out.println(sb);
    }
}
