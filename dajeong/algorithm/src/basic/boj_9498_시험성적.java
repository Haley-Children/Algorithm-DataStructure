package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9498_시험성적 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        char ans = 'F';
        if (90 <= t && t <= 100) {
            ans = 'A';
        } else if (80 <= t && t < 90) {
            ans = 'B';
        } else if (70 <= t && t < 80) {
            ans = 'C';
        } else if (60 <= t && t < 70) {
            ans = 'D';
        }

        System.out.println(ans);
    }
}
