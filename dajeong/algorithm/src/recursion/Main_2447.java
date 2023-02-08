package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2447{
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        recur(n);
        System.out.println(sb);
    }

    private static void recur(int n) {
        if (n == 1) {
            return;
        }

        int newSize = n / 3;
        recur(newSize);

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cnt++ == 5) sb.append(" ");
                sb.append("*");
            }
            cnt++;
            sb.append("\n");
        }


    }
}
