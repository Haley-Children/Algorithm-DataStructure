package arrays_strings;

import java.util.Arrays;

public class IsPermutationImpl {
    public static void main(String[] args) {
        System.out.println(permutation("ABC", "BCA"));
        System.out.println(permutation("ABC", "BDA"));

        System.out.println(permutation2("ABC", "BCA"));
        System.out.println(permutation2("ABC", "BDA"));
    }

    private static String sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    private static boolean permutation(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        return sort(s).equals(sort(t));
    }

    private static boolean permutation2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] letters = new int[128];

        for(int i = 0; i < s.length(); i++) {
            letters[s.charAt(i)]++;
        }


        for(int i = 0; i < t.length(); i++) {
            letters[t.charAt(i)]--;

            if (letters[t.charAt(i)] < 0) {
                return false;
            }
        }

        return true;
    }
}