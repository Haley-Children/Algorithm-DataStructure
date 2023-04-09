package kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_16916 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String original = br.readLine();
        String sub = br.readLine();

        // 실패함수
        int[] failure = new int[sub.length()];

        int j = 0;
        for(int i = 1; i < sub.length(); i++) {
            while(j > 0 && sub.charAt(i) != sub.charAt(j)) {
                j = failure[j-1];
            }
            if(sub.charAt(i) == sub.charAt(j)) {
                failure[i] = ++j;
            }
        }

        j = 0;
        for(int i = 0; i < original.length(); i++) {
            while(j > 0 && original.charAt(i) != sub.charAt(j)) {
                j = failure[j-1];
            }
            if(original.charAt(i) == sub.charAt(j)) j++;
            if(j == failure.length) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }
}
