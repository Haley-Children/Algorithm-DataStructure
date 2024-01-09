package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1267_핸드폰요금 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        int ySum = 0, mSum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cnt; i++) {
            int price = Integer.parseInt(st.nextToken());
            ySum += (price % 30 >= 0 ? price / 30 + 1 : price / 30) * 10;
            mSum += (price % 60 >= 0 ? price / 60 + 1 : price / 60) * 15;
        }
        if (ySum < mSum) {
            System.out.println(String.format("%s %d", "", ySum));
        } else if (mSum < ySum) {
            System.out.println(String.format("%s %d", "M", mSum));
        } else {
            System.out.println(String.format("%s %d", "Y M", mSum));
        }
    }

}
