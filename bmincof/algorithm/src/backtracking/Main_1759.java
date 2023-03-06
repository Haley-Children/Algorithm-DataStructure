package backtracking;

import java.util.Arrays;
import java.util.Scanner;

public class Main_1759 {
    static int l, c;
    static char[] alphabets;
    static boolean[] isUsed;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        l = sc.nextInt();
        c = sc.nextInt();

        alphabets = new char[c];
        isUsed = new boolean[c];

        for (int i = 0; i < c; i++) {
            alphabets[i] = sc.next().charAt(0);
        }

        // 알파벳 오름차순 정렬
        Arrays.sort(alphabets);

        makeOrder(0, -1, 0, 0);
        System.out.println(sb);
    }

    static void makeOrder(int k, int prev, int vowel, int consonant) {
        if(k == l) {
            if(vowel >= 1 && consonant >= 2) {
                for (int i = 0; i < c; i++) {
                    if(isUsed[i]) {
                        sb.append(alphabets[i]);
                    }
                }
                sb.append("\n");
            }
            return;
        }

        for (int i = prev + 1; i < c; i++) {
            isUsed[i] = true;
            if("aeiou".indexOf(alphabets[i]) != -1) {
                makeOrder(k + 1, i, vowel + 1, consonant);
            } else {
                makeOrder(k + 1, i, vowel, consonant + 1);
            }
            isUsed[i] = false;
        }
    }
}
