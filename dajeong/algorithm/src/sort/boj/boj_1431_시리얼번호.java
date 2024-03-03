package sort.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1431_시리얼번호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }
        Arrays.sort(arr, (a, b) -> {
            int len1 = a.length();
            int len2 = b.length();
            if (len1 == len2) {
                int d1 = splitDigit(a);
                int d2 = splitDigit(b);

                if (d1 == d2) {
                    return a.compareTo(b);
                } else {
                    return d1 - d2;
                }
            } else {
                return len1 - len2;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static int splitDigit(String str) {
        int sum = 0;
        for (char c : str.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                continue;
            }
            sum += (c-'0');
        }
        return sum;
    }

}
