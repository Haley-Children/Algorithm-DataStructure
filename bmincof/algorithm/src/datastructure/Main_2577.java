package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2577 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(20);

        int mult = Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine());
        int[] cnt = new int[10];

        while(mult > 0) {
            cnt[mult%10]++;
            mult /= 10;
        }

        for(int i : cnt) sb.append(i).append('\n');

        System.out.println(sb);
    }

}
