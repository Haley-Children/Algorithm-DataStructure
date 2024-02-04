package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2441_별찍기4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = cnt; i >= 1; i--) {
            sb.append(" ".repeat(cnt - i));
            sb.append("*".repeat(i)).append("\n");
        }
        System.out.println(sb);
    }
}
