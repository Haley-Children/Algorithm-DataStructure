package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_10808 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(50);

        String word = br.readLine();
        int[] cnt = new int['z'-'a' + 1];

        for(int i = 0; i < word.length(); i++) {
            cnt[word.charAt(i) - 'a']++;
        }

        for(int i : cnt) sb.append(i).append(' ');

        System.out.println(sb);
    }
}
