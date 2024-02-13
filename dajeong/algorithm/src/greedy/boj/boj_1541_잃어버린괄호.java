package greedy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1541_잃어버린괄호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        int sum = 0;
        int num = 0;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                num = Integer.parseInt(sb.toString());
                sb = new StringBuilder();
                if (c == '-') {
                    if (flag) {
                        sum -= num;
                    } else {
                        flag = true;
                        sum += num;
                    }
                } else {
                    if (flag) {
                        sum -= num;
                    } else {
                        sum += num;
                    }
                }
            }
        }
        // 마지막 원소 처리
        num = Integer.parseInt(sb.toString());
        if (flag) {
            sum -= num;
        } else {
            sum += num;
        }
        System.out.println(sum);
    }

}
