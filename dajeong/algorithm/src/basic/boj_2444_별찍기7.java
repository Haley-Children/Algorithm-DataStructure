package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2444_별찍기7 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        // java8은 repeat 함수 안됨
        for (int i = 1; i <= cnt; i++) {
            for (int j = 0; j < cnt - i; j++) {
                sb.append(" ");
            }
            for (int j = 0; j < 2*i-1; j++) {
                sb.append("*");
            }
            sb.append("\n");
        }
        for (int i = 1; i < cnt; i++) {
            for (int j = 0; j < i; j++) {
                sb.append(" ");
            }
            for (int j = 0; j < 2*(cnt-i)-1; j++) {
                sb.append("*");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
