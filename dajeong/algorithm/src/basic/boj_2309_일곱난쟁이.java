package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2309_일곱난쟁이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        int[] arr = new int[9];
        for (int i = 0; i < 9; i++) {
            int t = Integer.parseInt(br.readLine());
            arr[i] = t;
            sum += t;
        }

        int diff = sum - 100;
        Arrays.sort(arr);
        int fake1 = 0, fake2 = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            int s = arr[fake1] + arr[fake2];
            if (s == diff) {
                break;
            } else if (s < diff) {
                fake1++;
            } else {
                fake2--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (i != fake1 && i != fake2) {
                sb.append(arr[i]).append("\n");
            }
        }
        System.out.println(sb);

    }

}
