package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1181 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        ArrayList<String> arr = new ArrayList<>();

        // contain 이 O(N) 이므로 O(N^2)
        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            if(!arr.contains(s)) arr.add(s);
        }

        Collections.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() == s2.length()) {
                    for (int i = 0; i < s1.length(); i++) {
                        if (s1.charAt(i) == s2.charAt(i)) continue;
                        else return s1.charAt(i) < s2.charAt(i) ? -1 : 1;
                    }
                }
                return s1.length() < s2.length() ? -1 : 1;
            }
        });

        StringBuilder sb = new StringBuilder();
        for(String s : arr) {
            sb.append(s).append('\n');
        }

        System.out.println(sb);
    }
}

