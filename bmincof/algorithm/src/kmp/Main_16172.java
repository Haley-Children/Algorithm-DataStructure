package kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_16172 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();
        char[] keyword = br.readLine().toCharArray();

        int[] failure = new int[keyword.length];

        int j = 0;
        for(int i = 1; i < keyword.length; i++) {
            while(j > 0 && keyword[i] != keyword[j]) j = failure[j-1];
            if(keyword[i] == keyword[j]) failure[i] = ++j;
        }

        j = 0;
        for(int i = 1; i < str.length; i++) {
            while(j > 0 && str[i] > '9' && str[i] != keyword[j]) j = failure[j-1];
            if(str[i] == keyword[j]) j++;
            if(j == failure.length) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }
}
