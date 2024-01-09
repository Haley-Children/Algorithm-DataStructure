package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_10799_쇠막대기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0;
        int pos = 0;
        boolean flag = true; // 바로 전에 '('이면 true, ')'이면 false
        for (char c : br.readLine().toCharArray()) {
            if (c == '(') {
                flag = true;
                pos++;
            } else { // )
                pos--;
                if (flag) {
                    ans += pos;
                } else {
                    ans++;
                }
                flag = false;
            }
        }
        System.out.println(ans);
    }

}
