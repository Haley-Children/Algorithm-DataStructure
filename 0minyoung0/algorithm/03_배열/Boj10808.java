// 배열.boj10808;

import java.io.*;

public class Boj10808 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        int[] cnt = new int[26];
        for (int i=0; i<s.length(); i++) {
        	cnt[s.charAt(i)-'a']++;
        }
        for (int i=0; i<26; i++) {
        	sb.append(cnt[i]).append(" ");
        }
        System.out.println(sb);
    }
}