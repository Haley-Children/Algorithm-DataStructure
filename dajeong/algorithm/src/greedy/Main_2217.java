package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] lope = new int[n];
        for (int i = 0; i < n; i++) {
            lope[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lope);
        int max = 0;
        for (int i = 0; i < lope.length; i++) {
            int t = lope[i];
            int sum = t;
            for (int j = i+1; j < lope.length; j++) {
                if (lope[j] >= t) sum += t;
            }
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
}
