package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2577_숫자의갯수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        int multiple = a * b * c;

        int[] cntArr = new int[10];
        String str = String.valueOf(multiple);
        for (char ch : str.toCharArray()) {
            int t = ch - '0';
            cntArr[t]++;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(cntArr[i]);
        }

    }
}
