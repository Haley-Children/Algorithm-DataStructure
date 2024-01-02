package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9095 {

    /*
     * 1. d[i] = i를 1,2,3의 합으로 조합해서 만들기 위해 가능한 조합의 수
     * 2. d[i] = sum(d[i-1], d[i-2], d[i-3])
     * 3. 초기값 = d[1] = 1 / d[2] = 2
     */
    static int[] d = new int[12];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int n = 0;
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            d[1] = 1;
            d[2] = 2;
            d[3] = 4;
            for (int j = 4; j < d.length; j++) {
                d[j] = d[j-1] + d[j-2] + d[j-3];
            }
            System.out.println(d[n]);
        }
    }

}
