package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2438_별찍기1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= cnt; i++) {
            sb.append("*".repeat(i)).append("\n");
        }
        System.out.println(sb);
    }

}
