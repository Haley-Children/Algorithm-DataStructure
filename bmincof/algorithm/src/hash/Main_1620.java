package hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1620 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, String> dictionary = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            String name = br.readLine();
            String idx = String.valueOf(i);
            dictionary.put(idx, name);
            dictionary.put(name, idx);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String cmd = br.readLine();
            sb.append(dictionary.get(cmd)).append("\n");
        }

        System.out.println(sb);
    }
}
