package dp.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class code_피보나치수 {

    static int[] memo = new int[46];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Arrays.fill(memo, -1);
        System.out.println(Fibo(Integer.parseInt(br.readLine())));
    }

    private static int Fibo(int N) {
        if (memo[N] != -1) {
            return memo[N];
        }
        if (N <= 2) {
            memo[N] = 1;
        } else {
            memo[N] = Fibo(N - 1) + Fibo(N - 2);
        }
        return memo[N];
    }

}
