package greedy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2217_로프 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 로프 수
        int[] w = new int[N];
        for (int i = 0; i < N; i++) {
            w[i] = Integer.parseInt(br.readLine());
        }
        Arrays.parallelSort(w);

        int max = 0;
        for (int i = 0; i < N; i++) {
            int wx = w[i];
            if (max < wx * (N-i)) {
                max = wx * (N-i);
            }
        }

        System.out.println(max);
    }
}
