package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2562_최댓값 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int max = 0;
        int maxIdx = 0;
        for (int i = 0; i < 9; i++) {
            int t = Integer.parseInt(br.readLine());
            if (max < t) {
                max = t;
                maxIdx = i+1;
            }
        }
        System.out.println(max);
        System.out.println(maxIdx);
    }

}
